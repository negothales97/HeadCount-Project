package controller;

import java.sql.SQLException;
import java.util.List;

import model.connection.DAOException;
import model.dao.CargoDAO;
import model.vo.Cargo;
import model.vo.Filial;
import view.cargo.CadCargo;
import view.cargo.EditCargo;
import view.cargo.ListaCargo;
import view.filial.EditFilial;

public class CargoController {
	private ListaCargo listaCargo = null;
	private CadCargo cadCargo = null;
	private CargoDAO dao = null;
	private EditCargo editCargo; 
	
	public CargoController() {
		dao = new CargoDAO();
	}
	public void listaCargo() {
		listaCargo = new ListaCargo();
	}

	public void novoCargo() {
		cadCargo = new CadCargo();
	}
	
	public void criaCargo(Cargo cargo){
		try {
			dao.create(cargo);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaCargo();
	}

	public void deletaCargo(int id) {
		try {
			dao.delete(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		this.listaCargo();
	}
	
	public List<Cargo> getCargos(){
			List<Cargo> cargos = null;
			try {
				cargos = dao.getCargos();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		
			return cargos;
	}
	
	public void editaCargo(int id) {
		try {
			Cargo cargo = dao.getCargo(id);
			editCargo = new EditCargo(cargo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCargo(Cargo cargo) {
		try {
			dao.update(cargo);
			this.listaCargo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
