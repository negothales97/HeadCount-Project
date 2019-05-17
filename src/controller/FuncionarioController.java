package controller;

import view.funcionario.CadFuncionario;

public class FuncionarioController {
	
	public FuncionarioController() {
		CadFuncionario cadFunc = new CadFuncionario();
	}
	public void recebeDados(String nome, String matricula, String cargo, String salario,String cpf, String dataNasc, String setor, String status) {
		System.out.println(nome);
		System.out.println(matricula);
		System.out.println(cargo);
		System.out.println(cpf);
		System.out.println(dataNasc);
		System.out.println(setor);
		System.out.println(status);
		System.out.println(salario);

	}

}