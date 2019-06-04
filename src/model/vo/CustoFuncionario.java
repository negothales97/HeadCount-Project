package model.vo;

public class CustoFuncionario {
	int id;
	int funcionario_id;
	String observacao;
	double custo;
	
	public CustoFuncionario(int id, int funcionario_id, String observacao, double custo){
		this.id = id;
		this.funcionario_id = funcionario_id;
		this.custo = custo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFuncionario_id() {
		return funcionario_id;
	}
	public void setFuncionario_id(int funcionario_id) {
		this.funcionario_id = funcionario_id;
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
}
