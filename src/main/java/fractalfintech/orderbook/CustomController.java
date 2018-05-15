package fractalfintech.orderbook;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
    private MarketList marketList;

    public CustomController()
    {
        marketList = new MarketList();
        marketList.Add("Test");
    }

	@RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
		//final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //logger.info(username);
        //return "Welcome, " + username;
		return "Custom message here.";
    }
	
    @RequestMapping(value = "/add/item/{name}", method = RequestMethod.GET)
  	public String AddMarketItem(@PathVariable(value="name") String name) {
        marketList.Add(name);
        return "success";
  	}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
  	public List<String>  GetMarketList() {
    	List<String> list = marketList.GetList();
        return list;
  	}

    @RequestMapping(value = "/bid/add", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<String> AddMarketBid(@RequestBody OrderItemDao bid) {
    	logger.info("MarketController bid name : {}", bid.getName());
    	logger.info("MarketController bid price : {}", bid.getPrice());
    	logger.info("MarketController bid qty : {}", bid.getQty());
    	marketList.AddBid(bid);
  		return new ResponseEntity<String>("success: bid added.", HttpStatus.OK);
  	}

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<String> AddMarketOffer(@RequestBody OrderItemDao offer) {
    	logger.info("MarketController offer name : {}", offer.getName());
    	logger.info("MarketController offer price : {}", offer.getPrice());
    	logger.info("MarketController offer qty : {}", offer.getQty());
    	marketList.AddOffer(offer);
  		return new ResponseEntity<String>("success: offer added.", HttpStatus.OK);
  	}

    @RequestMapping(value = "/offer/get", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<Map<Double, List<Order>>> GetMarketOffer(@RequestBody OrderItemDao offer) {
    	logger.info("MarketController GetMarketOffer name : {}", offer.getName());
    	Map<Double, List<Order>> list = marketList.GetOfferMap(offer);
    	return new ResponseEntity<Map<Double, List<Order>>>(list, HttpStatus.OK);
  	}

    @RequestMapping(value = "/bid/get", method = RequestMethod.POST,consumes="application/json")
  	public ResponseEntity<Map<Double, List<Order>>> GetMarketBid(@RequestBody OrderItemDao bid) {
    	logger.info("MarketController GetMarketBid name : {}", bid.getName());
    	Map<Double, List<Order>> list = marketList.GetBidMap(bid);
    	return new ResponseEntity<Map<Double, List<Order>>>(list, HttpStatus.OK);
  	}
	
}
