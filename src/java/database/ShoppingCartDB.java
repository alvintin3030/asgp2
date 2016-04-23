package database;

import bean.ShoppingCart;
import bean.Toy;
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

    public int addRecord(String username, int toyId, int quantity) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
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
                preQueryStatement = "INSERT INTO \"ShoppingCart\" (\"username\", \"toyId\", \"quantity\") VALUES (?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, username);
                pStmnt.setInt(2, toyId);
                pStmnt.setInt(3, quantity);
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
    
    public ArrayList<ShoppingCart> getShoppingCart(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<ShoppingCart> al = new ArrayList<ShoppingCart>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"USERNAME\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                ShoppingCart s = new ShoppingCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUsername(rs.getString("username"));
                s.setToyId(rs.getInt("toyId"));
                s.setQuantity(rs.getInt("quantity"));	
                al.add(s);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }

    public boolean deleteCartItem(String username, int toyId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"ShoppingCart\" WHERE \"toyId\" = ? AND \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, toyId);
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
    
    public boolean modifyQuantity(String username, int toyId, int newQuantity) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"ShoppingCart\" SET \"Quantity\" = ? WHERE \"toyId\" = ? AND \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, newQuantity);
            pStmnt.setInt(2, toyId);
            pStmnt.setString(3, username);
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
}
