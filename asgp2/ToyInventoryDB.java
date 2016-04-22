package database;

import bean.Toy;
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

public class ToyInventoryDB {

    public Toy getToyById(int tid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Toy t = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Toys\" WHERE \"tid\" = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, tid);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
            }
            return t;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return t;
        }
    }

    //Add record to toy inventory
    public boolean addRecord(Toy t) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "INSERT INTO \"Toys\" (\"Name\", \"Description\", \"Category\", \"Image\", \"Price\", \"Quantity\") VALUES(?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, t.getName());
            pStmnt.setString(2, t.getDescription());
            pStmnt.setString(3, t.getCategory());
            pStmnt.setString(4, t.getImage());
            pStmnt.setFloat(5, t.getPrice());
            pStmnt.setInt(6, t.getQuantity());
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

    //Edit record in toy inventory
    public boolean editRecord(Toy t) { 
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE \"Toys\" SET \"Name\"=?, \"Description\"=?, \"Category\"=?, \"Image\"=?, \"Price\"=?, \"Quantity\"=? WHERE \"tid\"=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, t.getName());
            pStmnt.setString(2, t.getDescription());
            pStmnt.setString(3, t.getCategory());
            pStmnt.setString(4, t.getImage());
            pStmnt.setFloat(5, t.getPrice());
            pStmnt.setInt(6, t.getQuantity());
            pStmnt.setInt(7, t.getTid());
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

	//Delete record from toy inventory
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
                preQueryStatement = "DELETE FROM \"Toys\" WHERE \"tid\"=?";
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

	//sorting when browsing toys? 
    public ArrayList<Toy> getLatestToys() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Toy> al = new ArrayList<Toy>();
		
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Toys\" ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            int i = 0;
            while (rs.next() && i < 10) {
                Toy t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
				
                al.add(t);
                i++;
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }

    public ArrayList<Toy> getToys() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Toy> al = new ArrayList<Toy>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Toys\" ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Toy t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
				
                al.add(t);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }


    public ArrayList<Toy> searchToy(String searchString) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Toy> al = new ArrayList<Toy>();
        searchString = "%" + searchString.toLowerCase() + "%";

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Toys\" WHERE LOWER(\"Name\") LIKE ? OR LOWER(\"Description\") LIKE ? OR LOWER(\"Category\") LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, searchString);
            pStmnt.setString(2, searchString);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Toy t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
				
                al.add(t);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
    
        public static ArrayList<Toy> getToyByCategory(String category) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Toy> al = new ArrayList<Toy>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"Toys\" WHERE \"Category\" = ? ORDER BY \"tid\" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, category);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                Toy t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
				
                al.add(t);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
        
        public ArrayList<Toy> getAllCategory() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Toy> al = new ArrayList<Toy>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT Category FROM \"Toys\"";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            if (rs.next()) {
                Toy t = new Toy();
                t.setTid(rs.getInt("tid"));
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("Description"));
                t.setCategory(rs.getString("Category"));
                t.setImage(rs.getString("Image"));
                t.setPrice(rs.getFloat("Price"));
                t.setQuantity(rs.getInt("Quantity"));
                
                al.add(t);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
}
