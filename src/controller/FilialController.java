package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FilialDAO;
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
		listaFilial.geraTela();
	}
	

	public void novaFilial() {
		cadFilial = new CadFilial();
	}
	
	public void criaFilial(Filial filial) {
		try {
			dao.create(filial);
			this.listaFilial();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editaFilial(int id) {
		try {
			Filial filial = dao.getFilial(id);
			editFilial = new EditFilial(filial);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFilial(Filial filial) {
		try {
			dao.update(filial);
			this.listaFilial();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletaFilial(int id) throws SQLException {
		dao.delete(id);
		this.listaFilial();
	}
	
	public List<Filial> getFiliais(){
		List<Filial> filiais = null;
		try {
			filiais = dao.getFiliais();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filiais;
	}
		
}
