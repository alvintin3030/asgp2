package database;

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

public class UserDB {

    public boolean addRecord(String username, String password, String email, String phone, String address, int groupId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"Users\" (\"username\",\"password\",\"email\",\"phone\",\"address\",\"credit\",\"groupid\") VALUES(?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, email);
            pStmnt.setString(4, phone);
            pStmnt.setString(5, address);
            pStmnt.setFloat(6, 0);
            pStmnt.setInt(7, groupId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
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
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuccess;
    }

    public boolean updateCredit(String username, float newCredit) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"Users\" SET \"credit\"=? WHERE \"username\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setFloat(1, newCredit);
            pStmnt.setString(2, username);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
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
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuccess;
    }

    public boolean editRecord(String username, String password, String email, String phone, String address) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"Users\" SET \"password\"=?, \"email\"=?, \"phone\"=?, \"address\"=? WHERE \"username\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, password);
            pStmnt.setString(2, email);
            pStmnt.setString(3, phone);
            pStmnt.setString(4, address);
            pStmnt.setString(5, username);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
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
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuccess;
    }

    public boolean isValidUser(String username, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Users\" WHERE \"username\"=? AND \"password\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean isDuplicateUser(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Users\" WHERE \"username\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public User getUserInfo(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Users\" WHERE \"username\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setAddress(rs.getString("address"));
                user.setCredit(rs.getFloat("credit"));
                user.setGroupId(rs.getInt("groupId"));

                return user;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<User> getUsers() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<User> al = new ArrayList<User>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Users\"";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setAddress(rs.getString("address"));
                user.setCredit(rs.getFloat("credit"));
                user.setGroupId(rs.getInt("groupId"));
                
                al.add(user);
            }
            return al;
        } catch (Exception ex) {
            return al;
        }
    }
}
