package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FuncionarioDAO;
import model.dao.FilialDAO;
import model.connection.DAOException;
import model.dao.DepartamentoDAO;
import model.vo.Cargo;
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
	private FuncionarioDAO FuncionarioDAO;
	private FilialDAO FilialDAO;
	private DepartamentoDAO DepartamentoDAO;
	private CustoFuncionarioView custoFuncionario;
	private EditFuncionario editFuncionario;
	private RelCustoFuncionario relFunc;
	private FuncionarioDAO dao;	

	public FuncionarioController() {
		FuncionarioDAO = new FuncionarioDAO();
	}

	public void listaFuncionario(){
		listaFuncionario = new ListaFuncionario();
	}

	public void novoFuncionario() {
		cadFuncionario = new CadFuncionario();
	}

	public void criaFuncionario(Funcionario funcionario){
		try {
			FuncionarioDAO.create(funcionario);
			this.listaFuncionario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editaFuncionario(int id) {
		Funcionario funcionario;
		try {
			funcionario = FuncionarioDAO.getFuncionario(id);
			editFuncionario = new EditFuncionario(funcionario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateFuncionario(Funcionario funcionario) {
		try {
			FuncionarioDAO.update(funcionario);
			this.listaFuncionario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletaFuncionario(int id) {
		try {
			FuncionarioDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.listaFuncionario();
	}

	public void registraCustoFuncionario() {
		try {
			custoFuncionario = new CustoFuncionarioView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = null;
		try {
			funcionarios = FuncionarioDAO.getFuncionarios();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	public List<Funcionario> pesquisaFuncionarios(String nome){
		List<Funcionario> funcionarios = null;
		try {
			funcionarios = dao.pesquisar(nome);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

	public List<CustoFuncionario> getCustoFuncionarios(){
		List<CustoFuncionario> custos = null;
		try {
			custos = FuncionarioDAO.getCustoFunc();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custos;
	}

	public void relCustoFunc() {
		relFunc = new RelCustoFuncionario();
	}

	public void incluiCusto(int funcionario_id, String obs, double custo) {
		try {
			FuncionarioDAO.custoFunc(funcionario_id, obs, custo);
			this.registraCustoFuncionario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getRelFuncionarios(int filial, int departamento){
		List<String> relFuncionarios = null;
		try {
			relFuncionarios = FuncionarioDAO.getRelFuncionarios(filial, departamento);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return relFuncionarios;
	}
	
	public List<String> getCustosTodosFuncionarios(){
		List<String> custosFuncionarios = null;
		try {
			custosFuncionarios = FuncionarioDAO.getCustosTodosFuncionarios();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return custosFuncionarios;
	}
	
}