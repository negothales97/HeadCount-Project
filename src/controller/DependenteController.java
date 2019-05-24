package controller;

import java.sql.SQLException;

import model.dao.DependenteDAO;
import model.vo.Dependente;
import view.dependente.CadDependente;
import view.dependente.ListaDependente;

public class DependenteController {
	private ListaDependente listaDependente = null;
	private CadDependente cadDependente = null;
	private DependenteDAO dao = null; 
	
	public DependenteController() {
		dao = new DependenteDAO();
	}
	public void ListaDependente() throws SQLException {
		listaDependente = new ListaDependente();
	}

	public void novoDependente() {
		cadDependente = new CadDependente();
	}
	
	public void criaDependente(Dependente dependente) throws SQLException {
		dao.create(dependente);
	}

	public void deletaDependente(int id) throws SQLException {
		dao.delete(id);
	}
	
	
		
	
}