package view.dependente;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.DependenteController;
import controller.FilialController;
import model.dao.DependenteDAO;
import model.vo.Dependente;
import model.vo.Filial;

public class ListaDependente extends JFrame implements ActionListener {
	private static DependenteController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnSair;
	private JButton btnRemover;

	private JTable tblDependente;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ListaDependente(){
		super("Dependentes");
		geraTabela();
		geraTela();
		
		

	}

	public void geraTela() {
		btnNovo = new JButton("Novo");
		btnRemover = new JButton("Remover");
		btnSair = new JButton("Sair");
		btnEditar = new JButton ("Editar");
		
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();
		
		panelGrid.setLayout(new GridBagLayout());
		contentPanel.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());
		
		barraRolagem = new JScrollPane(tblDependente);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnEditar, gbc);
		panelGrid.add(btnRemover, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(barraRolagem, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnEditar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		
		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("Lista de Dependentes");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	
	private void geraTabela() {
		tblDependente = new JTable(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Funcionario");

		tblDependente.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDependente.getColumnModel().getColumn(3).setPreferredWidth(150);
		pesquisar(modelo);
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		control = new DependenteController();
		for (Dependente d : control.getDependentes()) {
			modelo.addRow(new Object[] {
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
		control = new DependenteController();

		if (fonte == btnNovo) {
			control.novoDependente();
			janela.dispose();

		}
		if(fonte == btnEditar) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblDependente.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblDependente.getValueAt(linhaSelecionada, 0);
            	control.editaDependete(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnRemover) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblDependente.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblDependente.getValueAt(linhaSelecionada, 0);
            	control.deletaDependente(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
	}
}
