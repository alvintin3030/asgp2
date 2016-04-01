package ict.bean;

public class ShoppingCartItem {

    private Book book;
    private int cartId;
    private int isBuy;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Book book, int cartId, int isBuy) {
        this.book = book;
        this.cartId = cartId;
        this.isBuy = isBuy;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the cartId
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * @param cartId the cartId to set
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * @return the isBuy
     */
    public int getIsBuy() {
        return isBuy;
    }

    /**
     * @param isBuy the isBuy to set
     */
    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

}
