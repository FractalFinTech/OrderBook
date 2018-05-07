package fractalfintech.orderbook;

//import fractalfintech.orderbook.OrderBook;
//import fractalfintech.orderbook.Order;
//import fractalfintech.orderbook.OrderItemDao;


import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MarketList
{
	private final Logger logger = LoggerFactory.getLogger(MarketList.class);
    private OrderBook market;
    private Map<String, OrderBook> orderBooks = null;

    public MarketList()
    {
        orderBooks = new HashMap<String, OrderBook>();
        market = new OrderBook("Test");
        orderBooks.put("Test", market);
    }

    public List<String> GetList()
    {
      List<String> list = new ArrayList<String>(orderBooks.keySet());  
      return list;
    }

    public void Add(String name)
    {
      if (!orderBooks.containsKey(name)) {
          market = new OrderBook(name);
          orderBooks.put(name, market);
      }
    }

    public void AddBid(OrderItemDao bid)
    {
    	logger.info("MarketList AddBid name : {}", bid.getName());
    	logger.info("MarketList AddBid price : {}", bid.getPrice());
    	logger.info("MarketList AddBid qty : {}", bid.getQty());
    	if (orderBooks.containsKey(bid.getName())) {
    		OrderBook book = orderBooks.get(bid.getName());
    		book.addBid(bid.getPrice(), bid.getQty());
    	}
    }

    public void AddOffer(OrderItemDao offer)
    {
    	logger.info("MarketList AddOffer name : {}", offer.getName());
    	logger.info("MarketList AddOffer price : {}", offer.getPrice());
    	logger.info("MarketList AddOffer qty : {}", offer.getQty());
    	if (orderBooks.containsKey(offer.getName())) {
    		OrderBook book = orderBooks.get(offer.getName());
    		book.addOffer(offer.getPrice(), offer.getQty());
    	}
    }

    public Map<Double, List<Order>> GetBidMap(OrderItemDao bid)
    {
    	logger.info("MarketList GetBidMap name : {}", bid.getName());
    	
    	if (orderBooks.containsKey(bid.getName())) {
    		OrderBook book = orderBooks.get(bid.getName());
        	Map<Double, List<Order>> bidMap = book.getBidMap();
            return bidMap;
    	}
    	return null;
    }

    public Map<Double, List<Order>> GetOfferMap(OrderItemDao offer)
    {
    	logger.info("MarketList GetOfferMap name : {}", offer.getName());

    	if (orderBooks.containsKey(offer.getName())) {
    		OrderBook book = orderBooks.get(offer.getName());
        	Map<Double, List<Order>> offerMap = book.getOfferMap();
            return offerMap;
    	}
    	return null;
    }

  }
