package com.wyndham.ari.cari.persistence;

import java.sql.*;
import com.wyndham.ari.dao.*;

public class DBTest {
	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
					args[0],
					args[1], args[2]);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("insert into ARI_DELIVER_HEADER values ( 1, '1','1','1',1,'',1,'',1,1,'N','1','1',1,'','','')");
			stmt.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
