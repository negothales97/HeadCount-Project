package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FuncionarioDAO;
import model.vo.Funcionario;
import view.funcionario.CadFuncionario;
import view.funcionario.CustoFuncionario;
import view.funcionario.ListaFuncionario;

public class FuncionarioController {
	private ListaFuncionario listaFuncionario;
	private CadFuncionario cadFuncionario;
	private FuncionarioDAO dao;
	private CustoFuncionario custoFuncionario;
	
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
	
	public void registraCustoFuncionario() {
		try {
			custoFuncionario = new CustoFuncionario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String[] comboBoxFuncionario() throws SQLException {
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Orçamento (R$)" };
		dao = new FuncionarioDAO();

		List<Funcionario> funcionarios = dao.read();
		String [] master = new String [funcionarios.size()];
		Object[][] dados = new Object[funcionarios.size()][4];
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			dados[i][0] = funcionario.getMatricula();
			dados[i][1] = funcionario.getNome();
			dados[i][2] = funcionario.getCpf();
			dados[i][3] = funcionario.getDatanasc();
			master[i] = funcionario.getNome();

		}
		return master;
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