package controller;

import java.sql.SQLException;

import model.dao.FuncionarioDAO;
import model.vo.Funcionario;
import view.funcionario.CadFuncionario;
import view.funcionario.ListaFuncionario;

public class FuncionarioController {
	private ListaFuncionario listaFuncionario = null;
	private CadFuncionario cadFuncionario = null;
	private FuncionarioDAO dao = null;
	
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
		dao.create(funcionario.getNome(), funcionario.getCpf(), funcionario.getDatanasc());
	}

	public void deletaFuncionario(int id) throws SQLException {
		dao.delete(id);
	}
}