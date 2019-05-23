package view.dependente;

<<<<<<< HEAD
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
=======
import java.awt.Container;
import java.awt.FlowLayout;
>>>>>>> master
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
<<<<<<< HEAD
import javax.swing.JPanel;
=======
>>>>>>> master
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DependenteController;
<<<<<<< HEAD
=======
import controller.DependenteController;
>>>>>>> master
import model.dao.DependenteDAO;
import model.vo.Dependente;

public class ListaDependente extends JFrame implements ActionListener {
	private DependenteController control;
<<<<<<< HEAD
	private DependenteDAO dao;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
=======
	private JFrame janela;
	
>>>>>>> master
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
<<<<<<< HEAD

	private JTable tblDependente;

	private JScrollPane barraRolagem;

	public ListaDependente() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "CPF", "Data Nasc", "Parentesco" };
		dao = new DependenteDAO();

		List<Dependente> dependentes = dao.read("Ka");
		Object[][] dados = new Object[dependentes.size()][5];
		for (int i = 0; i < dependentes.size(); i++) {
			Dependente Dependente = dependentes.get(i);
			dados[i][0] = Dependente.getid();
			dados[i][1] = Dependente.getNome();
			dados[i][2] = Dependente.getCpf();
			dados[i][3] = Dependente.getDataNasc();
			dados[i][4] = Dependente.getParentesco();
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

		tblDependente = new JTable(dados, colunas);
		tblDependente.setSize(container.getWidth(), container.getHeight());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnSair, gbc);
		panelGrid.add(btnRemover, gbc);
		panelGrid.add(txtPesquisar, gbc);
		panelGrid.add(btnPesquisar, gbc);
		container.add(tblDependente, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		btnPesquisar.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de dependentes");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
=======
	
	private JTable tblDependente;
	
	private JScrollPane barraRolagem;
	
	public ListaDependente() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "Centro de Custo", "Orçamento"};
		DependenteDAO dao = new DependenteDAO();
	
			List<Dependente> dependentes = dao.read();
			Object[][] dados = new Object[dependentes.size()][4];   
	           for (int i = 0; i < dependentes.size(); i++) {   
	        	   Dependente dependente = dependentes.get(i);   
	               dados [i][0] = dependente.getid();   
	               dados[i][1] = dependente.getNome();   
	               dados[i][2] = dependente.getCpf();
	               dados[i][3] = dependente.getDataNasc();
	           } 
		
			janela 	= new JFrame();
			setLayout(new FlowLayout());
			Container c = getContentPane();
			barraRolagem = new JScrollPane();
			
			btnNovo 		= new JButton("Novo");
			btnRemover 		= new JButton("Remover");
			btnSair 		= new JButton("Sair");
			btnPesquisar	= new JButton("Pesquisar");
			
			txtPesquisar 	= new JTextField(10);
			
			
			tblDependente= new JTable(dados, colunas);
			tblDependente.getWidth();
		
			c.add(tblDependente);
			c.add(btnNovo);
			c.add(btnSair);
			c.add(btnRemover);
			c.add(txtPesquisar);
			c.add(btnPesquisar);
			
			btnNovo.addActionListener(this);
			btnRemover.addActionListener(this);
			btnSair.addActionListener(this);
			
			
			janela.setContentPane(c);
			janela.setTitle("Lista de Dependentes");
			janela.setSize(600,400);
			janela.setVisible(true);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
>>>>>>> master

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DependenteController();
<<<<<<< HEAD

		if (fonte == btnNovo) {
			control.novaDependente();
			janela.dispose();

		}
		if (fonte == btnRemover) {

			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo a ser removido"));
			try {
=======
		if (fonte == btnNovo) {
            control.novoDependente();
        }if(fonte == btnRemover) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o código a ser removido"));
        	try {
>>>>>>> master
				control.deletaDependente(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
<<<<<<< HEAD
			janela.dispose();
			JOptionPane.showMessageDialog(null, "Dependente Removida com sucesso");
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
		if (fonte == btnPesquisar) {
			txtPesquisar.getText();
		}

=======
        }
		if(fonte == btnSair) {
			System.exit(0);
		}
			
>>>>>>> master
	}
}
