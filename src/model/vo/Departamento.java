package model.vo;

public class Departamento {
	private int id;
	private String nome;
	private String centroCusto;
	private double orcamento;
	
	public Departamento(String nome, String centroCusto, double orcamento) {
		this.nome = nome;
		this.centroCusto = centroCusto;
		this.orcamento = orcamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public String getCentroCusto() {
		return centroCusto;
	}
	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}
	public double getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}
	
	public String toString(){
		return getNome();
	}
}