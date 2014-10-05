package com.wyndham.ari.cari.persistence;

import java.sql.*;
import com.wyndham.ari.dao.*;

public class DBTest {
	public static void main(String[] args) {

		try {
			//Class.forName("org.postgresql.Driver");
			Connection connection = null;
			if  (args.length >= 3 ){ 
				System.out.print(args[0]+args[1]+args[2]);
			connection = DriverManager.getConnection(
					args[0],
					args[1], args[2]);
			
			connection.setAutoCommit(true);
			PreparedStatement insert = null;
			 String insertstmt = " insert into ARI_DELIVERY_HEADER(REQUEST_ID, BRAND_ID,PROPERTY_ID,MSG_STATUS_ID, MSG_TIMESTAMP,MSG_SUBJECT_ID) values (?,?,?,?,?,?)";
			 insert = connection.prepareStatement(insertstmt);
			 insert.setInt(1, 1);
			 insert.setString(2, "1");
			 insert.setString(3, "1");
			 insert.setInt(4, 1);
			 insert.setTimestamp(5, new Timestamp(1412480813));
			 insert.setInt(6, 1);
			 insert.execute();
			 insert.close();
			 
			} else 
				
			{
				System.out.println("Error");
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
