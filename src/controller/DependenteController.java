package controller;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import model.vo.Dependente;


public class DependenteController {
	public static boolean SalvarDependente(String Codigo,String Nome, String CPF, String DataNasc, String Parentesco){
        Dependente a = new Dependente(Codigo, Nome, CPF, DataNasc, Parentesco);
        try {
			return a.Persistir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
    }
	
	
}