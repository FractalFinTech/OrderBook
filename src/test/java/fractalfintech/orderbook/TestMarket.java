package fractalfintech.orderbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.Exception;

import java.util.List;
import java.util.LinkedList;

import fractalfintech.orderbook.OrderBook;
import fractalfintech.orderbook.Order;

public class TestMarket
{
    private OrderBook market;

    @Before
    public void initMarket()
    {
        market = new OrderBook("Test");
    }

    /*******************************************
     *
     *   addBid Tests
     *
     *******************************************/

    @Test
    public void addNewBidShouldCorrectlyAddNewBid()
    {
        initMarket();
        assertTrue(market.getBidMap().isEmpty());
        market.addBid(12.0, 1);
        assertTrue(market.getBidMap().containsKey(12.0));
    }

    @Test
    public void addDuplicateBidPriceShouldCorrectlyAddNewBid()
    {
        initMarket();
        assertTrue(market.getBidMap().isEmpty());
        market.addBid(12.0, 1);
        market.addBid(12.0, 2);

        // Checks to see if corresponding elements have same quantities.
        assertEquals(1, market.getBidMap().get(12.0).get(0).getQuantity());
        assertEquals(2, market.getBidMap().get(12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   addOffer Tests
     *
     *******************************************/

    @Test
    public void addNewOfferShouldCorrectlyAddNewOffer()
    {
        initMarket();
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        assertTrue(market.getOfferMap().containsKey(12.0));
    }

    @Test
    public void addDuplicateOfferPriceShouldCorrectlyAddNewOffer()
    {
        initMarket();
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        market.addOffer(12.0, 2);

        // Checks to see if corresponding elements have same quantities.
        assertEquals(1, market.getOfferMap().get(12.0).get(0).getQuantity());
        assertEquals(2, market.getOfferMap().get(12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   getBucket Test
     *
     *******************************************/

    @Test
    public void getBucketShouldReturnCorrectList()
    {
        initMarket();
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        market.addOffer(12.0, 2);

        // Checks to see if corresponding bucket elements have same quantities.
        assertEquals(1, market.getBucket(market.getOfferMap(), 12.0).get(0).getQuantity());
        assertEquals(2, market.getBucket(market.getOfferMap(), 12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   matchOrders Tests
     *
     *******************************************/

    @Test
    public void BidQuantityShouldCorrectlyDecrementWhenGreaterThanOfferQuantity()
    {
        initMarket();
        market.addOffer(12.0, 6);
        market.addBid(12.0, 9);
        market.matchOrders();
        assertEquals(3, market.getBidMap().get(12.0).get(0).getQuantity());  // Bid correctly decremented
        assertTrue(market.getOfferMap().get(12.0).isEmpty());  // Offer correctly closed
    }

    @Test
    public void OfferQuantityShouldCorrectlyDecrementWhenGreaterThanBidQuantity()
    {
        initMarket();
        market.addBid(12.0, 5);
        market.addOffer(12.0, 10);
        market.matchOrders();
        assertEquals(5, market.getOfferMap().get(12.0).get(0).getQuantity());  // Offer correctly decremented
        assertTrue(market.getBidMap().get(12.0).isEmpty());  // Bid correctly closed
    }

    @Test
    public void BothQuantitiesEqualShouldCorrectlyRemoveBoth()
    {
        initMarket();
        market.addBid(12.0, 5);
        market.addOffer(12.0, 5);
        market.matchOrders();
        assertTrue(market.getBidMap().get(12.0).isEmpty());   // Bid correctly closed
        assertTrue(market.getOfferMap().get(12.0).isEmpty()); // Offer correctly closed
    }

    @Test
    public void BidWithValueAndNoOffersShouldStayTheSame()
    {
        initMarket();
        market.addBid(12.0, 5);
        market.matchOrders();
        assertEquals(5, market.getBidMap().get(12.0).get(0).getQuantity());   // Bid still has same value
        assertTrue(market.getOfferMap().get(12.0) == null); // Offer still null
    }

    @Test
    public void OfferWithValueAndNoBidsShouldStayTheSame()
    {
        initMarket();
        market.addOffer(12.0, 5);
        market.matchOrders();
        assertEquals(5, market.getOfferMap().get(12.0).get(0).getQuantity());   // Offer still has same value
        assertTrue(market.getBidMap().get(12.0) == null); // Bid still null
    }

    @Test
    public void OfferPriceHigherThanBidPriceShouldStayTheSame()
    {
        initMarket();
        market.addOffer(12.0, 7);
        market.addBid(6.0, 5);
        market.matchOrders();
        assertEquals(7, market.getOfferMap().get(12.0).get(0).getQuantity());   // Offer still has same value
        assertEquals(5, market.getBidMap().get(6.0).get(0).getQuantity());   // Bid still has same value
    }
}
