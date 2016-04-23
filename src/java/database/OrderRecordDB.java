package database;

import bean.OrderRecord;
import bean.OrderRecordItem;
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

public class OrderRecordDB {

	public int addRecord(int userId, int cartId, Date orderDatetime, String orderStatus) { 
			Connection cnnct = null;
			PreparedStatement pStmnt = null;
			int isSuccess = -1;

			try {
				cnnct = ConnectionUtil.getConnection();

				String preQueryStatement = "SELECT * FROM \"OrderRecord\" WHERE \"userId\" = ?";
				pStmnt = cnnct.prepareStatement(preQueryStatement);
				pStmnt.setInt(1, userId);
				ResultSet rs = pStmnt.executeQuery();
				if (!rs.next()) {
					preQueryStatement = "INSERT INTO \"OrderRecord\" (\"userId\", \"cartId\", \"orderDatetime\", \"orderStatus\") VALUES (?,?,?,?)";
					pStmnt = cnnct.prepareStatement(preQueryStatement);
					pStmnt.setInt(1, userId);
					pStmnt.setInt(2, cartId);
					pStmnt.setDate(3, orderDatetime);
					pStmnt.setString(4, orderStatus);
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
				Logger.getLogger(OrderRecordDB.class.getName()).log(Level.SEVERE, null, ex);
			}
			return isSuccess;
		}
	
	public ArrayList<OrderRecord> getAllRecord() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<OrderRecord> al = new ArrayList<OrderRecord>();

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM \"OrderRecord\" ORDER BY \"orderDatetime\"ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                OrderRecord record = new OrderRecord();
                record.setUserId(rs.getInt("userId"));
                record.setCartId(rs.getInt("cartId"));
                record.setOrderDateTime(rs.getDate("orderDatetime"));
                record.setOrderStatus(rs.getString("orderStatus"));

                al.add(record);
            }
            return al;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return al;
        }
    }
        
    }	