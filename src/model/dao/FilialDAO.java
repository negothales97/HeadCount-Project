package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.connection.Database;
import model.vo.Endereco;
import model.vo.Filial;

public class FilialDAO {
	private FilialDAO(){
		
	}
	
	private static FilialDAO instancia =null;
	
	public static FilialDAO getInstance() {
		if (instancia ==null) {
			System.out.println("nova instancia");
			instancia = new FilialDAO();
		}
		return instancia;
		
		
	}
	private int id;

	public void create(Filial filial) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "INSERT INTO FILIAL (nome, cnpj, insc_estadual) values (?, ?, ?)";
			try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());
				
				stmt.execute();
				ResultSet resultSet = stmt.getGeneratedKeys();
		        while (resultSet.next()) {
		            id = resultSet.getInt("id");
		            
		        }
		        resultSet.close();
			}
			String sqlEnd = "INSERT INTO ENDERECO (rua, numero, bairro, filial_id) values (?, ?, ?, ?)";
			
			try(PreparedStatement stmt2 = con.prepareStatement(sqlEnd)){
				stmt2.setString(1, filial.getEndereco().getRua());
				stmt2.setString(2, filial.getEndereco().getNumero());
				stmt2.setString(3, filial.getEndereco().getBairro());
				stmt2.setInt(4, id);
				
				stmt2.execute();
			}
		}
	}

	
	public List<Filial> read() throws SQLException {
		List<Filial> filiais = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			String sql = "SELECT * FROM FILIAL";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String cnpj = rs.getString("cnpj");
					String inscEstadual = rs.getString("insc_estadual");
					Filial filial = new Filial(nome, cnpj, inscEstadual);

					filial.setId(id);
					filiais.add(filial);
				}
			}

		}
		return filiais;
	}
	
	public Filial getFilial(int id) throws SQLException{
		try (Connection con = Database.getConnection()){
			String sql = "SELECT * FROM FILIAL INNER JOIN ENDERECO ON FILIAL.ID = ENDERECO.FILIAL_ID WHERE FILIAL.ID = ?";
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, id);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				rs.next();
				int idFilial = rs.getInt("id");
				String nome = rs.getString("nome");
				String cnpj = rs.getString("cnpj");
				String inscEstadual = rs.getString("insc_estadual");
				String rua = rs.getString("rua");
				String numero = rs.getString("numero");
				String bairro = rs.getString("bairro");
				Filial filial = new Filial(nome, cnpj, inscEstadual);
				filial.setId(idFilial);
				Endereco endereco = new Endereco(rua, numero, bairro);
				filial.setEndereco(endereco);
				return filial;
			}
		}
	}

	public void update(Filial filial) throws SQLException {
		try (Connection con = Database.getConnection()) {

			String sql = "UPDATE FILIAL SET nome = ?, cnpj = ?, insc_estadual = ? WHERE id = ?;";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());
				stmt.setInt(4, filial.getId());
				
				stmt.execute();
			}
			String sqlEnd = "UPDATE ENDERECO SET rua = ?, numero = ?, bairro = ? where filial_id = ?";
			
			try(PreparedStatement stmt2 = con.prepareStatement(sqlEnd)){
				stmt2.setString(1, filial.getEndereco().getRua());
				stmt2.setString(2, filial.getEndereco().getNumero());
				stmt2.setString(3, filial.getEndereco().getBairro());
				stmt2.setInt(4, filial.getId());
				
				stmt2.execute();
			}
		}
	}

	public void delete(int id) throws SQLException {
		System.out.println(id);
		try (Connection con = Database.getConnection()) {
			String sql = "DELETE FROM FILIAL WHERE id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
		}
	}
}
