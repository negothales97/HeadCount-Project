package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Departamento;
import model.vo.Filial;


public class DepartamentoDAO {
	private int id;

	public void create(Departamento departamento) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO DEPARTAMENTO (nome, centrocusto, orcamento) values (?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, departamento.getNome());
				stmt.setString(2, departamento.getCentroCusto());
				stmt.setDouble(3, departamento.getOrcamento());
				
				stmt.execute();
				ResultSet resultSet = stmt.getGeneratedKeys();
		        while (resultSet.next()) {
		            id = resultSet.getInt("id");
		            
		        }
		        resultSet.close();
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