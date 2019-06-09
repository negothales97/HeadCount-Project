package model.vo;

public class Dependente {

	private int id;
	private String nome;
	private String cpf;
	private String dataNasc;
	private int funcionario_id;


	public Dependente(String nome, String cpf, String dataNasc, int funcionario_id) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.funcionario_id = funcionario_id;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getFuncionario() {
		return funcionario_id;
	}

	public void setFuncionario(int funcionario_id) {
		this.funcionario_id = funcionario_id;

	}
	
	public String toString(){
		return getNome();
	}
}