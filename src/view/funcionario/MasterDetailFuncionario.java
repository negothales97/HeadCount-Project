package view.funcionario;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private JScrollPane barraRolagemFuncionario;
	private JScrollPane barraRolagemDependente;

	private DefaultTableModel modeloFuncionario = new DefaultTableModel();
	private DefaultTableModel modeloDependente = new DefaultTableModel();

	public MasterDetailFuncionario(){
		
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
		modeloDependente.addColumn("Funcionario");

		tblDependente.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(1).setPreferredWidth(100);
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
		
		contentPanel.setLayout(flowLayout);
		containerFuncionario.setLayout(flowLayout);
		containerDependente.setLayout(flowLayout);
		
		barraRolagemFuncionario = new JScrollPane(tblFuncionario);
		barraRolagemDependente = new JScrollPane(tblDependente);

		containerFuncionario.add(barraRolagemFuncionario);
		containerDependente.add(barraRolagemDependente);

		contentPanel.add(containerFuncionario);
		contentPanel.add(containerDependente);
		
		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("MasterDetail Funcionario");
		janela.setSize(1000, 300);
		janela.setVisible(true);
	}
	
	public static void pesquisarFuncionario(DefaultTableModel modeloFuncionario) {
		modeloFuncionario.setNumRows(0);
		controlFuncionario = new FuncionarioController();
		for (Funcionario f : controlFuncionario.getFuncionarios()) {
			modeloFuncionario.addRow(new Object[] {
					f.getMatricula(),
					f.getNome(),
					f.getCpf(),
					f.getDatanasc(),
			});
		}
	}
	
	public static void pesquisarDependente(DefaultTableModel modeloDependente) {
		modeloDependente.setNumRows(0);
		controlDependente = new DependenteController();
		for (Dependente d : controlDependente.getDependentes()) {
			modeloDependente.addRow(new Object[] {
					d.getId(),
					d.getNome(),
					d.getCpf(),
					d.getFuncionario(),
					
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		controlFuncionario = new FuncionarioController();
		controlDependente = new DependenteController();


//		if (fonte == btnNovo) {
//			control.novoFuncionario();
//			janela.dispose();
//
//		}
//		if (fonte == btnRemover) {
//			int linhaSelecionada = -1;
//            linhaSelecionada = tblFuncionario.getSelectedRow();
//            if (linhaSelecionada >= 0) {
//            	int id = (int) tblFuncionario.getValueAt(linhaSelecionada, 0);
//            	control.deletaFuncionario(id);
//            	janela.dispose();
//            } else {
//                JOptionPane.showMessageDialog(null, 
//                "E necessario selecionar uma linha.");
//            }
//			
//		}
//		if (fonte == btnSair) {
//			janela.dispose();
//		}
//		if (fonte == btnEditar) {
//			int linhaSelecionada = -1;
//            linhaSelecionada = tblFuncionario.getSelectedRow();
//            if (linhaSelecionada >= 0) {
//            	int id = (int) tblFuncionario.getValueAt(linhaSelecionada, 0);
//            	control.editaFuncionario(id);
//            	janela.dispose();
//            } else {
//                JOptionPane.showMessageDialog(null, 
//                "E necessario selecionar uma linha.");
//            }
//		}
//
	}
}
