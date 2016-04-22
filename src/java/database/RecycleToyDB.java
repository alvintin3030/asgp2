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
                rt.setIsApproved(rs.getInt("isApproved"));
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
            String preQueryStatement = "INSERT INTO \"RecycleToy\" (\"Name\", \"Description\", \"Category\", \"Image\", \"price\", \"DonatedBy\", \"IsApproved\") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, rt.getName());
            pStmnt.setString(2, rt.getDescription());
            pStmnt.setString(3, rt.getCategory());
            pStmnt.setString(4, rt.getImage());
            pStmnt.setFloat(5, rt.getPrice());
            pStmnt.setString(6, rt.getDonatedBy());
            pStmnt.setInt(7, rt.getIsApproved());
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
            String preQueryStatement = "UPDATE \"RecycleToy\" SET \"Name\"=?, \"Description\"=?, \"Category\"=?, \"Image\"=?, \"price\"=?, \"DonatedBy\"=?, \"IsApproved\" WHERE \"tid\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, rt.getName());
            pStmnt.setString(2, rt.getDescription());
            pStmnt.setString(3, rt.getCategory());
            pStmnt.setString(4, rt.getImage());
            pStmnt.setFloat(5, rt.getPrice());
            pStmnt.setString(6, rt.getDonatedBy());
            pStmnt.setInt(7, rt.getIsApproved());
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
		rt.setIsApproved(rs.getInt("isApproved"));	
                
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
                rt.setIsApproved(rs.getInt("isApproved"));
				
                al.add(rt);
                i++;
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
     
     public boolean approveRecycle(int tid, int isApproved) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"RecycleToy\" SET \"IsApproved\" = ? WHERE \"Tid\" = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            RecycleToy rt = new RecycleToy();
            pStmnt.setInt(1, rt.getIsApproved());
            pStmnt.setInt(2, rt.getTid());
            
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
    
      public  ArrayList<RecycleToy> getRToyByCategory(String category) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<RecycleToy> al = new ArrayList<RecycleToy>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"RecycleToy\" WHERE \"Category\" = ? ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, category);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                RecycleToy t = new RecycleToy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setDonatedBy(rs.getString("donatedBy"));
				
                al.add(t);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
        
        public  ArrayList<String> getAllCategory() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<String> s=new ArrayList<String>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT DISTINCT Category FROM \"RecycleToy\"";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            
            while (rs.next()) {
                
                s.add(rs.getString("Category"));
                
            }
            
            System.out.println("S: "+s);
            return s;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return s;
        }
    }
}
