package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {
		Connection con = Database.getConnection();
		Statement statement = con.createStatement();
		boolean resultado = statement.execute("select * from filial");
		ResultSet result = statement.getResultSet();
		if (resultado) {
			while(result.next()) {
				int id = result.getInt("id");
			    String nome = result.getString("nome");
			    String cnpj = result.getString("cnpj");
			    System.out.println(id);
			    System.out.println(nome);
			    System.out.println(cnpj);
			}
		}
		result.close();
		statement.close();
		
		con.close();
	}
}
