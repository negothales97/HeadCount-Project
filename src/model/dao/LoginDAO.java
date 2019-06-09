package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.connection.DAOException;
import model.connection.Database;

public class LoginDAO {
	private final String LOGIN	= "SELECT * FROM USERS WHERE LOGIN = ? and SENHA = ?";
	
	public boolean getLogin(String login, String senha) throws DAOException {
		boolean acesso = false;
		try(Connection con = Database.getInstance().getConnection()){
			PreparedStatement stmt = con.prepareStatement(LOGIN);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				String log = rs.getString("login");	
				String pass = rs.getString("senha");
				if (login.equals(log) && senha.equals(pass)) {
					acesso = true;
				}
			}
			return acesso;
		}catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "Erro ao logar: " +e.getMessage());
		}
		return false;
	}
}
