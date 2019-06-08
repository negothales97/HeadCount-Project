package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.DepartamentoDAO;
import model.vo.Cargo;
import model.vo.CustoDepartamento;
import model.vo.CustoFuncionario;
import model.vo.Departamento;
import view.cargo.EditCargo;
import view.departamento.CadDepartamento;
import view.departamento.CustoDepartamentoView;
import view.departamento.EditDepartamento;
import view.departamento.ListaDepartamento;
import view.departamento.RelCustoDepartamento;

public class DepartamentoController {
	private ListaDepartamento listaDepartamento = null;
	private CadDepartamento cadDepartamento 	= null;
	private CustoDepartamentoView custoDepartamento;
	private DepartamentoDAO dao;
	private EditDepartamento editDepartamento;
	private RelCustoDepartamento relDep;
	

	public DepartamentoController() {
		dao = dao.getInstance();
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
	
	public List<CustoDepartamento> getCustoDepartamentos() throws SQLException {
		List<CustoDepartamento> custos = dao.getCustoDep();
		return custos;
	}
	
	public void incluiCusto(int filial_id, int departamento_id, String obs, double custo) {
		try {
			dao.custoDep(filial_id, departamento_id, obs, custo);
			this.registraCustoDepartamento();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Departamento> getDepartamentos() throws SQLException {
		List<Departamento> departamentos = dao.getDepartamentos();
		return departamentos;
	}
	
	public void editaDepartamento(int id) {
		Departamento departamento;
		try {
			departamento = dao.getDepartamento(id);
			editDepartamento = new EditDepartamento(departamento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void relDepartamento() {
		try {
			relDep = new RelCustoDepartamento();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDepartamento(Departamento departamento) {
		try {
			dao.update(departamento);
			this.listaDepartamento();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
