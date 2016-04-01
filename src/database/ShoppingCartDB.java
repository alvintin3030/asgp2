package database;

import ict.bean.RequestStatus;
import bean.ShoppingCart;
import bean.ShoppingCartItem;
import bean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartDB {

    public int addRecord(String username, String toyId, int isBuy) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int isSuccess = -1;

        try {
            cnnct = ConnectionUtil.getConnection();

            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"userId\" = ? AND \"bookId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, toyId);
            ResultSet rs = pStmnt.executeQuery();
            if (!rs.next()) {
                preQueryStatement = "INSERT INTO \"ShoppingCart\" (\"username\", \"toyId\", \"isBuy\") VALUES (?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, username);
                pStmnt.setString(2, toyId);
                pStmnt.setInt(3, isBuy);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = 0;
                } else {
                    isSuccess = 2;
                }
            } else {
                isSuccess = 1;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoppingCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public ArrayList<ShoppingCartItem> viewAllBookInCart(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ShoppingCartItem> al = new ArrayList<ShoppingCartItem>();
        ArrayList<ShoppingCart> scal = new ArrayList<ShoppingCart>();

        //Vector<UserInfo> vcb = new Vector<UserInfo>();
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"userId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userId);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                System.out.println("GetCartBook: userId(" + userId + ")");
                ShoppingCart s = new ShoppingCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUserId(rs.getString("userId"));
                s.setBookId(rs.getInt("bookId"));
                s.setIsBuy(rs.getInt("isBuy"));

                scal.add(s);
            }

            if (scal.size() != 0) {
                BookDB bookDB = new BookDB();
                for (int i = 0; i < scal.size(); i++) {
                    al.add(new ShoppingCartItem(bookDB.getBookById(scal.get(i).getBookId()), scal.get(i).getCartId(), scal.get(i).getIsBuy()));
                }
            }

            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }

    public boolean deleteCartItem(String userId, int cartId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"ShoppingCart\" WHERE \"cartId\" = ? AND \"userId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, cartId);
            pStmnt.setString(2, userId);
            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 3) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoppingCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public boolean deleteAllCartItem(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"ShoppingCart\" WHERE \"userId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userId);
            int rowCount = pStmnt.executeUpdate();

            if (rowCount >= 3) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoppingCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public ShoppingCart getShoppingCartByCartId(int cartId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ShoppingCart s = null;

        //Vector<UserInfo> vcb = new Vector<UserInfo>();
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"cartId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, cartId);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                s = new ShoppingCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUserId(rs.getString("userId"));
                s.setBookId(rs.getInt("bookId"));
                s.setIsBuy(rs.getInt("isBuy"));
            }
            return s;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
