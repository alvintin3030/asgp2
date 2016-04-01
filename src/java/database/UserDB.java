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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB {

    public boolean addRecord(String username, String password, String email, float credit, int userGroupId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"Users\" (\"username\",\"password\",\"email\",\"credit\",\"userGroupId\") VALUES(?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, email);
            pStmnt.setFloat(4, credit);
            pStmnt.setInt(5, userGroupId);
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
            String preQueryStatement = "UPDATE \"Users\" SET \"credit\" = ? WHERE \"username\" = ?";
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

    public boolean editRecord(String username, String password, String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"Users\" SET \"password\" = ?, \"email\" = ? WHERE \"username\" = ? AND \"password\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, password);
            pStmnt.setString(2, email);
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
            String preQueryStatement = "SELECT * FROM \"Users\" WHERE \"username\" = ? AND \"password\" = ?";
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

    public User getUserInfo(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Users\" WHERE \"username\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCredit(rs.getFloat("credit"));
                user.setUserGroupId(rs.getInt("userGroupId"));

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
                user.setCredit(rs.getFloat("credit"));
                user.setUserGroupId(rs.getInt("userGroupId"));

                al.add(user);
            }
            return al;
        } catch (Exception ex) {
            return al;
        }
    }
}