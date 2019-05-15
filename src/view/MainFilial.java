package view;

import java.sql.SQLException;

import controller.FilialController;
import model.dao.FilialDAO;
import model.vo.Filial;

public class MainFilial {

	public static void main(String[] args) throws SQLException {
		
		FilialController filial = new FilialController();
		filial.listaFilial();
		
	}

}
