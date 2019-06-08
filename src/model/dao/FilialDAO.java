package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.connection.Database;
import model.vo.Endereco;
import model.vo.Filial;

public class FilialDAO {
	
	private int id;
	private final String INSERTFILIAL	= "INSERT INTO FILIAL (nome, cnpj, insc_estadual) values (?, ?, ?)";
	private final String INSERTENDERECO	= "INSERT INTO ENDERECO (rua, numero, bairro, filial_id) values (?, ?, ?, ?)";
	private final String UPDATEFILIAL	= "UPDATE FILIAL SET nome = ?, cnpj = ?, insc_estadual = ? WHERE id = ?";
	private final String UPDATEENDERECO	= "UPDATE ENDERECO SET rua = ?, numero = ?, bairro = ? where filial_id = ?";
	private final String DELETE 		= "DELETE FROM FILIAL WHERE id=?; DELETE FROM ENDERECO WHERE filial_id=?";
	private final String LIST 			= "SELECT * FROM FILIAL INNER JOIN ENDERECO ON FILIAL.ID = ENDERECO.FILIAL_ID";
	private final String LISTBYID 		= "SELECT * FROM FILIAL INNER JOIN ENDERECO ON FILIAL.ID = ENDERECO.FILIAL_ID WHERE FILIAL.ID = ?";

	public void create(Filial filial) throws SQLException {
		try (Connection con = Database.getInstance().getConnection()) {

			try (PreparedStatement stmt = con.prepareStatement(INSERTFILIAL, PreparedStatement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());
				
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();
		        rs.next();
	            id = rs.getInt("id");
		        rs.close();
			}
			
			try(PreparedStatement stmt2 = con.prepareStatement(INSERTENDERECO)){
				stmt2.setString(1, filial.getEndereco().getRua());
				stmt2.setString(2, filial.getEndereco().getNumero());
				stmt2.setString(3, filial.getEndereco().getBairro());
				stmt2.setInt(4, id);
				
				stmt2.execute();
				JOptionPane.showMessageDialog(null, "Filial Criada com sucesso");
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao criar filial no banco de dados: " +e.getMessage());
			}
		}
	}
	
	public void update(Filial filial) throws SQLException {
		
		try (Connection con = Database.getInstance().getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(UPDATEFILIAL)) {
				stmt.setString(1, filial.getNome());
				stmt.setString(2, filial.getCnpj());
				stmt.setString(3, filial.getInscEstadual());
				stmt.setInt(4, filial.getId());
				stmt.execute();
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao editar filial no banco de dados: " +e.getMessage());
			}
			try(PreparedStatement stmt2 = con.prepareStatement(UPDATEENDERECO)){
				stmt2.setString(1, filial.getEndereco().getRua());
				stmt2.setString(2, filial.getEndereco().getNumero());
				stmt2.setString(3, filial.getEndereco().getBairro());
				stmt2.setInt(4, filial.getId());
				
				stmt2.execute();
				JOptionPane.showMessageDialog(null, "Filial Editada com sucesso");
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao editar filial no banco de dados: " +e.getMessage());
			}
			
		}
	}
	public void delete(int id) throws SQLException {
		try (Connection con = Database.getInstance().getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(DELETE)) {
				stmt.setInt(1, id);
				stmt.setInt(2, id);
				stmt.execute();
			}
		}catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "Erro ao deletar filial no banco de dados: " +e.getMessage());
		}
	}
	
	public List<Filial> getFiliais() throws SQLException {
		List<Filial> filiais = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(LIST)) {
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String cnpj = rs.getString("cnpj");
					String inscEstadual = rs.getString("insc_estadual");
					String rua = rs.getString("rua");
					String num = rs.getString("numero");
					String bairro = rs.getString("bairro");
					Endereco endereco = new Endereco(rua, num, bairro);
					
					Filial filial = new Filial(nome, cnpj, inscEstadual, endereco);

					filial.setId(id);
					filiais.add(filial);
				}
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao Listar filiais no banco de dados: " +e.getMessage());
			}

		}
		return filiais;
	}
	
	public Filial getFilial(int id) throws SQLException{
		try (Connection con = Database.getInstance().getConnection()){
			try(PreparedStatement stmt = con.prepareStatement(LISTBYID)){
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
				Filial filial = new Filial(nome, cnpj, inscEstadual, new Endereco(rua, numero, bairro));
				filial.setId(idFilial);
				return filial;
			}
		}
	}

	

	
}
