package model.vo;

public class Funcionario {
	int matricula;
	String nome;
	String cpf;
	String datanasc;
	String status;
	int cargo_id;
	int setor_id;
	double salario;
	
	public Funcionario(String nome, String cpf, String datanasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public int getCargo_id() {
		return cargo_id;
	}
	public void setCargo(int cargo_id) {
		this.cargo_id = cargo_id;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDatanasc() {
		return datanasc;
	}
	public void setDatanasc(String datanasc) {
		this.datanasc = datanasc;
	}
	public int getSetor_id() {
		return setor_id;
	}
	public void setSetor_id(int setor_id) {
		this.setor_id = setor_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}