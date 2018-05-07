package fractalfintech.orderbook;

import fractalfintech.orderbook.Order;

import java.lang.Double;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderBook
{
	private final Logger logger = LoggerFactory.getLogger(OrderBook.class);
	
    private String itemName;
    // Maps use List as value to hold multiple values with same hash
    private Map<Double, List<Order>> bidMap = null;
    private Map<Double, List<Order>> offerMap = null;

    private Queue<Double> bidMaxPriceList = null;
    private Queue<Double> offerMinPriceList = null;

    // Initializes marketplace
    public OrderBook(String name)
    {
        bidMap = new HashMap<Double, List<Order>>();
        offerMap = new HashMap<Double, List<Order>>();

        bidMaxPriceList = new PriorityQueue<Double>(30, Collections.reverseOrder()); // top is maximum bid price
        offerMinPriceList = new PriorityQueue<Double>();  // top is minimum offer price

        this.itemName = name;
    }

    public void setName(String name)
    {
      this.itemName = name;
    }

    /*  Adds bid to map by hashing the price, then
     *  adding bid to list located in that hash bucket
     */
    public void addBid(double price, int quantity)
    {
    	logger.info("addBid qty : {}", quantity);
    	logger.info("addBid price : {}", price);
        List<Order> bucket = getBucket(bidMap, price);
        Order newBid = new Order(price, quantity);
        bucket.add(newBid);
        bidMap.put(newBid.getPrice(), bucket);
        bidMaxPriceList.add(price);
        matchOrders();
    }

    /*  Adds offer to map by hashing the price, then
     *  adding offer to list located in that hash bucket
     */
    public void addOffer(double price, int quantity)
    {
        List<Order> bucket = getBucket(offerMap, price);
        Order newOffer = new Order(price, quantity);
    	logger.info("addOffer qty : {}", newOffer.getQuantity());
    	logger.info("addOffer price : {}", newOffer.getPrice());
    	logger.info("offerMap size : {}", bucket.size());
        bucket.add(newOffer);
    	logger.info("offerMap after insert size : {}", bucket.size());
        offerMap.put(newOffer.getPrice(), bucket);
        offerMinPriceList.add(price);
        matchOrders();
    }

    // Returns bucket list if price match, otherwise returns new list
    public List<Order> getBucket(Map<Double, List<Order>> hashmap, Double price)
    {
        List<Order> bucket;
        if(hashmap.containsKey(price))
        {
            bucket = hashmap.get(price);
        }
        else
        {
            bucket = new LinkedList<Order>();
        }
        return bucket;
    }

    // Matches offers and bids based on pricetime priority
    public void matchOrders()
    {
        List<Order> bidBucket = null;
        List<Order> offerBucket = null;
        Double lowestOffer = null;
        Double highestBid = null;
        boolean finished = false;

        while(!finished)
        {
            // Peek because we don't want to remove the top element until the order is closed
            highestBid = bidMaxPriceList.peek();
            lowestOffer = offerMinPriceList.peek();

            // No possible trade if either list is empty or no bid higher than an offer
            if(lowestOffer == null || highestBid == null || lowestOffer > highestBid)
            {
                finished = true;
            	logger.info("OrderBook matchOrders finished = true");
            }
            else
            {
                // Gets buckets for both maps
                bidBucket = bidMap.get(bidMaxPriceList.peek());
                offerBucket = offerMap.get(offerMinPriceList.peek());

                // Gets first element from each bucket since they're the oldest
                int bidQuantity = bidBucket.get(0).getQuantity();
                int offerQuantity = offerBucket.get(0).getQuantity();

                if(bidQuantity > offerQuantity)
                {
                	logger.info("bidQuantity > offerQuantity");
                    System.out.println(successfulTrade(offerQuantity, lowestOffer));

                    // Decrements quantity in bid
                    bidQuantity -= offerQuantity;
                    bidBucket.get(0).setQuantity(bidQuantity);
                	logger.info("bidQuantity remaining qty : {}", bidQuantity);

                    // Closes previous offer
                    offerBucket.remove(0);
                    offerMinPriceList.remove();
                }
                else if(offerQuantity > bidQuantity)
                {
                	logger.info("bidQuantity < offerQuantity");
                    System.out.println(successfulTrade(bidQuantity, lowestOffer));

                    // Decrements quantity in offer
                    offerQuantity -= bidQuantity;
                    offerBucket.get(0).setQuantity(offerQuantity);
                	logger.info("offerQuantity remaining qty : {}", offerQuantity);

                    //  Closes previous bid
                    bidBucket.remove(0);
                    bidMaxPriceList.remove();
                }
                else
                {
                    // bidQuantity is an arbitrary choice because both quantities are equal.
                    // lowestOffer is chosen because it's the price at which the trade is made.
                    System.out.println(successfulTrade(bidQuantity, lowestOffer));

                    // Removes bid and offer because they're both closed
                    bidBucket.remove(0);
                    bidMaxPriceList.remove();
                    offerBucket.remove(0);
                    offerMinPriceList.remove();
                }
            }
        }
    }

    // Returns the string printed for a successful trade.
    public String successfulTrade(int quantity, double price)
    {
    	logger.info("successfulTrade bidQuantity : {}", quantity);
    	logger.info("successfulTrade lowestOffer : {}", price);
        return quantity + " shares traded for $" + price + " per share.";
    }

    // Prints the remaining trades from input map after close of market.
    private void printFailedTrades(Map<Double, List<Order>> hashmap, String type)
    {
        for (Double key : hashmap.keySet())
        {
            List<Order> bucket = hashmap.get(key);

            for(Order order : bucket)
            {
                System.out.println(type + order.getQuantity() + " shares for $" + order.getPrice() + " per share failed to trade.");
            }
        }
    }

    // Signifies that the market is open.
    public void openMarket()
    {
        System.out.println("Market opens.");
    }

    public void closeMarket()
    {
        System.out.println("Market closes.");
        printFailedTrades(bidMap, "Bid for ");
        printFailedTrades(offerMap, "Offer of ");
    }

    public Map<Double, List<Order>> getBidMap()
    {
        return bidMap;
    }

    public Map<Double, List<Order>> getOfferMap()
    {
        return offerMap;
    }
}
