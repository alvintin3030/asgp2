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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDB {
    
    public boolean addComment(Comment c) { //return errorCode: 0=success, 1=fail(book exist in cart), 2=fail(error)
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"Comment\" (\"ToyID\", \"SubComment\", \"Username\", \"Content\", \"IsSubComment\") VALUES(?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, c.getToyID());
            pStmnt.setInt(2, c.getSubComment());
            pStmnt.setString(3, c.getUsername());
            pStmnt.setString(4, c.getContent());
            pStmnt.setInt(5, c.getIsSubComment());
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
            Logger.getLogger(ToyInventoryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }
    
    public ArrayList<Comment> getMainComment() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Comment> al = new ArrayList<Comment>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Comment\" WHERE \"IsSubComment\" = '0' ORDER BY \"Datetime\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                
                Comment s = new Comment();
                s.setCommentID(rs.getInt("commentID"));
                s.setToyID(rs.getInt("toyID"));
                s.setSubComment(rs.getInt("subComment"));
                s.setUsername(rs.getString("username"));
                s.setContent(rs.getString("content"));
                s.setDatetime(rs.getString("datetime"));
                s.setIsSubComment(rs.getInt("isSubComment"));	
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
            String preQueryStatement = "DELETE FROM \"Comment\" WHERE \"commentID\" = ?";
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
    
    public ArrayList<Comment> getSubComment(int subComment) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Comment> al = new ArrayList<Comment>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Comment\" WHERE \"IsSubComment\" = '1' AND \"SubComment\" = ? ORDER BY \"Datetime\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, subComment);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentID(rs.getInt("commentID"));
                c.setToyID(rs.getInt("toyID"));
                c.setSubComment(rs.getInt("subComment"));
                c.setUsername(rs.getString("username"));
                c.setContent(rs.getString("content"));
                c.setDatetime(rs.getString("datetime"));
                c.setIsSubComment(rs.getInt("isSubComment"));	
                al.add(c); 
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
}