package bean;

import java.sql.Date;

public class OrderRecord {
    private int orderID;
    private String username;
    private int toyID;
    private String orderDatetime;
    private int quantity;
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getToyID() {
        return toyID;
    }

    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    public String getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}

