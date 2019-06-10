package view.cargo;

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

import controller.CargoController;
import model.vo.Cargo;

public class ListaCargo extends JFrame implements ActionListener {
	private static CargoController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnEditar;

	private JTable tblCargo;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnPesquisar;
	private JTextField txtPesquisar;


	public ListaCargo() {
		super("Cargos");
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
		
		barraRolagem = new JScrollPane(tblCargo);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnPesquisar,gbc);
		panelGrid.add(txtPesquisar, gbc);
		panelGrid.add(btnNovo, gbc);
		panelGrid.add(btnEditar, gbc);
		panelGrid.add(btnRemover, gbc);
		panelGrid.add(btnSair, gbc);
		container.add(barraRolagem,gbc);
		
		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);

		btnNovo.addActionListener(this);
		btnEditar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnSair.addActionListener(this);
		btnPesquisar.addActionListener(this);
		
		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("Lista de Cargos");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	
	private void geraTabela() {
		tblCargo = new JTable(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Cargo");
		
		tblCargo.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblCargo.getColumnModel().getColumn(1).setPreferredWidth(70);
		pesquisar(modelo);
		
	}
	
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		control = new CargoController();
		for (Cargo c : control.getCargos()) {
			modelo.addRow(new Object[] {
					c.getId(),
					c.getNome(),
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
		for (Cargo c : control.pesquisaCargos(nome)) {
			modelo.addRow(new Object[] {
					c.getId(),
					c.getNome(),
			});
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new CargoController();

		if (fonte == btnNovo) {
			control.novoCargo();
			janela.dispose();

		}
		if (fonte == btnRemover) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblCargo.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblCargo.getValueAt(linhaSelecionada, 0);
            	control.deletaCargo(id);
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
            linhaSelecionada = tblCargo.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblCargo.getValueAt(linhaSelecionada, 0);
            	control.editaCargo(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}if (fonte == btnPesquisar) {
			AtualizaTabela(txtPesquisar.getText());
		}

	}
}
