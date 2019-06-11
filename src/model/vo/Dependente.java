package model.vo;

public class Dependente {

	private int id;
	private String nome;
	private String cpf;
	private String dataNasc;
	private Funcionario funcionario;


	public Dependente(String nome, String cpf, String dataNasc, Funcionario funcionario) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.funcionario = funcionario;
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;

	}
	
	public String toString(){
		return getNome();
	}
}