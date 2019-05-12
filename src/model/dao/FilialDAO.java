package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.connection.Database;
import model.vo.Filial;

public class FilialDAO {

	public void create(Filial filial) throws SQLException {
		// Conexão
		try (Connection con = Database.getConnection()) {
			// SQL
			String sql = "INSERT INTO FILIAL (nome, cnpj, insc_estadual) values (?, ?, ?)";	
			// Prepara o sql para evitar erros
			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				// Seta os valores na query
				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());

				// Executa a query
				boolean resultado = stmt.execute();
				// Fecha o stmt e a conexão
			}
		}
	}

	public void read(Filial filial) throws SQLException {
		
	}

	public void update(Filial filial) throws SQLException {
		try (Connection con = Database.getConnection()){
			String sql = "UPDATE FILIAL SET nome=?, cnpj=?, insc_estadual=? WHERE id=?";
			
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());
				stmt.setInt(4, filial.getId());
				
				boolean resultado = stmt.execute();
			}
		}
	}

	public void delete(Filial filial) throws SQLException {
		try(Connection con = Database.getConnection()){
			String sql = "DELETE FROM FILIAL WHERE id=?";
			
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, filial.getId());
				boolean resultado = stmt.execute();
			}
		}
	}
}
