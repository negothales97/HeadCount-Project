package controller;

import java.sql.SQLException;

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
	
		
}
