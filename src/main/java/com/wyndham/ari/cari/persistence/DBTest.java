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
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("insert into ARI_DELIVERY_HEADER values ( 1, '1','1','1',1,"+new Timestamp(1412229952)+",1,'',1,1,'N','1','1',1,'','','')");
			stmt.close();
			} else 
				
			{
				System.out.println("Error");
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
