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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.FuncionarioController;
import model.connection.DAOException;
import model.vo.CustoFuncionario;
import model.vo.Departamento;
import model.vo.Funcionario;

public class CustoFuncionarioView extends JFrame implements ActionListener {
	private FuncionarioController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JComboBox<Funcionario> cmbFuncionario;
	private JLabel lblFuncionario;
	private JLabel lblObs;
	private JLabel lblCusto;
	private JTextField txtObs;
	private JTextField txtCusto;
	private JButton btnSalvar;
	private JButton btnSair;

	private JTable tblFuncionario;

	private JScrollPane barraRolagem;

	public CustoFuncionarioView() throws DAOException {
		
		control= new FuncionarioController();
		String[] colunas = { "ID", "Nome", "Observacao", "Custo" };

		
		List<CustoFuncionario> custos = control.getCustoFuncionarios();
		Object[][] dados = new Object[custos.size()][4];
		for (int i = 0; i < custos.size(); i++) {
			CustoFuncionario custo = custos.get(i);
			dados[i][0] = custo.getId();
			dados[i][1] = custo.getFuncionario();
			dados[i][2] = custo.getObservacao();
			dados[i][3] = custo.getCusto();
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

		lblFuncionario = new JLabel("Funcionario");
		lblObs = new JLabel("Observacao");
		lblCusto = new JLabel("Custo");
		
		cmbFuncionario = new JComboBox<Funcionario>();
		
		try {
			control = new FuncionarioController();
			List<Funcionario> master= control.getFuncionarios();
			for (int i = 0; i< master.size(); i++) {
				cmbFuncionario.addItem(master.get(i));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		txtObs = new JTextField(10);
		txtCusto  = new JTextField(10);

		btnSalvar = new JButton("Salvar");
		btnSair = new JButton("Sair");


		tblFuncionario = new JTable(dados, colunas);
		barraRolagem = new JScrollPane(tblFuncionario);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		
		panelGrid.add(lblFuncionario, gbc);
		panelGrid.add(cmbFuncionario, gbc);
		panelGrid.add(lblObs, gbc);
		panelGrid.add(txtObs, gbc);
		panelGrid.add(lblCusto, gbc);
		panelGrid.add(txtCusto, gbc);
		panelGrid.add(btnSalvar, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(barraRolagem, gbc);
		
		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnSalvar.addActionListener(this);
		btnSair.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Funcionarios");
		janela.setSize(1000, 800);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FuncionarioController();

		if (fonte == btnSalvar) {
			double custo = Double.parseDouble(txtCusto.getText());
			Funcionario funcionario = (Funcionario) cmbFuncionario.getSelectedItem();
			control.incluiCusto(funcionario.getMatricula(), txtObs.getText(), custo);
			janela.dispose();

		}
		if (fonte == btnSair) {
			janela.dispose();

		}

	}
}
