package database;

import bean.RecycleToy;
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

public class RecycleToyDB {
    public RecycleToy getRToyById(int tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        RecycleToy rt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleToy\" WHERE \"tid\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, tid);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                rt = new RecycleToy();
                rt.setTid(rs.getInt("tid"));
                rt.setName(rs.getString("name"));
                rt.setDescription(rs.getString("description"));
                rt.setCategory(rs.getString("category"));
                rt.setImage(rs.getString("image"));
                rt.setPrice(rs.getFloat("price"));
                rt.setDonatedBy(rs.getString("donatedBy"));
            }
            return rt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return rt;
        }
    }
    
    public boolean addRecord(RecycleToy rt) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"RecycleToy\" (\"Name\", \"Description\", \"Category\", \"Image\", \"price\", \"DonatedBy\") VALUES(?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, rt.getName());
            pStmnt.setString(2, rt.getDescription());
            pStmnt.setString(3, rt.getCategory());
            pStmnt.setString(4, rt.getImage());
            pStmnt.setFloat(5, rt.getPrice());
            pStmnt.setString(6, rt.getDonatedBy());
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
    
    public boolean editRecord(RecycleToy rt) { 
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"RecycleToy\" SET \"Name\"=?, \"Description\"=?, \"Category\"=?, \"Image\"=?, \"price\"=?, \"DonatedBy\"=? WHERE \"tid\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, rt.getName());
            pStmnt.setString(2, rt.getDescription());
            pStmnt.setString(3, rt.getCategory());
            pStmnt.setString(4, rt.getImage());
            pStmnt.setFloat(5, rt.getPrice());
            pStmnt.setString(6, rt.getDonatedBy());
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
    
    public boolean deleteRecord(int tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"ShoppingCart\" WHERE \"tid\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, tid);
            ResultSet rs = pStmnt.executeQuery();
            int existedRecord = 0;

            if (rs.next()) {
                existedRecord += 1;
            }
			
			
            if (existedRecord == 0) {
                preQueryStatement = "DELETE FROM \"RecycleToy\" WHERE \"tid\"=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, tid);
                int rowCount = pStmnt.executeUpdate();

                if (rowCount >= 1) {
                    isSuccess = true;
                }
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
    
    public ArrayList<RecycleToy> getRToys() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleToy> al = new ArrayList<RecycleToy>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleToy\" ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleToy rt = new RecycleToy();
                rt.setTid(rs.getInt("tid"));
                rt.setName(rs.getString("name"));
                rt.setDescription(rs.getString("description"));
                rt.setCategory(rs.getString("category"));
                rt.setImage(rs.getString("image"));
                rt.setPrice(rs.getFloat("price"));
                rt.setDonatedBy(rs.getString("donatedBy"));
				
                al.add(rt);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
    
    public ArrayList<RecycleToy> searchRToy(String searchString) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleToy> al = new ArrayList<RecycleToy>();
        searchString = "%" + searchString.toLowerCase() + "%";

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleToy\" WHERE LOWER(\"Name\") LIKE ? OR LOWER(\"Description\") LIKE ? OR LOWER(\"Category\") LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, searchString);
            pStmnt.setString(2, searchString);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleToy rt = new RecycleToy();
                rt.setTid(rs.getInt("tid"));
                rt.setName(rs.getString("name"));
                rt.setDescription(rs.getString("description"));
                rt.setCategory(rs.getString("category"));
                rt.setImage(rs.getString("image"));
                rt.setPrice(rs.getFloat("price"));
                rt.setDonatedBy(rs.getString("donatedBy"));
				
                al.add(rt);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
    
     public ArrayList<RecycleToy> getLatestRToys() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleToy> al = new ArrayList<RecycleToy>();
		
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleToy\" ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            int i = 0;
            while (rs.next() && i < 10) {
                RecycleToy rt = new RecycleToy();
                rt.setTid(rs.getInt("tid"));
                rt.setName(rs.getString("name"));
                rt.setDescription(rs.getString("description"));
                rt.setCategory(rs.getString("category"));
                rt.setImage(rs.getString("image"));
                rt.setPrice(rs.getFloat("price"));
                rt.setDonatedBy(rs.getString("donatedBy"));
				
                al.add(rt);
                i++;
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
}
