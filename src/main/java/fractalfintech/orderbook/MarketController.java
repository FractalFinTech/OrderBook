package fractalfintech.orderbook;

import fractalfintech.orderbook.OrderBook;
import fractalfintech.orderbook.MarketList;
import fractalfintech.orderbook.Order;
import fractalfintech.orderbook.OrderItemDao;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	private final Logger logger = LoggerFactory.getLogger(MarketController.class);
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

    @RequestMapping("/market/list/get")
    @ResponseBody
  	public List<String>  GetMarketList() {
    	List<String> list = marketList.GetList();
        return list;
  	}

    @RequestMapping(value = "/market/bid/add", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<String> AddMarketBid(@RequestBody OrderItemDao bid) {
    	logger.info("MarketController bid name : {}", bid.getName());
    	logger.info("MarketController bid price : {}", bid.getPrice());
    	logger.info("MarketController bid qty : {}", bid.getQty());
    	marketList.AddBid(bid);
  		return new ResponseEntity<String>("success: bid added.", HttpStatus.OK);
  	}



    @RequestMapping(value = "/market/offer/add", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<String> AddMarketOffer(@RequestBody OrderItemDao offer) {
    	logger.info("MarketController offer name : {}", offer.getName());
    	logger.info("MarketController offer price : {}", offer.getPrice());
    	logger.info("MarketController offer qty : {}", offer.getQty());
    	marketList.AddOffer(offer);
  		return new ResponseEntity<String>("success: offer added.", HttpStatus.OK);
  	}

    @RequestMapping(value = "/market/offer/get", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<Map<Double, List<Order>>> GetMarketOffer(@RequestBody OrderItemDao offer) {
    	logger.info("MarketController GetMarketOffer name : {}", offer.getName());
    	Map<Double, List<Order>> list = marketList.GetOfferMap(offer);
    	return new ResponseEntity<Map<Double, List<Order>>>(list, HttpStatus.OK);
  	}


    @RequestMapping(value = "/market/bid/get", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<Map<Double, List<Order>>> GetMarketBid(@RequestBody OrderItemDao bid) {
    	logger.info("MarketController GetMarketBid name : {}", bid.getName());
    	Map<Double, List<Order>> list = marketList.GetBidMap(bid);
    	return new ResponseEntity<Map<Double, List<Order>>>(list, HttpStatus.OK);
  	}
    
    
    @RequestMapping("/")
  	public String index() {
  		return "Greetings from Spring Boot!";
  	}

  }
