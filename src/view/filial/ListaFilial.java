package view.filial;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.FilialController;
import model.vo.Cargo;
import model.vo.Filial;

public class ListaFilial extends JFrame implements ActionListener {
	private static FilialController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;

	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnSair;
	
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tblFilial;
	private JScrollPane barraRolagem;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;

	public ListaFilial() {
		super("Filiais");
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
		
		barraRolagem = new JScrollPane(tblFilial);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(btnPesquisar,gbc);
		panelGrid.add(txtPesquisar, gbc);
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
		btnPesquisar.addActionListener(this);
		
		janela.setContentPane(contentPanel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setTitle("Lista de Filiais");
		janela.setSize(700, 300);
		janela.setVisible(true);
	}
	
	private void geraTabela() {

		tblFilial = new JTable(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nome");
		modelo.addColumn("CNPJ");
		modelo.addColumn("Insc. Estadual");
		modelo.addColumn("Rua");
		modelo.addColumn("Num");
		modelo.addColumn("Bairro");

		tblFilial.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblFilial.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(6).setPreferredWidth(150);
		pesquisar(modelo);
	}

	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		control = new FilialController();
		for (Filial f : control.getFiliais()) {
			modelo.addRow(new Object[] {
					f.getId(),
					f.getNome(),
					f.getCnpj(),
					f.getInscEstadual(),
					f.getEndereco().getRua(),
					f.getEndereco().getNumero(),
					f.getEndereco().getBairro(),
					
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
		for (Filial f : control.pesquisaFiliais(nome)) {
			modelo.addRow(new Object[] {
					f.getId(),
					f.getNome(),
					f.getCnpj(),
					f.getInscEstadual(),
					f.getEndereco().getRua(),
					f.getEndereco().getNumero(),
					f.getEndereco().getBairro(),
					
			});
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FilialController();

		if (fonte == btnNovo) {
			control.novaFilial();
			janela.dispose();

		}
		if(fonte== btnEditar) {
            int linhaSelecionada = -1;
            linhaSelecionada = tblFilial.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblFilial.getValueAt(linhaSelecionada, 0);
            	control.editaFilial(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnRemover) {
			int linhaSelecionada = -1;
            linhaSelecionada = tblFilial.getSelectedRow();
            if (linhaSelecionada >= 0) {
            	int id = (int) tblFilial.getValueAt(linhaSelecionada, 0);
            	control.deletaFilial(id);
            	janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                "É necesário selecionar uma linha.");
            }
		}
		if (fonte == btnSair) {
			janela.dispose();
		}
		if(fonte == btnPesquisar) {
			AtualizaTabela(txtPesquisar.getText());
		}

	}
}
