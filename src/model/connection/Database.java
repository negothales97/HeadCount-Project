package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection con;
	private String url = "jdbc:hsqldb:hsql://localhost/head-count-project";
	private String user = "SA";
	private String pass = "";
	
	private static Database instancia =null;
	
	private Database() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Driver criado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() { 
		if (instancia == null) { 
			instancia = new Database();
		}
		return instancia;
	}
	
	public Connection getConnection() throws SQLException{
		if (con == null || con.isClosed()) { 
			 con = DriverManager.getConnection(url, user, pass);
		}
		return con;
	}
	
}