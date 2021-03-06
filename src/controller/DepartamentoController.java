package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.connection.DAOException;
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

public class DepartamentoController {
	private ListaDepartamento listaDepartamento;
	private CadDepartamento cadDepartamento;
	private CustoDepartamentoView custoDepartamento;
	private DepartamentoDAO dao;
	private EditDepartamento editDepartamento;

	public DepartamentoController() {
		dao = new DepartamentoDAO();
	}

	public void listaDepartamento(){
		listaDepartamento = new ListaDepartamento();
	}

	
	public void novoDepartamento() {
		cadDepartamento = new CadDepartamento();
	}

	public boolean criaDepartamento(Departamento departamento){
		if (validaCampos(departamento)) {
			try {
				dao.create(departamento);
				this.listaDepartamento();
				return true;
			} catch (DAOException e) {
				e.printStackTrace();
				return true;
			}
		}
		return false;
	}

	public void deletaDepartamento(int id){
		try {
			dao.delete(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaDepartamento();
	}

	public void registraCustoDepartamento() {
		try {
			custoDepartamento = new CustoDepartamentoView();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List<CustoDepartamento> getCustoDepartamentos(){
		List<CustoDepartamento> custos = null;
		try {
			custos = dao.getCustoDep();
		} catch (DAOException e) {
			e.printStackTrace();
		}
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

	public List<Departamento> getDepartamentos(){
		List<Departamento> departamentos = null;
		try {
			departamentos = dao.getDepartamentos();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return departamentos;
	}
	
	public List<Departamento> pesquisaDepartamentos(String nome){
		List<Departamento> departamentos = null;
		try {
			departamentos = dao.pesquisar(nome);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return departamentos;
	}
	
	public void editaDepartamento(int id) {
		Departamento departamento;
		try {
			departamento = dao.getDepartamento(id);
			editDepartamento = new EditDepartamento(departamento);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	public boolean updateDepartamento(Departamento departamento) {
		if(validaCampos(departamento)) {
			try {
				dao.update(departamento);
				this.listaDepartamento();
				return true;
			} catch (DAOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public boolean validaCampos(Departamento departamento) {
		boolean campos = true;
		if(departamento.getNome().equals("") || departamento.getNome().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(departamento.getCentroCusto().equals("") || departamento.getCentroCusto().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		}
		return campos;
	}
	
}
