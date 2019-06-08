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
import javax.swing.JTable;

import controller.DepartamentoController;
import controller.FilialController;
import controller.FuncionarioController;
import model.vo.Departamento;
import model.vo.Filial;
import model.vo.Funcionario;

public class RelCustoFuncionario extends JFrame implements ActionListener {

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
	
	private JButton btnFiltrar;

	private JTable tblFuncionario;

	private FuncionarioController controlFuncionario;
	private DepartamentoController controlDepart;
	private FilialController filialControl;

	public RelCustoFuncionario() throws SQLException {

		String[] colunas = { "Filial", "Departamento", "Observação", "Custo (R$)" };

		Object[][] dados = new Object[1][4];

		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();

		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();

		panelGrid.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		container.setLayout(new FlowLayout());

		lblFilial = new JLabel("Filial");
		lblDepartamento = new JLabel("Departamento");
		
		btnFiltrar = new JButton("Filtrar");

		cmbFilial = new JComboBox<String>();
		cmbDepartamento = new JComboBox<String>();

		cmbFilial.addItem("SELECIONE....");
		cmbDepartamento.addItem("SELECIONE....");


		try {
			controlDepart = new DepartamentoController();
			List<Departamento> masterDepartamento = controlDepart.getDepartamentos();
			for (int i = 0; i < masterDepartamento.size(); i++) {
				cmbDepartamento.addItem(masterDepartamento.get(i).getNome());
			}
			filialControl = new FilialController();
			List<Filial> master = filialControl.comboBoxFilial();
			for (int i = 0; i < master.size(); i++) {

				cmbFilial.addItem(master.get(i).getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblFuncionario = new JTable(dados, colunas);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(lblFilial, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(lblDepartamento, gbc);
		panelGrid.add(cmbDepartamento, gbc);
		panelGrid.add(btnFiltrar, gbc);

		container.add(tblFuncionario);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		janela.setContentPane(contentPanel);
		janela.setTitle("Relatorio do Custo de Funcionario");
		janela.setSize(800, 600);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlFuncionario = new FuncionarioController();
		
		if (fonte == btnFiltrar) {
			try {
				String txtDepartamento = cmbDepartamento.getSelectedItem().toString();
				String txtFilial = cmbFilial.getSelectedItem().toString();
				List<String> relFuncionario = controlFuncionario.getRelFuncionarios(txtFilial, txtDepartamento);
				String[][] dados = new String[(relFuncionario.size())/3][3];
				for (int i = 0; i < relFuncionario.size(); i = i +3) {
					dados[i][0] = relFuncionario.get(i);
					dados[i][1] = relFuncionario.get(i+1);
					dados[i][2] = relFuncionario.get(i+2);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
