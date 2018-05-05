package fractalfintech.orderbook;

public class Order
{
    private double price;
    private int quantity;

    public Order(double price, int quantity)
    {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double newPrice)
    {
        this.price = newPrice;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public void setQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }

    public String toString()
    {
        return this.price + " " + this.quantity;
    }
}
