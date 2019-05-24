package view.funcionario;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DependenteController;
import controller.FilialController;
import controller.FuncionarioController;
import model.dao.DependenteDAO;
import model.dao.FilialDAO;
import model.dao.FuncionarioDAO;
import model.vo.Filial;
import model.vo.Funcionario;

public class ListaFuncionario extends JFrame implements ActionListener {
	private FuncionarioController control;
	private DependenteDAO dao;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;

	private JTable tblFuncionario;

	private JScrollPane barraRolagem;

	public ListaFuncionario() throws SQLException {
		String[] colunas = { "Matricula", "Nome", "CPF", "Data Nasc" };
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

		btnNovo = new JButton("Novo");
		btnRemover = new JButton("Remover");
		btnSair = new JButton("Sair");
		btnPesquisar = new JButton("Pesquisar");

		txtPesquisar = new JTextField(10);

		tblFuncionario = new JTable(dados, colunas);
		tblFuncionario.setSize(container.getWidth(), container.getHeight());
		
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnSair, gbc);
		panelGrid.add(btnRemover, gbc);
		panelGrid.add(txtPesquisar, gbc);
		panelGrid.add(btnPesquisar, gbc);
		container.add(tblFuncionario, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		btnPesquisar.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Filiais");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FuncionarioController();

		if (fonte == btnNovo) {
			control.novoFuncionario();
			janela.dispose();

		}
		if (fonte == btnRemover) {

			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo a ser removido"));
			try {
				control.deletaFuncionario(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			janela.dispose();
			JOptionPane.showMessageDialog(null, "Funcionario Removida com sucesso");
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
		if (fonte == btnPesquisar) {
			txtPesquisar.getText();
		}

	}
}
