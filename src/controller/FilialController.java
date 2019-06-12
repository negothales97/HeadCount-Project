package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import model.connection.DAOException;
import model.dao.FilialDAO;
import model.vo.Cargo;
import model.vo.Filial;
import view.filial.CadFilial;
import view.filial.EditFilial;
import view.filial.ListaFilial;

public class FilialController  {
	private ListaFilial listaFilial;
	private CadFilial cadFilial;
	private EditFilial editFilial;
	private FilialDAO dao;
	
	public FilialController() {
		dao = new FilialDAO();
	}
	public void listaFilial(){
		listaFilial = new ListaFilial();
	}
	

	public void novaFilial() {
		cadFilial = new CadFilial();
	}
	
	public boolean criaFilial(Filial filial) {
		if(validaCampos(filial)) {
			try {
				dao.create(filial);
				this.listaFilial();
				return true;
			} catch (DAOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public void editaFilial(int id) {
		try {
			Filial filial = dao.getFilial(id);
			editFilial = new EditFilial(filial);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateFilial(Filial filial) {
		if(validaCampos(filial)) {
			try {
				dao.update(filial);
				this.listaFilial();
				return true;
			} catch (DAOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	public List<Filial> pesquisaFiliais(String nome){
		List<Filial> filiais = null;
		try {
			filiais = dao.pesquisar(nome);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return filiais;
	}

	public void deletaFilial(int id) {
		try {
			dao.delete(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaFilial();
	}
	
	public List<Filial> getFiliais(){
		List<Filial> filiais = null;
		try {
			filiais = dao.getFiliais();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return filiais;
	}
	
	public boolean validaCampos(Filial filial) {
		boolean campos = true;
		if(filial.getNome().equals("") || filial.getNome().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(filial.getCnpj().equals("") || filial.getCnpj().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(filial.getEndereco().getBairro().equals("") || filial.getEndereco().getBairro().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(filial.getEndereco().getRua().equals("") || filial.getEndereco().getRua().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		} else if(filial.getEndereco().getNumero().equals("") || filial.getEndereco().getNumero().equals(null)) {
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			campos = false;
		}
		return campos;
	}
}
