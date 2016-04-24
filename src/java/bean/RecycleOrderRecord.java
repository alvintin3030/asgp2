/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Jason
 */
public class RecycleOrderRecord {
     private int orderID;
    private String username;
    private int toyID;
    private String orderDatetime;

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the toyID
     */
    public int getToyID() {
        return toyID;
    }

    /**
     * @param toyID the toyID to set
     */
    public void setToyID(int toyID) {
        this.toyID = toyID;
    }

    /**
     * @return the orderDatetime
     */
    public String getOrderDatetime() {
        return orderDatetime;
    }

    /**
     * @param orderDatetime the orderDatetime to set
     */
    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

}
