package fractalfintech.orderbook;

import fractalfintech.orderbook.OrderBook;
import fractalfintech.orderbook.MarketList;
import fractalfintech.orderbook.Order;
import fractalfintech.orderbook.OrderItemDao;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.lang.Double;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


@RestController
public class MarketController
{
    private MarketList marketList;

    // Initializes marketplace
    public MarketController()
    {
        marketList = new MarketList();
        marketList.Add("Test");
    }


    @RequestMapping("/market/add/item/{name}")
  	public String AddMarketItem(@PathVariable(value="name") String name) {
        marketList.Add(name);
  			return "success";
  	}

    @PostMapping("/market/bid/add")
  	public String AddMarketBid(@ModelAttribute OrderItemDao item) {
  			return "success";
  	}

    @PostMapping("/market/offer/add")
  	public String AddMarketOffer(@ModelAttribute OrderItemDao item) {
  			return "success";
  	}

    @RequestMapping("/")
  	public String index() {
  			return "Greetings from Spring Boot!";
  	}

  }
