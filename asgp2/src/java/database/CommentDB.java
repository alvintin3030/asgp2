package database;

import bean.Comment;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDB {
    
    public int addComment(int toyID, int subComment, String username, String content, Date datetime, int isMainComment) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int isSuccess = -1;

        try {
            cnnct = ConnectionUtil.getConnection();

            String preQueryStatement = "SELECT * FROM \"Comment\" WHERE \"username\" = ? AND \"toyID\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setInt(2, toyID);
            ResultSet rs = pStmnt.executeQuery();
            if (!rs.next()) {
                preQueryStatement = "INSERT INTO \"Comment\" (\"toyId\", \"subComment\", \"username\",  \"content\", \"datetime\", \"isMainComment\") VALUES (?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, toyID);
                pStmnt.setInt(2, subComment);
                pStmnt.setString(3, username);
                pStmnt.setString(4, content);
                pStmnt.setDate(5, datetime);
                pStmnt.setInt(6, isMainComment);
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
    
    public ArrayList<Comment> getMainComment() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Comment> al = new ArrayList<Comment>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Comment\" WHERE \"IsMainComment\" = '1'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Comment s = new Comment();
                s.setToyID(rs.getInt("toyID"));
                s.setSubComment(rs.getInt("subComment"));
                s.setUsername(rs.getString("username"));
                s.setContent(rs.getString("content"));
                s.setDatetime(rs.getDate("datetime"));
                s.setIsMainComment(rs.getInt("isMainComment"));	
                al.add(s);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
    
    public boolean deleteComment(int commentID) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE FROM \"Comment\" WHERE \"commnetID\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, commentID);
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
    
    public ArrayList<Comment> getSubComment() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Comment> al = new ArrayList<Comment>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Comment\" WHERE \"IsMainComment\" = '0'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Comment s = new Comment();
                s.setToyID(rs.getInt("toyID"));
                s.setSubComment(rs.getInt("subComment"));
                s.setUsername(rs.getString("username"));
                s.setContent(rs.getString("content"));
                s.setDatetime(rs.getDate("datetime"));
                s.setIsMainComment(rs.getInt("isMainComment"));	
                al.add(s);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
}