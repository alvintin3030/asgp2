package database;

import bean.RecycleCart;
import bean.RecycleToy;
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

public class RecycleCartDB {

    public int addRecord(String username, int toyId) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int isSuccess = -1;

        try {
            cnnct = ConnectionUtil.getConnection();

            String preQueryStatement = "SELECT * FROM \"RecycleCart\" WHERE \"username\" = ? AND \"toyId\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setInt(2, toyId);
            ResultSet rs = pStmnt.executeQuery();
            if (!rs.next()) {
                preQueryStatement = "INSERT INTO \"RecycleCart\" (\"username\", \"toyId\") VALUES (?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, username);
                pStmnt.setInt(2, toyId);
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
    
    public ArrayList<RecycleCart> getRecycleCart(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleCart> al = new ArrayList<RecycleCart>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleCart\" WHERE \"USERNAME\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleCart s = new RecycleCart();
                s.setCartId(rs.getInt("cartId"));
                s.setUsername(rs.getString("username"));
                s.setToyId(rs.getInt("toyId"));
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
            String preQueryStatement = "DELETE FROM \"RecycleCart\" WHERE \"toyId\" = ? AND \"username\" = ?";
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
            String preQueryStatement = "UPDATE \"RecycleCart\" SET \"Quantity\" = ? WHERE \"toyId\" = ? AND \"username\" = ?";
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
            String preQueryStatement = "DELETE FROM \"RecycleCart\" WHERE \"username\" = ?";
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
