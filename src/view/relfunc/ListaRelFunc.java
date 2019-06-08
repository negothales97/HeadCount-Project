package view.relfunc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.CargoController;
import controller.DepartamentoController;
import controller.FilialController;
import controller.FuncionarioController;
import controller.RelFuncController;
import model.dao.CargoDAO;
import model.vo.Cargo;
import model.vo.Departamento;
import model.vo.Filial;
import model.vo.Funcionario;

public class ListaRelFunc extends JFrame implements ActionListener {
	private DepartamentoController control;
	private FilialController controller;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
	
	
	private JComboBox<String> cmbFilial;
	private JComboBox<String> cmbDepartamento;
	private JButton btnSair;
	private JButton btnFiltrar;

	private JTable tblCargo;


	public ListaRelFunc() throws SQLException {
		String[] colunas = { "Codigo", "Cargo" };
//
//		List<Cargo> filiais = control.getC();
//		Object[][] dados = new Object[filiais.size()][3];
//		for (int i = 0; i < filiais.size(); i++) {
//			Cargo cargo = filiais.get(i);
//			dados[i][0] = cargo.getId();
//			dados[i][1] = cargo.getNome();
//		}

		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();

		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();

		panelGrid.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		container.setLayout(new FlowLayout());
		
		cmbFilial = new JComboBox<String>();
		
		cmbFilial.addItem("SELECIONE....");
		try {
			controller = new FilialController();
			List<Filial> master= controller.getFiliais();
			for (int i = 0; i< master.size(); i++) {
				cmbFilial.addItem(master.get(i).getNome());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		cmbDepartamento = new JComboBox<String>();
		
		cmbDepartamento.addItem("SELECIONE....");
		try {
			control = new DepartamentoController();
			List<Departamento> master= control.getDepartamentos();
			for (int i = 0; i< master.size(); i++) {
				cmbFilial.addItem(master.get(i).getNome());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		btnSair = new JButton("Sair");
		btnFiltrar = new JButton("Filtrar");


//		tblCargo = new JTable(dados, colunas);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnSair, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(cmbDepartamento, gbc);
		panelGrid.add(btnFiltrar, gbc);
		container.add(tblCargo, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnSair.addActionListener(this);
		btnFiltrar.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Relatórios");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new RelFuncController();

		if (fonte == btnSair) {
			janela.dispose();
		}
		if (fonte == btnFiltrar) {
			String departamento = cmbDepartamento.getSelectedItem().toString();
			String filial = cmbFilial.getSelectedItem().toString();
			control.listaRelFunc(departamento, filial);
		}

	}
}
