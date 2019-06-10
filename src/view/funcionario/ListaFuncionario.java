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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.DependenteController;
import controller.FilialController;
import controller.FuncionarioController;
import model.dao.DependenteDAO;
import model.dao.FilialDAO;
import model.dao.FuncionarioDAO;
import model.vo.Cargo;
import model.vo.Filial;
import model.vo.Funcionario;

public class ListaFuncionario extends JFrame implements ActionListener {
	private static FuncionarioController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;

	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSair;
	private JButton btnRemover;

	private JTable tblFuncionario;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnPesquisar;
	private JTextField txtPesquisar;

	public ListaFuncionario(){
		
		super("Funcionarios");
		geraTabela();
		geraTela();
	}
	
	public void geraTela() {
		btnNovo = new JButton("Novo");
		btnEditar = new JButton("Editar");
		btnRemover = new JButton("Remover");
		btnPesquisar = new JButton("Pesquisar");
		btnSair = new JButton("Sair");
		
		txtPesquisar = new JTextField(10);
		
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();
		
		panelGrid.setLayout(new GridBagLayout());
		contentPanel.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());
		
		barraRolagem = new JScrollPane(tblFuncionario);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnPesquisar,gbc);
		panelGrid.add(txtPesquisar,gbc);
		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnEditar, gbc);
		panelGrid.add(btnRemover, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(barraRolagem, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnPesquisar.addActionListener(this);
		btnNovo.addActionListener(this);
		btnEditar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		
		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("Lista de Funcionários");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	private void geraTabela() {
		tblFuncionario = new JTable(modelo);
		modelo.addColumn("Matricula");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Data Nasc");

		tblFuncionario.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblFuncionario.getColumnModel().getColumn(3).setPreferredWidth(150);
		pesquisar(modelo);
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		control = new FuncionarioController();
		for (Funcionario f : control.getFuncionarios()) {
			modelo.addRow(new Object[] {
					f.getMatricula(),
					f.getNome(),
					f.getCpf(),
					f.getDatanasc(),
			});
		}
	}
	public void AtualizaTabela(String nome) {
		LimpaTabela();
		PopulaTabela(nome);		
	}
	
	public void LimpaTabela() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}
	
	public void PopulaTabela(String nome) {
		for (Funcionario f : control.pesquisaFuncionarios(nome)) {
			modelo.addRow(new Object[] {
					f.getMatricula(),
					f.getNome(),
					f.getCpf(),
					f.getDatanasc(),
			});
		}
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
			int linhaSelecionada = -1;
            linhaSelecionada = tblFuncionario.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblFuncionario.getValueAt(linhaSelecionada, 0);
            	control.deletaFuncionario(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
		if (fonte == btnEditar) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblFuncionario.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblFuncionario.getValueAt(linhaSelecionada, 0);
            	control.editaFuncionario(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if(fonte == btnPesquisar) {
			AtualizaTabela(txtPesquisar.getText());
		}
	}
}
