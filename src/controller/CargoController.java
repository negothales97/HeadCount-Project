package controller;

import java.sql.SQLException;
import java.util.List;

import model.dao.CargoDAO;
import model.vo.Cargo;
import view.cargo.CadCargo;
import view.cargo.ListaCargo;

public class CargoController {
	private ListaCargo listaCargo = null;
	private CadCargo cadCargo = null;
	private CargoDAO dao = null; 
	
	public CargoController() {
		dao = new CargoDAO();
	}
	public void listaCargo() throws SQLException {
		listaCargo = new ListaCargo();
	}

	public void novoCargo() {
		cadCargo = new CadCargo();
	}
	
	public void criaCargo(Cargo cargo) throws SQLException {
		dao.create(cargo);
		this.listaCargo();
	}

	public void deletaCargo(int id) throws SQLException {
		dao.delete(id);
		this.listaCargo();
	}
	
	public String [] comboBoxCargo() throws SQLException {
			String[] colunas = { "Codigo", "Cargo" };
			dao = new CargoDAO();

			List<Cargo> cargos = dao.read("");
			String [] masterCargos = new String[cargos.size()];
			Object[][] dados = new Object[cargos.size()][4];
			for (int i = 0; i < cargos.size(); i++) {
				Cargo cargo = cargos.get(i);
				dados[i][0] = cargo.getId();
				dados[i][1] = cargo.getCargo();
				masterCargos[i]= cargo.getCargo();
			}
			return masterCargos;
	}
		
}
