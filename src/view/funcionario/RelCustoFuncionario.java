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
import javax.swing.table.DefaultTableModel;

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

	private JButton btnFiltrar;
	private JComboBox<Filial> cmbFilial;
	private JComboBox<Departamento> cmbDepartamento;

	private JLabel lblFilial;
	private JLabel lblDepartamento;
		
	private String[] colunas = { "Matricula", "Funcionario", "Custo (R$)" };

	private JTable tblFuncionario;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();

	private static FuncionarioController controlFuncionario;
	private DepartamentoController controlDepart;
	private FilialController filialControl;

	

	public RelCustoFuncionario()  {
		super("Relatório de custos de funcionários");
		geraTabela();
		geraTela();		
	}


		
	public void geraTela() {
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();

		panelGrid.setLayout(new GridBagLayout());
		contentPanel.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());

		lblFilial = new JLabel("Filial");
		lblDepartamento = new JLabel("Departamento");
		
		btnFiltrar = new JButton("Filtrar");

		cmbFilial = new JComboBox<>();
		cmbDepartamento = new JComboBox<>();

		try {
			controlDepart = new DepartamentoController();
			List<Departamento> masterDepartamento = controlDepart.getDepartamentos();
			for (int i = 0; i < masterDepartamento.size(); i++) {
				cmbDepartamento.addItem(masterDepartamento.get(i));
			}
			filialControl = new FilialController();
			List<Filial> master = filialControl.getFiliais();
			for (int i = 0; i < master.size(); i++) {

				cmbFilial.addItem(master.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		barraRolagem = new JScrollPane(tblFuncionario);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(lblFilial, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(lblDepartamento, gbc);
		panelGrid.add(cmbDepartamento, gbc);
		panelGrid.add(btnFiltrar, gbc);
		container.add(barraRolagem, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);
		
		btnFiltrar.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("Lista de Funcionários");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	
	public void geraTabela() {
		tblFuncionario = new JTable(modelo);
		modelo.addColumn("Matricula");
		modelo.addColumn("Nome");
		modelo.addColumn("Custo");		

		tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
		pesquisar(modelo);
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		controlFuncionario = new FuncionarioController();
		List <String> custosFuncionarios = controlFuncionario.getCustosTodosFuncionarios();
		for (int i = 0; i < custosFuncionarios.size(); i = i +3) {
			modelo.addRow(new Object [] {
					custosFuncionarios.get(i),
					custosFuncionarios.get(i+1),
					custosFuncionarios.get(i+2)
			});
		}
	}
	
	public void AtualizaTabela(int filial, int departamento) {
		LimpaTabela();
		PopulaTabela(filial,departamento);		
	}
	
	public void LimpaTabela() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
			
	}
	
	public void PopulaTabela(int filial, int departamento) {
		List<String> relFuncionario = controlFuncionario.getRelFuncionarios(filial, departamento);
		for (int i = 0; i < relFuncionario.size(); i = i +3) {
			modelo.addRow(new Object [] {
					relFuncionario.get(i),
					relFuncionario.get(i+1),
					relFuncionario.get(i+2)
			});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlFuncionario = new FuncionarioController();
		
		if (fonte == btnFiltrar) {
			Filial filial = (Filial) cmbFilial.getSelectedItem();
			Departamento departamento = (Departamento) cmbDepartamento.getSelectedItem();
			AtualizaTabela(filial.getId(), departamento.getId());
		}
	}

}
