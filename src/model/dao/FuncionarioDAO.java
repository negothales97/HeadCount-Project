package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Filial;
import model.vo.Funcionario;

public class FuncionarioDAO {


	public void create(String nome,  String cpf, String datanasc) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO Funcionario (nome,cpf,datanasc) values (?, ?, ?)";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, nome);
				stmt.setString(2, cpf);
				stmt.setString(3, datanasc);
				
				stmt.execute();
			}
		}
	}

	public List<Funcionario> read() throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM FUNCIONARIO";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int matricula = rs.getInt("matricula");
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String dataNasc = rs.getString("datanasc");
					Funcionario funcionario = new Funcionario(nome, cpf, dataNasc);

					funcionario.setMatricula(matricula);
					funcionarios.add(funcionario);
				}
			}

		}
		return funcionarios;
	}

	public void update(Funcionario funcionario) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "UPDATE FILIAL SET  matricula=?, nome=?, cpf=?, datanasc=? WHERE matricula=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, funcionario.getMatricula());
				stmt.setString(2, funcionario.getNome());
				stmt.setString(3, funcionario.getCpf());
				stmt.setString(4, funcionario.getDatanasc());
				stmt.setInt(5, funcionario.getMatricula());

				stmt.execute();
			}
		}
	}

	public void delete(int matricula) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM FUNCIONARIO WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, matricula);
				stmt.execute();
			}
		}
	}
}