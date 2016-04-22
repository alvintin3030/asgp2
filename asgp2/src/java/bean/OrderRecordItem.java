package bean;

public class OrderRecordItem {
    private OrderRecord orderRecord;
    private Toy toy;

    public OrderRecord getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(OrderRecord orderRecord) {
        this.orderRecord = orderRecord;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

}