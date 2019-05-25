package model.vo;

public class Cargo {
	private int id;
	private String cargo;
	
	public Cargo(String cargo) {
		this.cargo = cargo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
