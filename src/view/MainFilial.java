package view;

import java.sql.SQLException;

import controller.FilialController;
import model.dao.FilialDAO;
import model.vo.Filial;

public class MainFilial {

	public static void main(String[] args) {
//		FilialController filial = new FilialController();
		
		Filial filial = new Filial();
		filial.setId(3);
//		filial.setCnpj("01.0234/0789-04");
//		filial.setInscEstadual("0705080910");
//		
		FilialDAO dao = new FilialDAO();
		try {
			dao.delete(filial);
			System.out.println("Deletado com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
