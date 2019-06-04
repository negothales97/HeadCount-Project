package view.dependente;

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

import controller.DependenteController;
import model.dao.DependenteDAO;
import model.vo.Dependente;

public class ListaDependente extends JFrame implements ActionListener {
	private DependenteController control;
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

	private JTable tblDependente;

	public ListaDependente() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "CPF", "Funcionario" };
		dao = new DependenteDAO();

		List<Dependente> dependentes = dao.read();
		Object[][] dados = new Object[dependentes.size()][4];
		for (int i = 0; i < dependentes.size(); i++) {
			Dependente dependente = dependentes.get(i);
			dados[i][0] = dependente.getId();
			dados[i][1] = dependente.getNome();
			dados[i][2] = dependente.getCpf();
			dados[i][3] = dependente.getFuncionario();
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


		btnNovo = new JButton("Novo");
		btnRemover = new JButton("Remover");
		btnSair = new JButton("Sair");

		tblDependente = new JTable(dados, colunas);
		tblDependente.setSize(container.getWidth(), container.getHeight());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnSair, gbc);
		panelGrid.add(btnRemover, gbc);
		container.add(tblDependente, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Filiais");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DependenteController();

		if (fonte == btnNovo) {
			control.novoDependente();
			janela.dispose();

		}
		if (fonte == btnRemover) {

			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo a ser removido"));
			try {
				control.deletaDependente(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			janela.dispose();
			JOptionPane.showMessageDialog(null, "Dependente Removida com sucesso");
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
	}
}
