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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DepartamentoController;
import controller.FilialController;
import model.vo.Departamento;
import model.vo.Filial;

public class CustoDepartamentoView extends JFrame implements ActionListener {
	
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JComboBox<String> cmbFilial;
	private JComboBox<String> cmbDepartamento;
	
	private JLabel lblFilial;
	private JLabel lblDepartamento;
	private JLabel lblObs;
	private JLabel lblCusto;
	
	private JTextField txtObs;
	private JTextField txtCusto;
	private JButton btnSalvar;
	private JButton btnSair;

	private JTable tblDepartamento;

	private JScrollPane barraRolagem;
	private DepartamentoController controlDepart;
	private FilialController filialControl;

	public CustoDepartamentoView() throws SQLException {
		String[] colunas = { "Filial", "Departamento", "Observação", "Custo (R$)" };
		janela 			= new JFrame();
		contentPanel 	= new JPanel();
		panelGrid 		= new JPanel();
		container 		= new JPanel();

		boderLayout 	= new BorderLayout();
		gbLayout 		= new GridBagLayout();

		panelGrid.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		container.setLayout(new FlowLayout());

		barraRolagem 	= new JScrollPane();
		
		lblFilial 		= new JLabel("Filial");
		lblDepartamento = new JLabel("Departamento");
		lblObs 			= new JLabel("Observcao");
		lblCusto 		= new JLabel("Custo");
		
		cmbFilial 		= new JComboBox<String>();
		cmbDepartamento = new JComboBox<String>();
		
		cmbDepartamento.addItem("SELECIONE....");
		cmbFilial.addItem("SELECIONE....");
		try {
			controlDepart = new DepartamentoController();
			List<Departamento> masterDepartamento = controlDepart.getDepartamentos();
			for (int i = 0; i< masterDepartamento.size(); i++) {
				cmbDepartamento.addItem(masterDepartamento.get(i).getNome());
			}
			filialControl = new FilialController();
			List<Filial> master = filialControl.comboBoxFilial();
			  for (int i = 0; i < master.size(); i++) {
				  
				  cmbFilial.addItem(master.get(i).getNome());
			  }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		txtObs = new JTextField(10);
		txtCusto  = new JTextField(10);

		btnSalvar = new JButton("Salvar");
		btnSair = new JButton("Sair");


		tblDepartamento = new JTable();
		tblDepartamento.setSize(container.getWidth(), container.getHeight());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		
		panelGrid.add(lblFilial, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(lblDepartamento, gbc);
		panelGrid.add(cmbDepartamento, gbc);
		panelGrid.add(lblObs, gbc);
		panelGrid.add(txtObs, gbc);
		panelGrid.add(lblCusto, gbc);
		panelGrid.add(txtCusto, gbc);
		panelGrid.add(btnSalvar, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(tblDepartamento, gbc);
		
		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnSalvar.addActionListener(this);
		btnSair.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Lista de Departamentos");
		janela.setSize(800, 600);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlDepart = new DepartamentoController();
		if (fonte == btnSalvar) {
			double custo = Double.parseDouble(txtCusto.getText());
			int filial_id = cmbFilial.getSelectedIndex();
			int departamento_id = cmbDepartamento.getSelectedIndex();
			controlDepart.incluiCusto(filial_id, departamento_id, txtObs.getText(), custo);
			janela.dispose();
		}
		if (fonte == btnSair) {
			janela.dispose();

		}

	}
}
