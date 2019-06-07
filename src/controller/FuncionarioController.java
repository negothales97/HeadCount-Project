package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FuncionarioDAO;
import model.vo.Funcionario;
import view.funcionario.CadFuncionario;
import view.funcionario.CustoFuncionarioView;
import view.funcionario.ListaFuncionario;

public class FuncionarioController {
	private ListaFuncionario listaFuncionario;
	private CadFuncionario cadFuncionario;
	private FuncionarioDAO dao;
	private CustoFuncionarioView custoFuncionario;
	
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
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Or�amento (R$)" };
		dao = new FuncionarioDAO();

		List<Funcionario> funcionarios = dao.getFuncionarios();
		Object[][] dados = new Object[funcionarios.size()][4];
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			dados[i][0] = funcionario.getMatricula();
			dados[i][1] = funcionario.getNome();
			dados[i][2] = funcionario.getCpf();
			dados[i][3] = funcionario.getDatanasc();

		}
		return funcionarios;
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