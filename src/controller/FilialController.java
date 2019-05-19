package controller;

import java.sql.SQLException;

import model.dao.FilialDAO;
import model.vo.Filial;
import view.filial.CadFilial;
import view.filial.ListaFilial;

public class FilialController {
	private ListaFilial listaFilial = null;
	private CadFilial cadFilial = null;
	private FilialDAO dao = null; 
	
	public FilialController() {
		dao = new FilialDAO();
	}
	public void listaFilial() throws SQLException {
		listaFilial = new ListaFilial();
	}

	public void novaFilial() {
		cadFilial = new CadFilial();
	}
	
	public void criaFilial(Filial filial) throws SQLException {
		dao.create(filial);
		this.listaFilial();
	}

	public void deletaFilial(int id) throws SQLException {
		dao.delete(id);
		this.listaFilial();
	}
	
		
}
