package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.connection.DAOException;
import model.connection.Database;
import model.vo.Cargo;
import model.vo.Endereco;
import model.vo.Filial;

public class CargoDAO {

	private final String INSERT		= "INSERT INTO CARGO (nome) values (?)";
	private final String UPDATE		= "UPDATE CARGO SET nome = ? WHERE id = ?";
	private final String DELETE 	= "DELETE FROM CARGO WHERE id=?";
	private final String LIST 		= "SELECT * FROM CARGO";
	private final String LISTBYID 	= "SELECT * FROM CARGO WHERE ID = ?";
	private final String SEARCH 	= "SELECT * from cargo WHERE nome LIKE ?";

	public void create(Cargo cargo) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {

			PreparedStatement stmt = con.prepareStatement(INSERT); 

				stmt.setString(1, cargo.getNome());
				
				stmt.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro criar cargo: " +e.getMessage());
		}
	}
	public void update(Cargo cargo) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
				stmt.setString(1, cargo.getNome());
				stmt.setInt(2, cargo.getId());
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
			}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar cargo no banco de dados: " +e.getMessage());
		}
}
	public void delete(int id) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(DELETE);
				stmt.setInt(1, id);
				stmt.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar cargo no banco de dados: " +e.getMessage());
			
		}
	}

	public List<Cargo> getCargos() throws DAOException {
		List<Cargo> cargos = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LIST);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Cargo cargo = new Cargo(nome);

					cargo.setId(id);
					cargos.add(cargo);
					
				}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar cargos no banco de dados: " +e.getMessage());
		}
		return cargos;
	}

	public Cargo getCargo(int id) throws DAOException{
		Cargo cargo = null;
		try (Connection con = Database.getInstance().getConnection()){
			PreparedStatement stmt = con.prepareStatement(LISTBYID);
			stmt.setInt(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			rs.next();
			int idCargo = rs.getInt("id");
			String nome = rs.getString("nome");
			cargo = new Cargo(nome);
			cargo.setId(idCargo);
			return cargo;
				
			
		}catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "Erro ao buscar cargo no banco de dados: " +e.getMessage());
		}
		return null;
	}
	
	public List<Cargo> pesquisar(String search) throws DAOException {
		List<Cargo> cargos = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SEARCH);
			stmt.setString(1, "%"+ search+"%");
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				Cargo cargo = new Cargo(nome);
				cargo.setId(id);
				cargos.add(cargo);
				
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar cargos no banco de dados: " +e.getMessage());
		}
		return cargos;
	}

	

	
}
