package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Cargo;
import model.vo.Endereco;
import model.vo.Filial;

public class CargoDAO {

	public void create(Cargo cargo) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO CARGO (nome) values (?)";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setString(1, cargo.getNome());
				
				stmt.execute();
			}
		}
	}

	public List<Cargo> read() throws SQLException {
		List<Cargo> cargos = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM CARGO";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Cargo cargo = new Cargo(nome);

					cargo.setId(id);
					cargos.add(cargo);
				}
			}

		}
		return cargos;
	}

	public Cargo getCargo(int id) throws SQLException{
		try (Connection con = Database.getConnection()){
			String sql = "SELECT * FROM CARGO WHERE ID=? ";
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, id);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				rs.next();
				int idCargo = rs.getInt("id");
				String nome = rs.getString("nome");
				Cargo cargo = new Cargo(nome);
				cargo.setId(idCargo);
				return cargo;
			}
		}
	}

	public void update(Cargo cargo) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "UPDATE CARGO SET nome = ? WHERE id = ?;";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, cargo.getNome());
				stmt.setInt(1, cargo.getId());
				stmt.execute();
			}
		}
	}

	public void delete(int id) throws SQLException {
		System.out.println(id);
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM CARGO WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
		}
	}
}
