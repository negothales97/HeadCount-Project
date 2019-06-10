package controller;

import java.sql.SQLException;
import java.util.List;

import model.connection.DAOException;
import model.dao.DependenteDAO;
import model.dao.FilialDAO;
import model.vo.Cargo;
import model.vo.Dependente;
import view.cargo.EditCargo;
import view.dependente.CadDependente;
import view.dependente.EditDependente;
import view.dependente.ListaDependente;

public class DependenteController {
	private ListaDependente listaDependente = null;
	private CadDependente cadDependente = null;
	private DependenteDAO dao;
	private EditDependente editDependente; 
	
	public DependenteController() {
		dao = new DependenteDAO();
	}
	public void listaDependente() {
		listaDependente = new ListaDependente();
	}

	public void novoDependente() {
		cadDependente = new CadDependente();
	}
	
	public void criaDependente(Dependente dependente) {
		try {
			dao.create(dependente);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaDependente();
	}

	public void deletaDependente(int id){
		try {
			dao.delete(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaDependente();
	}
	public List<Dependente> getDependentes() {
		List<Dependente> dependentes = null;
		try {
			dependentes = dao.getDependentes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependentes;
	}
	public void editaDependete(int id) {
		try {
			Dependente dependente = dao.getDependente(id);
			editDependente = new EditDependente(dependente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDependente(Dependente dependente) {
		try {
			dao.update(dependente);
			this.listaDependente();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getListaDependente(int matricula){
		List<String> listaDepentendes = null;
		try {
			listaDepentendes = dao.getListaDependentes(matricula);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaDepentendes;
	}
	
//	public List<String> getTodosDependentes(){
//		List<String> listaDepentendes = null;
//		try {
//			listaDepentendes = dao.getDependentes();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
//		return listaDepentendes;
//	}
		
	
}