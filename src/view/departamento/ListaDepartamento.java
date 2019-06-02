package view.departamento;

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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DepartamentoController;
import controller.FilialController;
import model.dao.DepartamentoDAO;
import model.dao.FilialDAO;
import model.vo.Departamento;

public class ListaDepartamento extends JFrame implements ActionListener {
	private DepartamentoController control;
	private DepartamentoDAO dao;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;

	private JTable tblDepartamento;

	private JScrollPane barraRolagem;

	public ListaDepartamento() throws SQLException {
		String[] colunas = { "ID", "Nome", "Centro de Custo", "Or�amento (R$)" };
		DepartamentoDAO dao = new DepartamentoDAO();

		List<Departamento> departamentos = dao.read();
		Object[][] dados = new Object[departamentos.size()][4];
		for (int i = 0; i < departamentos.size(); i++) {
			Departamento departamento = departamentos.get(i);
			dados[i][0] = departamento.getId();
			dados[i][1] = departamento.getNome();
			dados[i][2] = departamento.getCentroCusto();
			dados[i][3] = departamento.getOrcamento();
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

		tblDepartamento = new JTable(dados, colunas);
		tblDepartamento.setSize(container.getWidth(), container.getHeight());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnSair, gbc);
		panelGrid.add(btnRemover, gbc);
		container.add(tblDepartamento, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Departamentos");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DepartamentoController();

		if (fonte == btnNovo) {
			control.novoDepartamento();
			janela.dispose();

		}
		if (fonte == btnRemover) {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o c�digo a ser removido"));
			try {
				control.deletaDepartamento(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			janela.dispose();
			JOptionPane.showMessageDialog(null, "Departamento removido com sucesso");

		}
		if (fonte == btnSair) {
			janela.dispose();

		}

	}
}
