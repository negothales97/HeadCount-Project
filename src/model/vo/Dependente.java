package model.vo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Dependente {
	
	private String id;
	private String nome;
	private String cpf;
	private String dataNasc;
	private String parentesco;
	
	public Dependente() {
		
	}
	
	public Dependente(String id, String nome, String cpf, String dataNasc, String parentesco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.parentesco = parentesco;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
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
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	
	}
	
	public boolean Persistir() throws IOException {
		FileWriter arq = new FileWriter("c:\\temp\\dependente.txt");
		PrintWriter gravarArq =new PrintWriter(arq);
		gravarArq.println(id);
		gravarArq.println(nome);
		gravarArq.println(cpf);
		gravarArq.println(dataNasc);
		gravarArq.println(parentesco);
		arq.close();
		return true;
	}
	
	public static ArrayList<Dependente> getDependente(){
		ArrayList<Dependente> dependente = new ArrayList();
		return dependente;
	}
		
	

}