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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.DepartamentoController;
import controller.FilialController;
import model.dao.DepartamentoDAO;
import model.dao.FilialDAO;
import model.vo.Departamento;

public class ListaDepartamento extends JFrame implements ActionListener {
	private static DepartamentoController control;
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

	private JTable tblDepartamento;

	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();

	public ListaDepartamento(){
		super("Departamentos");
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
		
		barraRolagem = new JScrollPane(tblDepartamento);
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
		janela.setTitle("Lista de Filiais");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	
	private void geraTabela(){
		tblDepartamento = new JTable(modelo);
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Centro de Custo");
		modelo.addColumn("Orcamento");
		
		tblDepartamento.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblDepartamento.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblDepartamento.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDepartamento.getColumnModel().getColumn(3).setPreferredWidth(150);
		pesquisar(modelo);
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		control = new DepartamentoController();
		for (Departamento d : control.getDepartamentos()) {
			modelo.addRow(new Object[] {
					d.getId(),
					d.getNome(),
					d.getCentroCusto(),
					"R$ "+d.getOrcamento(),
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DepartamentoController();

		if (fonte == btnNovo) {
			control.novoDepartamento();
			janela.dispose();

		}
		if(fonte == btnEditar) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblDepartamento.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblDepartamento.getValueAt(linhaSelecionada, 0);
            	control.editaDepartamento(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnRemover) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblDepartamento.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblDepartamento.getValueAt(linhaSelecionada, 0);
            	control.deletaDepartamento(id);
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
