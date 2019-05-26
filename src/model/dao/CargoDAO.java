package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Cargo;

public class CargoDAO {
	private int id;

	public void create(Cargo cargo) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO CARGO (cargo) values (?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, cargo.getCargo());
				
				stmt.execute();
				ResultSet resultSet = stmt.getGeneratedKeys();
		        while (resultSet.next()) {
		            id = resultSet.getInt("id");
		            
		        }
		        resultSet.close();
			}
		}
	}

	public List<Cargo> read(String search) throws SQLException {
		List<Cargo> cargos = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM CARGO where cargo like ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, "%"+search+"%");
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String cargo1 = rs.getString("cargo");
					Cargo cargo = new Cargo(cargo1);

					cargo.setId(id);
					cargos.add(cargo);
				}
			}

		}
		return cargos;
	}

	public void update(Cargo cargo) throws SQLException {
		try (Connection con = Database.getConnection()) {
			String sql = "UPDATE CARGO SET cargo=? WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, cargo.getCargo());
				stmt.setInt(2, cargo.getId());

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
