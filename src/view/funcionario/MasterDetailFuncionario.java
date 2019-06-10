package view.funcionario;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DependenteController;
import controller.FuncionarioController;
import model.vo.Dependente;
import model.vo.Funcionario;

public class MasterDetailFuncionario extends JFrame implements ActionListener {
	private static FuncionarioController controlFuncionario;
	private static DependenteController controlDependente;

	private JFrame janela;
	private JPanel contentPanel;
	private Container containerFuncionario;
	private Container containerDependente;

	private FlowLayout flowLayout;

	private JTable tblFuncionario;
	private JTable tblDependente;
	
	private JButton btnRefresh;

	private JScrollPane barraRolagemFuncionario;
	private JScrollPane barraRolagemDependente;

	private DefaultTableModel modeloFuncionario = new DefaultTableModel();
	private DefaultTableModel modeloDependente = new DefaultTableModel();

	public MasterDetailFuncionario() {

		super("MasterFuncionarios");
		geraTabelaFuncionario();
		geraTabelaDependente();
		geraTela();
	}

	private void geraTabelaFuncionario() {
		tblFuncionario = new JTable(modeloFuncionario);
		modeloFuncionario.addColumn("Matricula");
		modeloFuncionario.addColumn("Nome");
		modeloFuncionario.addColumn("CPF");
		modeloFuncionario.addColumn("Data Nasc");

		tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(3).setPreferredWidth(150);
		pesquisarFuncionario(modeloFuncionario);
	}

	private void geraTabelaDependente() {
		tblDependente = new JTable(modeloDependente);
		modeloDependente.addColumn("Codigo");
		modeloDependente.addColumn("Nome");
		modeloDependente.addColumn("CPF");
		modeloDependente.addColumn("DataNasc");
		modeloDependente.addColumn("Funcionario");

		tblDependente.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(3).setPreferredWidth(150);
		pesquisarDependente(modeloDependente);
	}

	public void geraTela() {
		janela = new JFrame();
		contentPanel = new JPanel();
		containerFuncionario = new JPanel();
		containerDependente = new JPanel();
		flowLayout = new FlowLayout();
		btnRefresh = new JButton("Atualiza");

		contentPanel.setLayout(flowLayout);
		containerFuncionario.setLayout(flowLayout);
		containerDependente.setLayout(flowLayout);

		barraRolagemFuncionario = new JScrollPane(tblFuncionario);
		barraRolagemDependente = new JScrollPane(tblDependente);

		containerFuncionario.add(barraRolagemFuncionario);
		containerDependente.add(barraRolagemDependente);

		contentPanel.add(btnRefresh);
		contentPanel.add(containerFuncionario);
		contentPanel.add(containerDependente);
		
		btnRefresh.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("MasterDetail Funcionario");
		janela.setSize(1100, 300);
		janela.setVisible(true);
	}

	public static void pesquisarFuncionario(DefaultTableModel modeloFuncionario) {
		modeloFuncionario.setNumRows(0);
		controlFuncionario = new FuncionarioController();
		for (Funcionario f : controlFuncionario.getFuncionarios()) {
			modeloFuncionario.addRow(new Object[] { f.getMatricula(), f.getNome(), f.getCpf(), f.getDatanasc(), });
		}
	}

	public static void pesquisarDependente(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		controlDependente = new DependenteController();
		for (Dependente d : controlDependente.getDependentes()) {
			modelo.addRow(new Object[] {
					d.getId(),
					d.getNome(),
					d.getCpf(),
					d.getDataNasc(),
					d.getFuncionario(),
					
			});
		}
	}
	
	public void AtualizaTabela(int matricula) {
		LimpaTabela();
		PopulaTabela(matricula);		
	}
	
	public void LimpaTabela() {
		while (modeloDependente.getRowCount() > 0) {
			modeloDependente.removeRow(0);
		}
			
	}
	
	public void PopulaTabela(int matricula) {
		List<String> listaDepentende = controlDependente.getListaDependente(matricula);
		for (int i = 0; i < listaDepentende.size(); i = i +5) {
			modeloDependente.addRow(new Object [] {
					listaDepentende.get(i),
					listaDepentende.get(i+1),
					listaDepentende.get(i+2),
					listaDepentende.get(i+3),
					listaDepentende.get(i+4)
					
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlFuncionario = new FuncionarioController();
		controlDependente = new DependenteController();

		if (fonte == btnRefresh) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblFuncionario.getSelectedRow();
            if (linhaSelecionada >= 0) {
            int matricula = (int) tblFuncionario.getValueAt(linhaSelecionada, 0);
			AtualizaTabela(matricula);
            }
		}
	}

}
