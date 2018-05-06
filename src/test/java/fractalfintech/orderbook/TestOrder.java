package fractalfintech.orderbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fractalfintech.orderbook.Order;

public class TestOrder
{
    private Order order;

    @Test
    public void getPriceShouldReturnCorrectPrice()
    {
        order = new Order(12.0, 1);
                                              // margin of error
        assertEquals(12.0, order.getPrice(), .00000000000001);
    }

    @Test
    public void setPriceShouldCorrectlySetPrice()
    {
        order = new Order(12.0, 1);
        order.setPrice(15.0);
        assertEquals(15.0, order.getPrice(), .00000000000001);
    }

    @Test
    public void getQuantityShouldReturnCorrectQuantity()
    {
        order = new Order(12.0, 1);
        assertEquals(1, order.getQuantity());
    }

    @Test
    public void setQuantityShouldCorrectlySetQuantity()
    {
        order = new Order(12.0, 1);
        order.setQuantity(5);
        assertEquals(5, order.getQuantity());
    }

    @Test
    public void toStringShouldReturnCorrectStringFormat()
    {
        order = new Order(12.0, 1);
        assertEquals("12.0 1", order.toString());
    }
}
