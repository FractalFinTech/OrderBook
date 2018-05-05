package fractalfintech.orderbook;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fractalfintech.orderbook.Market;
import fractalfintech.orderbook.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderbookApplicationTests {

	private Market market;

	@Before
	public void initMarket()
	{
			market = new Market();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void addNewBidShouldCorrectlyAddNewBid2()
	{
			initMarket();
			assertTrue(market.getBidMap().isEmpty());
			market.addBid(12.0, 1);
			assertTrue(market.getBidMap().containsKey(12.0));
	}

}
