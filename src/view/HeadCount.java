package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

	public HeadCount() {

		janela = new JFrame();

		janela.setTitle("HeadCount");
		janela.setSize(150, 150);

		menuBar = new JMenuBar();

		janela.setJMenuBar(menuBar);
		crudMenu = new JMenu("Cadastro");
		custoMenu = new JMenu("Incluir custos");

		menuBar.add(crudMenu);
		menuBar.add(custoMenu);

		cadFil = new JMenuItem("Filial");
		cadDeprt = new JMenuItem("Departamento");
		cadFunc = new JMenuItem("Funcionario");
		cadDep = new JMenuItem("Dependente");
		cadCar = new JMenuItem("Cargo");

		custoFunc = new JMenuItem("Custo p/ Funcionario");
		custoDepart = new JMenuItem("Custo p/ Departamento");

		crudMenu.add(cadFil);
		crudMenu.add(cadFunc);
		crudMenu.add(cadDeprt);
		crudMenu.add(cadDep);
		crudMenu.add(cadCar);

		custoMenu.add(custoFunc);
		custoMenu.add(custoDepart);

		cadFil.addActionListener(this);
		cadDeprt.addActionListener(this);
		cadFunc.addActionListener(this);
		cadDep.addActionListener(this);
		cadCar.addActionListener(this);
		custoFunc.addActionListener(this);
		custoDepart.addActionListener(this);

		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == cadFil) {
			controlFil = new FilialController();
			try {
				controlFil.listaFilial();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (fonte == cadFunc) {
			controlFun = new FuncionarioController();
			try {
				controlFun.listaFuncionario();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (fonte == cadDeprt) {
			controlDeprt = new DepartamentoController();
			try {
				controlDeprt.listaDepartamento();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (fonte == cadDep) {
			controlDep = new DependenteController();
			try {
				controlDep.ListaDependente();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (fonte == cadCar) {
			controlCar = new CargoController();
			try {
				controlCar.listaCargo();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (fonte == custoFunc) {
			controlFun = new FuncionarioController();
			controlFun.registraCustoFuncionario();
		} else if (fonte == custoDepart) {
			controlDeprt = new DepartamentoController();
			controlDeprt.registraCustoDepartamento();
		}

	}
}