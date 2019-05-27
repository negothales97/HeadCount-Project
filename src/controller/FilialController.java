package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.FilialDAO;
import model.vo.Filial;
import view.filial.CadFilial;
import view.filial.EditFilial;
import view.filial.ListaFilial;

public class FilialController  {
	private ListaFilial listaFilial = null;
	private CadFilial cadFilial = null;
	private EditFilial editFilial = null;
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
	
	public String[] comboBoxFilial() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "CNPJ", "Insc. Estadual" };

		List<Filial> filiais = dao.read();
		String [] master = new String[filiais.size()];
		Object[][] dados = new Object[filiais.size()][4];
		for (int i = 0; i < filiais.size(); i++) {
			Filial filial = filiais.get(i);
			dados[i][0] = filial.getId();
			dados[i][1] = filial.getNome();
			dados[i][2] = filial.getCnpj();
			dados[i][3] = filial.getInscEstadual();
			master[i] = filial.getNome();
		}
		return master;
	}
		
}
