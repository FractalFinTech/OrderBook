package fractalfintech.orderbook;

import fractalfintech.orderbook.OrderBook;
import fractalfintech.orderbook.Order;
import fractalfintech.orderbook.OrderItemDao;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.Double;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class MarketList
{
    private OrderBook market;
    private Map<String, OrderBook> orderBooks = null;

    public MarketList()
    {
        orderBooks = new HashMap<String, OrderBook>();
        market = new OrderBook("Test");
        orderBooks.put("Test", market);
    }

    public void Add(String name)
    {
      market = new OrderBook(name);
      orderBooks.put(name, market);
    }

    public void AddBid(OrderItemDao bid)
    {
    	if (orderBooks.containsKey(bid.getName())) {
    		OrderBook book = orderBooks.get(bid.getName());
    		book.addBid(bid.getPrice(), bid.getQty());
    	}
    }
    
    public void AddOffer(OrderItemDao offer)
    {
    	if (orderBooks.containsKey(offer.getName())) {
    		OrderBook book = orderBooks.get(offer.getName());
    		book.addBid(offer.getPrice(), offer.getQty());
    	}
    }
    
    public Map<Double, List<Order>> GetBidMap(OrderItemDao bid)
    {
    	if (orderBooks.containsKey(bid.getName())) {
    		OrderBook book = orderBooks.get(bid.getName());
        	Map<Double, List<Order>> bidMap = book.getBidMap();
            return bidMap;
    	}
    	return null;
    }    

    public Map<Double, List<Order>> GetOfferMap(OrderItemDao offer)
    {
    	if (orderBooks.containsKey(offer.getName())) {
    		OrderBook book = orderBooks.get(offer.getName());
        	Map<Double, List<Order>> offerMap = book.getOfferMap();
            return offerMap;
    	}
    	return null;
    }    
    
  }
