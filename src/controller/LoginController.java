package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.dao.LoginDAO;
import view.HeadCount;
import view.Login;

public class LoginController {
	public void validaLogin(String login, String senha) {
		LoginDAO dao = new LoginDAO();
		try {
			boolean acesso = dao.getLogin(login, senha);
			
			if(acesso) {
				new HeadCount();
			}else {
				JOptionPane.showMessageDialog(null, "Login Incorreto");
				new Login();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
