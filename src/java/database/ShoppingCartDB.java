package database;

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

    public int addRecord(String username, int toyId, boolean isBuy) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int isSuccess = -1;

        try {
            cnnct = ConnectionUtil.getConnection();

            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"username\" = ? AND \"toyId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setInt(2, toyId);
            ResultSet rs = pStmnt.executeQuery();
            if (!rs.next()) {
                preQueryStatement = "INSERT INTO \"ShoppingCart\" (\"username\", \"toyId\", \"isBuy\") VALUES (?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, username);
                pStmnt.setInt(2, toyId);
                pStmnt.setBoolean(3, isBuy);
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

    public ArrayList<ShoppingCartItem> viewAllBookInCart(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ShoppingCartItem> al = new ArrayList<ShoppingCartItem>();
        ArrayList<ShoppingCart> scal = new ArrayList<ShoppingCart>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                System.out.println("GetCartToy: username(" + username + ")");
                ShoppingCart s = new ShoppingCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUsername(rs.getString("username"));
                s.setToyId(rs.getInt("toyId"));
                s.setIsBuy(rs.getBoolean("isBuy"));

                scal.add(s);
            }

            if (scal.size() != 0) {
                ToyInventoryDB toyDB = new ToyInventoryDB();
                for (int i = 0; i < scal.size(); i++) {
                    al.add(new ShoppingCartItem(toyDB.getToyById(scal.get(i).getToyId()), scal.get(i).getCartId(), scal.get(i).getIsBuy()));
                }
            }

            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }

    public boolean deleteCartItem(String username, int cartId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"ShoppingCart\" WHERE \"cartId\" = ? AND \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, cartId);
            pStmnt.setString(2, username);
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

    public boolean deleteAllCartItem(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"ShoppingCart\" WHERE \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
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

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"cartId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, cartId);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                s = new ShoppingCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUsername(rs.getString("username"));
                s.setToyId(rs.getInt("toyId"));
                s.setIsBuy(rs.getBoolean("isBuy"));
            }
            return s;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
