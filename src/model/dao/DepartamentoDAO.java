package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Departamento;


public class DepartamentoDAO {

	public void create(String nome, String centroCusto, double orcamento) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO departamento (nome, centrocusto, orcamento) values (?, ?, ?)";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setString(1,nome);
				stmt.setString(2, centroCusto);
				stmt.setDouble(3, orcamento);

				stmt.execute();
			}
		}
	}

	public List<Departamento> read() throws SQLException {
		List<Departamento> departamentos = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM DEPARTAMENTO";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String centroCusto = rs.getString("centrocusto");
					Double orcamento = rs.getDouble("orcamento");
					Departamento departamento = new Departamento(nome, centroCusto, orcamento);
					departamento.setId(id);

					departamentos.add(departamento);
				}
			}

		}
		return departamentos;
	}

	public void update(Departamento departamento) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "UPDATE DEPARTAMENTO SET nome=?, centrocusto=?, orcamento=? WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, departamento.getNome());
				stmt.setString(2, departamento.getCentroCusto());
				stmt.setDouble(3, departamento.getOrcamento());
				stmt.setInt(4, departamento.getId());

				stmt.execute();
			}
		}
	}

	public void delete(int id) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM DEPARTAMENTO WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
		}
	}
}