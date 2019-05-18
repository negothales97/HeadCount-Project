package controller;

import java.sql.SQLException;

import model.dao.DepartamentoDAO;
import model.vo.Departamento;
import view.departamento.CadDepartamento;
import view.departamento.ListaDepartamento;

public class DepartamentoController {
	private ListaDepartamento listaDepartamento = null;
	private CadDepartamento cadDepartamento = null;
	private DepartamentoDAO dao = null; 
	
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
		System.out.println(departamento.getNome()+ departamento.getCentroCusto()+ departamento.getOrcamento());
		dao.create(departamento.getNome(), departamento.getCentroCusto(), departamento.getOrcamento());
	}

	public void deletaDepartamento(int id) throws SQLException {
		dao.delete(id);
	}
	
		
}
