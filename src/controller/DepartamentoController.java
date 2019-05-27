package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.DepartamentoDAO;
import model.vo.Departamento;
import view.departamento.CadDepartamento;
import view.departamento.ConfirmaRCD;
import view.departamento.ListaDepartamento;
import view.departamento.RegCustoDepartamento;

public class DepartamentoController {
	private ListaDepartamento listaDepartamento = null;
	private CadDepartamento cadDepartamento = null;
	private RegCustoDepartamento regCustoDepartamento = null;
	private DepartamentoDAO dao = null;
	private ConfirmaRCD confirmaRCD = null;

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
		regCustoDepartamento = new RegCustoDepartamento();
	}

	public void formRegistraCusto() {
		confirmaRCD = new ConfirmaRCD();
	}

	public String[] comboBoxDepartamento() throws SQLException {
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Orçamento (R$)" };
		DepartamentoDAO dao = new DepartamentoDAO();

		List<Departamento> departamentos = dao.read();
		String [] masterDepartamento = new String [departamentos.size()];
		Object[][] dados = new Object[departamentos.size()][4];
		for (int i = 0; i < departamentos.size(); i++) {
			Departamento departamento = departamentos.get(i);
			dados[i][0] = departamento.getId();
			dados[i][1] = departamento.getNome();
			dados[i][2] = departamento.getCentroCusto();
			dados[i][3] = departamento.getOrcamento();
			masterDepartamento[i] = departamento.getNome();

		}
		return masterDepartamento;
	}
}
