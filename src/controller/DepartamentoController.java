package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.DepartamentoDAO;
import model.vo.Departamento;
import view.departamento.CadDepartamento;
import view.departamento.CustoDepartamentoView;
import view.departamento.ListaDepartamento;

public class DepartamentoController {
	private ListaDepartamento listaDepartamento = null;
	private CadDepartamento cadDepartamento 	= null;
	private CustoDepartamentoView custoDepartamento = null;
	private DepartamentoDAO dao 				= null;
	

	public DepartamentoController() {
		dao = new DepartamentoDAO();
	}

	public void listaDepartamento() throws SQLException {
		listaDepartamento = new ListaDepartamento();
	}

	public void novoDepartamento() {
		cadDepartamento = new CadDepartamento();
	}

	public void criaDepartamento(Departamento departamento) throws SQLException {
		dao.create(departamento);
		this.listaDepartamento();
	}

	public void deletaDepartamento(int id) throws SQLException {
		dao.delete(id);
		this.listaDepartamento();
	}

	public void registraCustoDepartamento() {
		try {
			custoDepartamento = new CustoDepartamentoView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void incluiCusto(int filial_id, int departamento_id, String obs, double custo) {
		try {
			dao.custoDep(filial_id, departamento_id, obs, custo);
			this.registraCustoDepartamento();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Departamento> comboBoxDepartamento() throws SQLException {
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Orçamento (R$)" };
		DepartamentoDAO dao = new DepartamentoDAO();

		List<Departamento> departamentos = dao.read();
		Object[][] dados = new Object[departamentos.size()][4];
		for (int i = 0; i < departamentos.size(); i++) {
			Departamento departamento = departamentos.get(i);
			dados[i][0] = departamento.getId();
			dados[i][1] = departamento.getNome();
			dados[i][2] = departamento.getCentroCusto();
			dados[i][3] = departamento.getOrcamento();

		}
		return departamentos;
	}
}
