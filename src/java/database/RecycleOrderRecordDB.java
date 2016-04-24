package database;

import bean.RecycleOrderRecord;
import bean.RecycleCart;
import bean.User;
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

public class RecycleOrderRecordDB {

    public boolean addRecord(RecycleCart s) { 
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"RecycleOrderRecords\" (\"Username\", \"ToyID\") VALUES(?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, s.getUsername());
            pStmnt.setInt(2, s.getToyId());
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

    public ArrayList<RecycleOrderRecord> getAllRecord() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleOrderRecord> al = new ArrayList<RecycleOrderRecord>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleOrderRecords\" ORDER BY \"OrderDatetime\"ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleOrderRecord record = new RecycleOrderRecord();
                record.setOrderID(rs.getInt("OrderID"));
                record.setUsername(rs.getString("Username"));
                record.setToyID(rs.getInt("ToyID"));
                record.setOrderDatetime(rs.getString("OrderDatetime"));

                al.add(record);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
        
    public RecycleOrderRecord getOrderRecord(int orderid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        RecycleOrderRecord or = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"OrderRecords\" WHERE \"orderid\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, orderid);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                or = new RecycleOrderRecord();
                or.setOrderID(rs.getInt("Orderid"));
                or.setUsername(rs.getString("Username"));
                or.setToyID(rs.getInt("ToyID"));
                or.setOrderDatetime(rs.getString("OrderDatetime"));
                
            }
            return or;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return or;
        }
    }
     
    public ArrayList<RecycleOrderRecord> getUserOrderHistory(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleOrderRecord> al = new ArrayList<RecycleOrderRecord>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"OrderRecords\" WHERE \"Username\"=? ORDER BY \"OrderDatetime\"ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleOrderRecord record = new RecycleOrderRecord();
                record.setOrderID(rs.getInt("OrderID"));
                record.setUsername(rs.getString("Username"));
                record.setToyID(rs.getInt("ToyID"));
                record.setOrderDatetime(rs.getString("OrderDatetime"));
                al.add(record);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
}	