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
import model.vo.Dependente;

public class DependenteDAO {
	
	private int id;
	private final String INSERT		= "INSERT INTO DEPENDENTE (nome, cpf, datanasc, funcionario_id) values (?, ?, ?, ?)";
	private final String UPDATE		= "UPDATE DEPENDENTE SET nome=?, cpf=?, datanasc=?, funcionario_id=? WHERE id=?";
	private final String DELETE 	= "DELETE FROM DEPENDENTE WHERE ID=?";
	private final String LIST 		= "SELECT * FROM DEPENDENTE";
	private final String LISTBYID 	= "SELECT * FROM DEPENDENTE WHERE ID = ?";
	private final String LISTBYFID 	= "SELECT * FROM DEPENDENTE WHERE FUNCIONARIO_ID = ?";

	public void create(Dependente dependente) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
				
			PreparedStatement stmt = con.prepareStatement(INSERT);

				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
				stmt.setInt(4, dependente.getFuncionario());
				
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Dependente Cadastrado com sucesso");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(Dependente dependente) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
				stmt.setString(1, dependente.getNome());
				stmt.setString(2, dependente.getCpf());
				stmt.setString(3, dependente.getDataNasc());
				stmt.setInt(4, dependente.getFuncionario());
				stmt.setInt(5, dependente.getId());

				stmt.execute();
				JOptionPane.showMessageDialog(null, "Dependente atualizado com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	public void delete(int id) throws DAOException{
		try (Connection con = Database.getInstance().getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(DELETE)) {
				stmt.setInt(1, id);
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Dependente removido com sucesso");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Dependente> getDependentes() throws DAOException {
		List<Dependente> dependentes = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LIST);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String datanasc = rs.getString("datanasc");
					int funcionario_id = rs.getInt("funcionario_id");
					Dependente dependente = new Dependente(nome, cpf, datanasc, funcionario_id);
					
					dependente.setId(id);
					dependentes.add(dependente);
					
				}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependentes;
	}	

	public Dependente getDependente(int id) throws DAOException {

		Dependente dependente = null;
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LISTBYID);
				stmt.setInt(1, id);
				stmt.execute();
				
				ResultSet rs = stmt.getResultSet();
				rs.next();
				int idDependente = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String datanasc = rs.getString("datanasc");
				int funcionario_id = rs.getInt("funcionario_id");
				dependente = new Dependente(nome, cpf, datanasc, funcionario_id);					
				dependente.setId(idDependente);
				return dependente;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getListaDependentes(int matricula) throws DAOException {
		List<String> listaDepentendes = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LISTBYFID);
			stmt.setInt(1, matricula);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String id = String.valueOf(rs.getInt("id"));
				listaDepentendes.add(id);

				String nome = rs.getString("nome");
				listaDepentendes.add(nome);

				String cpf = rs.getString("cpf");
				listaDepentendes.add(cpf);
				
				String datanasc = rs.getString("datanasc");
				listaDepentendes.add(datanasc);
				
				String funcionario_id = String.valueOf(rs.getInt("funcionario_id"));
				listaDepentendes.add(funcionario_id);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar custo no banco de dados: " + e.getMessage());
		}

		return listaDepentendes;
	}
}
