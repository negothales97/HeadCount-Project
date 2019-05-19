package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Dependente;

public class DependenteDAO {
	private int id;

	public void create(Dependente dependente) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO DEPENDENTE (nome, cpf, datanasc, funcionario_id) values (?, ?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
				stmt.setInt(4, dependente.getFuncionario());
				
				stmt.execute();
			}
		}
	}

	public List<Dependente> read(String search) throws SQLException {
		List<Dependente> dependentes = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM Dependente where nome like ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, "%"+search+"%");
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String datanasc = rs.getString("datanasc");
					int funcionario_id= rs.getInt("funcionario_id");
					Dependente dependente = new Dependente(nome, cpf, datanasc, funcionario_id);
					
					dependente.setid(id);
					dependentes.add(dependente);
				}
			}

		}
		return dependentes;
	}

	public void update(Dependente dependente) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "UPDATE DEPENDENTE SET nome=?, cpf=?, datanasc=?, funcionario_id=? WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
				stmt.setInt(4, dependente.getFuncionario());
				stmt.setInt(5, dependente.getid());

				stmt.execute();
			}
		}
	}

	public void delete(int id) throws SQLException {
		System.out.println(id);
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM DEPENDENTE WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
		}
	}
}
