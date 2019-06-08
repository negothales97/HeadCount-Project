package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FuncionarioDAO;
import model.vo.CustoFuncionario;
import model.vo.Departamento;
import model.vo.Funcionario;
import view.departamento.EditDepartamento;
import view.funcionario.CadFuncionario;
import view.funcionario.CustoFuncionarioView;
import view.funcionario.EditFuncionario;
import view.funcionario.ListaFuncionario;
import view.funcionario.RelCustoFuncionario;

public class FuncionarioController {
	private ListaFuncionario listaFuncionario;
	private CadFuncionario cadFuncionario;
	private FuncionarioDAO dao;
	private CustoFuncionarioView custoFuncionario;
	private EditFuncionario editFuncionario;
	private RelCustoFuncionario relFunc;
	
	public FuncionarioController() {
		dao = new FuncionarioDAO();
	}
	public void listaFuncionario() throws SQLException {
		listaFuncionario = new ListaFuncionario();
	}

	public void novoFuncionario() {
		cadFuncionario = new CadFuncionario();
	}
	
	public void criaFuncionario(Funcionario funcionario) throws SQLException {
		dao.create(funcionario);
	}
	public void editaFuncionario(int id) {
		Funcionario funcionario;
		try {
			funcionario = dao.getFuncionario(id);
			editFuncionario = new EditFuncionario(funcionario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateFuncionario(Funcionario funcionario) {
		try {
			dao.update(funcionario);
			this.listaFuncionario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletaFuncionario(int id) throws SQLException {
		dao.delete(id);
		this.listaFuncionario();
	}
	
	public void registraCustoFuncionario() {
		try {
			custoFuncionario = new CustoFuncionarioView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Funcionario> getFuncionarios() throws SQLException {
		List<Funcionario> funcionarios = dao.getFuncionarios();
		return funcionarios;
	}
	
	public List<CustoFuncionario> getCustoFuncionarios() throws SQLException {
		List<CustoFuncionario> custos = dao.getCustoFunc();
		return custos;
	}
	
	public void relCustoFunc() {
		try {
			relFunc = new RelCustoFuncionario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void incluiCusto(int funcionario_id, String obs, double custo) {
		try {
			System.out.println(obs);
			dao.custoFunc(funcionario_id, obs, custo);
			this.registraCustoFuncionario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}