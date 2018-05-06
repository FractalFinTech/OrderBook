package fractalfintech.orderbook;

public class OrderItemDao {

    private String name;
    private double price;
    private int qty;
    private String type;
    private String transactionType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTranType() {
        return transactionType;
    }

    public void setTranType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
