package fractalfintech.orderbook;

import fractalfintech.orderbook.MarketItem;
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
    private MarketItem market;
    private Map<String, MarketItem> itemList = null;

    public MarketList()
    {
        itemList = new HashMap<String, MarketItem>();
        market = new MarketItem("Test");
        itemList.put("Test", market);
    }

    public void Add(String name)
    {
      market = new MarketItem(name);
      itemList.put(name, market);
    }

  }
