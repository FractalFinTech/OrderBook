package fractalfintech.orderbook;

import fractalfintech.orderbook.Market;
import fractalfintech.orderbook.Order;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MarketController
{
    private Market market;

    // Initializes marketplace
    public MarketController()
    {
        market = new Market();
    }

    @RequestMapping("/")
  	public String index() {
  			return "Greetings from Spring Boot!";
  	}

  }
