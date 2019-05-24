package controller;

import java.sql.SQLException;

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
	
	public void registraCustoDepartamento(){
		regCustoDepartamento = new RegCustoDepartamento();
	}
	
	public void formRegistraCusto() {
		confirmaRCD = new ConfirmaRCD();
	}
		
}
