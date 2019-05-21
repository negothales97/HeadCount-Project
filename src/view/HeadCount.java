package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.DepartamentoController;
import controller.FilialController;
import controller.FuncionarioController;
import controller.DependenteController;


public class HeadCount implements ActionListener{
	private JFrame janela;
	private JMenuBar menuBar;
	private JMenu crudMenu;
	private JMenu relMenu;

	private JMenuItem cadFil;
	private JMenuItem  cadDeprt;
	private JMenuItem cadFunc;
	private JMenuItem cadDep;

	private JMenuItem relCustoFunc;
	private JMenuItem relCustoDeprt;
	private JMenuItem relCustoEmp;
	
	private FilialController controlFil;
	private FuncionarioController controlFun;
	private DepartamentoController controlDeprt;
	private DependenteController controlDep;
	
	public HeadCount() {

		janela 			= new JFrame();

		janela.setTitle("HeadCount");
		janela.setSize(150, 150);

		menuBar 		= new JMenuBar();

		janela.setJMenuBar(menuBar);
		crudMenu 		= new JMenu("Cadastro");
		relMenu 		= new JMenu("Relatório");

		menuBar.add(crudMenu);
		menuBar.add(relMenu);

		cadFil 			= new JMenuItem("Filial");
		cadDeprt 		= new JMenuItem("Departamento");
		cadFunc 		= new JMenuItem("Funcionário");
		cadDep 			= new JMenuItem("Dependente");
		
		relCustoFunc 	= new JMenuItem("Custo p/ Função");
		relCustoDeprt 	= new JMenuItem("Custo p/ Departamento");
		relCustoEmp 	= new JMenuItem("Custo p/ Empresa");

		crudMenu.add(cadFil);
		crudMenu.add(cadFunc);
		crudMenu.add(cadDeprt);
		crudMenu.add(cadDep);

		relMenu.add(relCustoEmp);
		relMenu.add(relCustoDeprt);
		relMenu.add(relCustoFunc);
		
		cadFil.addActionListener(this);
		cadDeprt.addActionListener(this);
		cadFunc.addActionListener(this);
		cadDep.addActionListener(this);

		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadFil) {
			controlFil = new FilialController();
			try {
				controlFil.listaFilial();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(fonte == cadFunc) {
			controlFun = new FuncionarioController();
			try {
				controlFun.listaFuncionario();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(fonte == cadDeprt) {
			controlDeprt = new DepartamentoController();
			try {
				controlDeprt.listaDepartamento();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(fonte == cadDep) {
			controlDep = new DependenteController();
			try {
				controlDep.listaDependente();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}