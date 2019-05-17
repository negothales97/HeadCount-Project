package model.vo;

public class Departamento {
	private int id;
	private String nome;
	private String CentroCusto;
	private Float Orcamento;
	
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
		return CentroCusto;
	}
	public void setCentroCusto(String centroCusto) {
		CentroCusto = centroCusto;
	}
	public Float getOrcamento() {
		return Orcamento;
	}
	public void setOrcamento(Float orcamento) {
		Orcamento = orcamento;
	}
}