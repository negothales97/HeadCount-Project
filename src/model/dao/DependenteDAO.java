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

<<<<<<< HEAD
			String sql = "INSERT INTO FILIAL (nome, cnpj, insc_estadual) values (?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCnpj());
				stmt.setString(3, dependente.getInscEstadual());
				
				stmt.execute();
				ResultSet resultSet = stmt.getGeneratedKeys();
		        while (resultSet.next()) {
		            id = resultSet.getInt("id");
		            
		        }
		        resultSet.close();
			}
			String sqlEnd = "INSERT INTO ENDERECO (rua, numero, bairro, dependente_id) values (?, ?, ?, ?)";
			
			try(PreparedStatement stmt2 = con.prepareStatement(sqlEnd)){
				stmt2.setString(1, dependente.getEndereco().getRua());
				stmt2.setString(2, dependente.getEndereco().getNumero());
				stmt2.setString(3, dependente.getEndereco().getBairro());
				stmt2.setInt(4, id);
				
				stmt2.execute();
=======
			String sql = "INSERT INTO DEPENDENTE (nome, cpf, datanasc, funcionario_id) values (?, ?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
				stmt.setInt(4, dependente.getFuncionario());
				
				stmt.execute();
>>>>>>> master
			}
		}
	}

<<<<<<< HEAD
	public List<Dependente> read(String search) throws SQLException {
		List<Dependente> filiais = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM FILIAL where nome like ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, "%"+search+"%");
=======
	public List<Dependente> read() throws SQLException {
		List<Dependente> dependentes = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM Dependente";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
>>>>>>> master
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
<<<<<<< HEAD
					String cnpj = rs.getString("cnpj");
					String inscEstadual = rs.getString("insc_estadual");
					Dependente dependente = new Dependente(nome, cnpj, inscEstadual);

					dependente.setId(id);
					filiais.add(dependente);
=======
					String cpf = rs.getString("cpf");
					String datanasc = rs.getString("datanasc");
					int funcionario_id= rs.getInt("funcionario_id");
					Dependente dependente = new Dependente(nome, cpf, datanasc, funcionario_id);
					
					dependente.setid(id);
					dependentes.add(dependente);
>>>>>>> master
				}
			}

		}
<<<<<<< HEAD
		return filiais;
=======
		return dependentes;
>>>>>>> master
	}

	public void update(Dependente dependente) throws SQLException {
		try (Connection con = Database.getConnection()) {
<<<<<<< HEAD
			String sql = "UPDATE FILIAL SET nome=?, cnpj=?, insc_estadual=? WHERE id=?";
=======
			String sql = "UPDATE DEPENDENTE SET nome=?, cpf=?, datanasc=?, funcionario_id=? WHERE id=?";
>>>>>>> master

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
<<<<<<< HEAD
				stmt.setString(3, dependente.getParentesco());
				stmt.setInt(4, dependente.getId());
=======
				stmt.setInt(4, dependente.getFuncionario());
				stmt.setInt(5, dependente.getid());
>>>>>>> master

				stmt.execute();
			}
		}
	}

	public void delete(int id) throws SQLException {
		System.out.println(id);
		try (Connection con = Database.getConnection()) {
<<<<<<< HEAD
			String sql = "DELETE FROM FILIAL WHERE id=?";
=======
			String sql = "DELETE FROM DEPENDENTE WHERE id=?";
>>>>>>> master

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
		}
	}
}
