package fractalfintech.orderbook;

import fractalfintech.orderbook.OrderBook;
import fractalfintech.orderbook.Order;

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

  }
