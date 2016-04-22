package bean;

public class ShoppingCartItem {

    private Toy toy;
    private int cartId;

    public ShoppingCartItem(Toy toy, int cartId) {
        this.toy = toy;
        this.cartId = cartId;
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

}
