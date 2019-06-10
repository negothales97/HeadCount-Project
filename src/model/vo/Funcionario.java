package model.vo;

public class Funcionario {
	int matricula;
	String nome;
	String cpf;
	String datanasc;
	int cargo_id;
	int departamento_id;
	double salario;
	int filial_id;
	
	public Funcionario(int matricula, String nome, String cpf, String datanasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		this.matricula = matricula;
	}
	public Funcionario(String nome, String cpf, String datanasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		
	}
	public Funcionario(int matricula, String nome, String cpf, String datanasc, int cargo_id, int departamento_id, int filial_id) {
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		this.matricula = matricula;
	}
	public Funcionario(String nome, String cpf, String datanasc, int cargo_id, int departamento_id, int filial_id) {
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
	public int getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(int departamento_id) {
		this.departamento_id = departamento_id;
	}
	public int getFilial_id() {
		return filial_id;
	}
	public void setFilial_id(int filial_id) {
		this.filial_id = filial_id;
	}
	public int getMatricula() {
		return matricula;
	}

	public String toString(){
		return getNome();
	}
}