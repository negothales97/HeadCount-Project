package controller;

import view.filial.CadFilial;
import view.filial.ListaFilial;

public class FilialController {
	private ListaFilial cadFilial = null;
	
	public FilialController() {
		cadFilial = new ListaFilial();
	}

	public void recebeDados(String nome, String cnpj, String inscEstadual) {
		System.out.println(nome);
		System.out.println(cnpj);
		System.out.println(inscEstadual);
	}
	
	
		
}
