package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import model.connection.DAOException;
import model.connection.Database;
import model.vo.Cargo;
import model.vo.CustoDepartamento;
import model.vo.Departamento;
import model.vo.Filial;


public class DepartamentoDAO {
	
	private final String INSERT		= "INSERT INTO DEPARTAMENTO (nome, centrocusto, orcamento) values (?, ?, ?)";
	private final String UPDATE		= "UPDATE DEPARTAMENTO SET nome=?, centrocusto=?, orcamento=? WHERE id=?";
	private final String DELETE 	= "DELETE FROM DEPARTAMENTO WHERE id=?";
	private final String LIST 		= "SELECT * FROM DEPARTAMENTO";
	private final String SEARCH 	= "select * from DEPARTAMENTO where nome like ?";
	private final String LISTBYID 	= "SELECT * FROM DEPARTAMENTO WHERE ID = ?";

	public void create(Departamento departamento) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {

			PreparedStatement stmt = con.prepareStatement(INSERT);
				stmt.setString(1, departamento.getNome());
				stmt.setString(2, departamento.getCentroCusto());
				stmt.setDouble(3, departamento.getOrcamento());
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Departamento criado com sucesso");
		
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao criar departamento no banco de dados: " +e.getMessage());
			}	
	}
	
	public void update(Departamento departamento) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			String sql = "UPDATE DEPARTAMENTO SET nome=?, centrocusto=?, orcamento=? WHERE id=?";

			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, departamento.getNome());
				stmt.setString(2, departamento.getCentroCusto());
				stmt.setDouble(3, departamento.getOrcamento());
				stmt.setInt(4, departamento.getId());

				stmt.execute();
				JOptionPane.showMessageDialog(null, "Departamento editado com sucesso");
			
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao editar departamento no banco de dados: " +e.getMessage());
			}
	}
	
	
	
	public void delete(int id) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			String sql = "DELETE FROM DEPARTAMENTO WHERE id=?";

			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Departamento removido com sucesso");
				
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao deletar departamento no banco de dados: " +e.getMessage());
			}
	}

	
	public List<Departamento> getDepartamentos() throws DAOException {
		List<Departamento> departamentos = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LIST);
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
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departamentos;
		
	}
	public Departamento getDepartamento(int id) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()){
			PreparedStatement stmt = con.prepareStatement(LISTBYID);
				stmt.setInt(1, id);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				rs.next();
				int idDepartamento = rs.getInt("id");
				String nome = rs.getString("nome");
				String centrocusto = rs.getString("centrocusto");
				double orcamento= rs.getDouble("orcamento");
				Departamento departamento = new Departamento(nome, centrocusto, orcamento);
				departamento.setId(idDepartamento);
				return departamento;
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Departamento> pesquisar(String search) throws DAOException {
		List<Departamento> departamentos = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SEARCH);
			stmt.setString(1, "%"+search+"%");
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
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return departamentos;
		
	}
	
	public List<CustoDepartamento> getCustoDep() throws DAOException{
		List<CustoDepartamento> custosDepartamento = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()){
			String sql = "SELECT * FROM CUSTO_DEPARTAMENTO";
			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					int filial_id = rs.getInt("filial_id");
					int departamento_id = rs.getInt("departamento_id");
					String observacao = rs.getString("observacao");
					double custo = rs.getDouble("custo");
					
					Departamento departamento = getDepartamento(departamento_id);
					FilialDAO dao= new FilialDAO();
					Filial filial = dao.getFilial(filial_id); 
					
					System.out.println(filial);
					System.out.println(departamento);
					
					CustoDepartamento custoDep = new CustoDepartamento(id, filial_id, departamento_id, observacao, custo,departamento,filial);
					custosDepartamento.add(custoDep);
					
				}
			}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custosDepartamento;
	}
	
	
	
	public void custoDep(int filial_id, int departamento_id, String obs, double custo) throws DAOException{
		try(Connection con = Database.getInstance().getConnection()){
			String sql = "INSERT INTO CUSTO_DEPARTAMENTO (filial_id, departamento_id, observacao, custo) values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, filial_id);
				stmt.setInt(2, departamento_id);
				stmt.setString(3, obs);
				stmt.setDouble(4, custo);
				
				stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}