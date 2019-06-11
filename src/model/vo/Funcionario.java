package model.vo;

public class Funcionario {
	int matricula;
	String nome;
	String cpf;
	String datanasc;
	Cargo cargo;
	Departamento departamento;
	Filial filial;

	public Funcionario(int matricula, String nome, String cpf, String datanasc, Cargo cargo, Departamento departamento, Filial filial) {
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		this.cargo = cargo;
		this.departamento = departamento;
		this.filial = filial;
	}
	public Funcionario(String nome, String cpf, String datanasc, Cargo cargo, Departamento departamento, Filial filial) {
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		this.cargo = cargo;
		this.departamento = departamento;
		this.filial = filial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento_id(Departamento departamento) {
		this.departamento = departamento;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public int getMatricula() {
		return matricula;
	}

	public String toString(){
		return getNome();
	}
}