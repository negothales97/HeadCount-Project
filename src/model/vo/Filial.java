package model.vo;

public class Filial {
	private int id;
	private String nome;
	private String cnpj;
	private String InscEstadual;
	private Endereco endereco;
	
	public Filial(String nome, String cnpj, String InscEstadual) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.InscEstadual = InscEstadual;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscEstadual() {
		return InscEstadual;
	}
	public void setInscEstadual(String inscEstadual) {
		InscEstadual = inscEstadual;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
