package model.vo;

public class CustoDepartamento {
	int id;
	int filial_id;
	int departamento_id;
	String observacao;
	double custo;
	Departamento departamento;
	Filial filial;

	
	public CustoDepartamento(int id, int filial_id, int departamento_id, 
			String observacao, double custo, Departamento departamento, Filial filial){
		this.id = id;
		this.filial_id = filial_id;
		this.departamento_id = departamento_id;
		this.custo = custo;
		this.departamento = departamento;
		this.filial = filial;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilial_id() {
		return filial_id;
	}
	public void setFilial_id(int filial_id) {
		this.filial_id = filial_id;
	}
	public int getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(int departamento_id) {
		this.departamento_id = departamento_id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
