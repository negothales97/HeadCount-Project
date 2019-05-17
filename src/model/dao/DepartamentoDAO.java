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

	public void create(String nome, String CentroCusto, Float Orcamento) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO FILIAL (nome, CentroCusto, Orcamento) values (?, ?, ?)";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setString(1,nome);
				stmt.setString(2, CentroCusto);
				stmt.setFloat(3, Orcamento);

				stmt.execute();
			}
		}
	}

	public List<Departamento> read(String nomeSearch) throws SQLException {
		List<Departamento> departamentos = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM DEPARTAMENTO WHERE nome LIKE %?%";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, nomeSearch);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String centrocusto = rs.getString("CentroCusto");
					Float orcamento = rs.getFloat("Orcamento");
					Departamento departamento = new Departamento();

					departamento.setId(id);
					departamento.setNome(nome);
					departamento.setCentroCusto(centrocusto);
					departamento.setOrcamento(orcamento);

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
				stmt.setFloat(3, departamento.getOrcamento());
				stmt.setInt(4, departamento.getId());

				stmt.execute();
			}
		}
	}

	public void delete(Departamento departamento) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM DEPARTAMENTO WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, departamento.getId());
				stmt.execute();
			}
		}
	}
}