package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.connection.Database;
import model.vo.Cargo;
import model.vo.CustoDepartamento;
import model.vo.CustoFuncionario;
import model.vo.Filial;
import model.vo.Funcionario;

public class RelFuncDAO {
	private RelFuncDAO(){}
	
	private static RelFuncDAO instancia =null;
	
	public static RelFuncDAO getInstance() {
		if (instancia ==null) {
			instancia = new RelFuncDAO();
		}
		return instancia;
		
		
	}
	private final String LIST 		= "select  f.matricula, f.nome, sum(c.custo) as total_custo from custo_funcionario as c\r\n" + 
			"join funcionario as f on c.funcionario_id = f.matricula group by f.matricula, f.nome";


	public List<Funcionario> listaFuncionarios(String departamento, String filial) throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		try (Connection con = Database.getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(LIST)) {				
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
					Funcionario funcionario = new Funcionario(matricula, nome, cpf, dataNasc, cargo_id, departamento_id, filial_id);

					
					funcionarios.add(funcionario);
				}
			}catch (SQLException e) {
	    		JOptionPane.showMessageDialog(null, "Erro ao listar Funcionario no banco de dados: " +e.getMessage());
			}

		}
		return funcionarios;
	}


}