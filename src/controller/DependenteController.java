package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

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
		if(validaCampos(dependente)) {
			try {
				dao.create(dependente);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			this.listaDependente();
		}
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
	
	public List<Dependente> pesquisaDependentes(String nome) {
		List<Dependente> dependentes = null;
		try {
			dependentes = dao.pesquisar(nome);
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
		if(validaCampos(dependente)) {
			try {
				dao.update(dependente);
				this.listaDependente();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean validaCampos(Dependente dependente) {
		boolean campos = true;
		if(dependente.getNome().equals("") || dependente.getNome().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(dependente.getDataNasc().equals("") || dependente.getDataNasc().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(dependente.getCpf().equals("") || dependente.getCpf().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		}
		return campos;
	}
	
}