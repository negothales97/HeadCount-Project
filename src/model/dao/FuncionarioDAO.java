package model.dao;

import java.lang.reflect.Array;
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
import model.vo.CustoDepartamento;
import model.vo.CustoFuncionario;
import model.vo.Filial;
import model.vo.Funcionario;

public class FuncionarioDAO {
	

	private final String INSERT = "INSERT INTO Funcionario (nome,cpf,datanasc, cargo_id, departamento_id, filial_id) values (?, ?, ?, ?, ?, ?)";
	private final String UPDATE = "UPDATE FUNCIONARIO SET   nome=?, cpf=?, datanasc=?,cargo_id=?,departamento_id=?,filial_id=? WHERE matricula=?";
	private final String DELETE = "DELETE FROM FUNCIONARIO WHERE MATRICULA=?";
	private final String LIST = "SELECT * FROM FUNCIONARIO";
	private final String LISTBYID = "SELECT * FROM FUNCIONARIO WHERE MATRICULA = ?";
	private final String CUSTOFUNC = "SELECT * FROM CUSTO_FUNCIONARIO";
	private final String LISTRELFUNC = "SELECT f.matricula, f.nome, sum(c.custo) as total_custo from custo_funcionario as c "
			+ "join funcionario as f on c.funcionario_id = f.matricula WHERE filial_id=? and departamento_id=? "
			+ "group by f.matricula, f.nome ";
	private final String LISTCUSFUNC = "SELECT f.matricula, f.nome, sum(c.custo) as total_custo from custo_funcionario as c "
			+ "join funcionario as f on c.funcionario_id = f.matricula "
			+ "group by f.matricula, f.nome ";

	public void create(Funcionario funcionario) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT);
				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getCpf());
				stmt.setString(3, funcionario.getDatanasc());
				stmt.setInt(4, funcionario.getCargo_id());
				stmt.setInt(5, funcionario.getDepartamento_id());
				stmt.setInt(6, funcionario.getFilial_id());

				stmt.execute();
				JOptionPane.showMessageDialog(null, "Funcionario criado com sucesso");
		} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao criar Funcionario no banco de dados: " + e.getMessage());
		}

	public void update(Funcionario funcionario) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getCpf());
				stmt.setString(3, funcionario.getDatanasc());
				stmt.setInt(4, funcionario.getCargo_id());
				stmt.setInt(5, funcionario.getDepartamento_id());
				stmt.setInt(6, funcionario.getFilial_id());
				stmt.setInt(7, funcionario.getMatricula());
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao editar Funcionario no banco de dados: " + e.getMessage());
			}
		}

	public void delete(int matricula) throws DAOException {
		try (Connection con = Database.getInstance().getConnection()) {

			PreparedStatement stmt = con.prepareStatement(DELETE);
				stmt.setInt(1, matricula);
				stmt.execute();
				JOptionPane.showMessageDialog(null, "Funcionario Removido com sucesso");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao remover Funcionario no banco de dados: " + e.getMessage());
			}
		}
	

	public List<Funcionario> getFuncionarios() throws DAOException {
		List<Funcionario> funcionarios = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LIST);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int matricula = rs.getInt("matricula");
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String dataNasc = rs.getString("datanasc");
					int cargo_id = rs.getInt("cargo_id");
					int departamento_id = rs.getInt("departamento_id");
					int filial_id = rs.getInt("filial_id");
					Funcionario funcionario = new Funcionario(matricula, nome, cpf, dataNasc, cargo_id, departamento_id,
							filial_id);

					funcionarios.add(funcionario);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar Funcionario no banco de dados: " + e.getMessage());
			}

		
		return funcionarios;
	}

	public Funcionario getFuncionario(int id) throws DAOException {
		Funcionario funcionario = null;
		try (Connection con = Database.getInstance().getConnection()){
			PreparedStatement stmt = con.prepareStatement(LISTBYID);
				stmt.setInt(1, id);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				rs.next();
				int matricula = rs.getInt("matricula");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String dataNasc = rs.getString("datanasc");
				int cargo_id = rs.getInt("cargo_id");
				int departamento_id = rs.getInt("departamento_id");
				int filial_id = rs.getInt("filial_id");
				funcionario = new Funcionario(matricula, nome, cpf, dataNasc, cargo_id, departamento_id, filial_id);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar funcionario no banco de dados: " + e.getMessage());
			}

		return funcionario;
	}

	public void custoFunc(int funcionario_id, String obs, double custo) throws DAOException {
		try(Connection con = Database.getInstance().getConnection()){
			String sql = "INSERT INTO CUSTO_FUNCIONARIO (funcionario_id, observacao, custo) values (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, funcionario_id);
				stmt.setString(2, obs);
				stmt.setDouble(3, custo);

				stmt.execute();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar custos no banco de dados: " + e.getMessage());
			}
		}

	

	public List<CustoFuncionario> getCustoFunc() throws DAOException {
		List<CustoFuncionario> custoFuncs = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()){
			PreparedStatement stmt = con.prepareStatement(CUSTOFUNC);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id");
					System.out.println(id);
					int funcionario_id = rs.getInt("funcionario_id");
					String observacao = rs.getString("observacao");
					double custo = rs.getDouble("custo");

					CustoFuncionario custoFunc = new CustoFuncionario(id, funcionario_id, observacao, custo);
					custoFuncs.add(custoFunc);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar custo no banco de dados: " + e.getMessage());
			}
		return custoFuncs;
	}

	public List<String> getRelFuncionarios(int filial_id, int departamento_id) throws DAOException {
		List<String> relFuncionarios = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LISTRELFUNC);
				stmt.setInt(1, filial_id);
				stmt.setInt(2, departamento_id);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					String matricula = String.valueOf(rs.getInt("matricula"));		
					relFuncionarios.add(matricula);
					
					String nome = rs.getString("nome");
					relFuncionarios.add(nome);
					
					String total_custo = String.valueOf(rs.getDouble("total_custo"));
					relFuncionarios.add(total_custo);	
					
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar custo no banco de dados: " + e.getMessage());
			}
		
		return relFuncionarios;
	}
	
	public List<String> getCustosTodosFuncionarios() throws DAOException {
		List<String> relFuncionarios = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection()) {
			PreparedStatement stmt = con.prepareStatement(LISTCUSFUNC);				
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					String matricula = String.valueOf(rs.getInt("matricula"));		
					relFuncionarios.add(matricula);
					
					String nome = rs.getString("nome");
					relFuncionarios.add(nome);
					
					String total_custo = String.valueOf(rs.getDouble("total_custo"));
					relFuncionarios.add(total_custo);	
					
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar custo no banco de dados: " + e.getMessage());
			}
		
		return relFuncionarios;
	}
	
}
