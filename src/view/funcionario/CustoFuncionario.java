package view.funcionario;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.FuncionarioController;
import controller.FilialController;
import model.dao.FuncionarioDAO;
import model.dao.FilialDAO;
import model.vo.Funcionario;

public class CustoFuncionario extends JFrame implements ActionListener {
	private FuncionarioController control;
	private FuncionarioDAO dao;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JComboBox<String> cmbFilial;
	private JComboBox<String> cmbFuncionario;
	private JLabel lblFilial;
	private JLabel lblFuncionario;
	private JLabel lblOBS;
	private JLabel lblCusto;
	private JTextField txtOBS;
	private JTextField txtCusto;
	private JButton btnSalvar;
	private JButton btnSair;

	private JTable tblFuncionario;

	private JScrollPane barraRolagem;

	public CustoFuncionario() throws SQLException {
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Orçamento (R$)" };
		FuncionarioDAO dao = new FuncionarioDAO();

		List<Funcionario> funcionarios = dao.read();
		Object[][] dados = new Object[funcionarios.size()][4];
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			dados[i][0] = funcionario.getMatricula();
			dados[i][1] = funcionario.getNome();
			dados[i][2] = funcionario.getCpf();
			dados[i][3] = funcionario.getDatanasc();
		}

		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();

		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();

		panelGrid.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		container.setLayout(new FlowLayout());

		barraRolagem = new JScrollPane();
		
		lblFilial = new JLabel("Filial");
		lblFuncionario = new JLabel("Funcionario");
		lblOBS = new JLabel("Observcao");
		lblCusto = new JLabel("Custo");
		
		cmbFilial = new JComboBox<String>();
		cmbFuncionario = new JComboBox<String>();
		
		txtOBS = new JTextField(10);
		txtCusto  = new JTextField(10);

		btnSalvar = new JButton("Salvar");
		btnSair = new JButton("Sair");


		tblFuncionario = new JTable(dados, colunas);
		tblFuncionario.setSize(container.getWidth(), container.getHeight());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		
		panelGrid.add(lblFilial, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(lblFuncionario, gbc);
		panelGrid.add(cmbFuncionario, gbc);
		panelGrid.add(lblOBS, gbc);
		panelGrid.add(txtOBS, gbc);
		panelGrid.add(lblCusto, gbc);
		panelGrid.add(txtCusto, gbc);
		panelGrid.add(btnSalvar, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(tblFuncionario, gbc);
		
		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnSalvar.addActionListener(this);
		btnSair.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Funcionarios");
		janela.setSize(800, 600);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FuncionarioController();

		if (fonte == btnSalvar) {
			janela.dispose();

		}
		if (fonte == btnSair) {
			janela.dispose();

		}

	}
}
