package bean;

public class ShoppingCartItem {

    private Toy toy;
    private int cartId;
    private int isBuy;

    public ShoppingCartItem(Toy toy, int cartId, int isBuy) {
        this.toy = toy;
        this.cartId = cartId;
        this.isBuy = isBuy;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public int getCartId() {
        return cartId;
    }
    
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

}
