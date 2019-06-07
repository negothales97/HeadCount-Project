package controller;

import java.sql.SQLException;
import java.util.List;

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
	
	public List<Cargo> getCargos() throws SQLException {
			String[] colunas = { "Codigo", "Cargo" };
			dao = new CargoDAO();

			List<Cargo> cargos = dao.getCargos();
		
			Object[][] dados = new Object[cargos.size()][4];
			for (int i = 0; i < cargos.size(); i++) {
				Cargo cargo = cargos.get(i);
				dados[i][0] = cargo.getId();
				dados[i][1] = cargo.getNome();
			
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
