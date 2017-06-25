package it.polito.esame.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;



public class DBConnect {

	private static String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?user=root";
	private static DataSource ds;

	
	
	public static Connection getConnection() {
		
		if(ds==null){
			// crea il DataSource
						try {
							ds = DataSources.pooledDataSource(
									DataSources.unpooledDataSource(jdbcUrl));
						} catch (SQLException e) {
							e.printStackTrace();
							System.exit(1);
						}
		}
		
		
		
		try {
			Connection conn = ds.getConnection();
			return conn ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

}
