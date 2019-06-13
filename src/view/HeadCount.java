package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.CargoController;
import controller.DepartamentoController;
import controller.FilialController;
import controller.FuncionarioController;
import controller.DependenteController;

public class HeadCount implements ActionListener {
	private JFrame janela;
	private JMenuBar menuBar;
	private JMenu crudMenu;
	private JMenu custoMenu;

	private JMenuItem cadFil;
	private JMenuItem cadDeprt;
	private JMenuItem cadFunc;
	private JMenuItem cadDep;
	private JMenuItem cadCar;

	private JMenuItem custoDepart;
	private JMenuItem custoFunc;

	private FilialController controlFil;
	private FuncionarioController controlFun;
	private DepartamentoController controlDeprt;
	private DependenteController controlDep;
	private CargoController controlCar;
	private JMenu relMenu;
	private JMenuItem relFunc;
	private JMenuItem masterDetailFD;

	public HeadCount() {

		janela = new JFrame();

		janela.setTitle("HeadCount");
		janela.setSize(150, 150);

		menuBar = new JMenuBar();

		janela.setJMenuBar(menuBar);
		crudMenu = new JMenu("Cadastro");
		custoMenu = new JMenu("Custos");
		relMenu = new JMenu("Relatorios");

		menuBar.add(crudMenu);
		menuBar.add(custoMenu);
		menuBar.add(relMenu);

		cadFil = new JMenuItem("Filial");
		cadDeprt = new JMenuItem("Departamento");
		cadFunc = new JMenuItem("Funcionario");
		cadDep = new JMenuItem("Dependente");
		cadCar = new JMenuItem("Cargo");

		custoFunc = new JMenuItem("Custo p/ Funcionario");
		custoDepart = new JMenuItem("Custo p/ Departamento");

		relFunc = new JMenuItem("Relatório Custo Funcionario");
		masterDetailFD = new JMenuItem("MasterDetail Func x Depend");

		crudMenu.add(cadFil);
		crudMenu.add(cadFunc);
		crudMenu.add(cadDeprt);
		crudMenu.add(cadDep);
		crudMenu.add(cadCar);

		custoMenu.add(custoFunc);
		custoMenu.add(custoDepart);

		relMenu.add(relFunc);
		relMenu.add(masterDetailFD);

		cadFil.addActionListener(this);
		cadDeprt.addActionListener(this);
		cadFunc.addActionListener(this);
		cadDep.addActionListener(this);
		cadCar.addActionListener(this);
		custoFunc.addActionListener(this);
		custoDepart.addActionListener(this);
		relFunc.addActionListener(this);
		masterDetailFD.addActionListener(this);

		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlFil = new FilialController();
		controlFun = new FuncionarioController();
		controlDeprt = new DepartamentoController();
		controlCar = new CargoController();
		controlDep = new DependenteController();
		
		if (fonte == cadFil) {
				controlFil.listaFilial();
		}if (fonte == cadFunc) {
			controlFun.listaFuncionario();
		}if (fonte == cadDeprt) {
			controlDeprt.listaDepartamento();
		}if (fonte == cadDep) {
			controlDep.listaDependente();
		}if (fonte == cadCar) {
				controlCar.listaCargo();
		}if (fonte == custoFunc) {
			controlFun.registraCustoFuncionario();
		}if (fonte == custoDepart) {
			controlDeprt.registraCustoDepartamento();
		}if(fonte == relFunc) {
			controlFun.relCustoFunc();
		}if(fonte == masterDetailFD) {
			controlFun.masterDetailFuncionario();
		}
	}
}