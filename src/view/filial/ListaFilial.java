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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.FilialController;
import model.dao.FilialDAO;
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
	private List<Filial> filiais;
	private Object[][] dados;
	private JScrollPane barraRolagem;

	public ListaFilial() {
		
	}
	
	public void geraTela() {
		
		control = new FilialController();
		filiais = control.getFiliais();
		dados = new Object[filiais.size()][7];
		for (int i = 0; i < filiais.size(); i++) {
			Filial filial = filiais.get(i);
			dados[i][0] = filial.getId();
			dados[i][1] = filial.getNome();
			dados[i][2] = filial.getCnpj();
			dados[i][3] = filial.getInscEstadual();
			dados[i][4] = filial.getEndereco().getRua();
			dados[i][5] = filial.getEndereco().getNumero();
			dados[i][6] = filial.getEndereco().getBairro();
			
		}
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();
		
		panelGrid.setLayout(new GridBagLayout());
		contentPanel.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());

		btnNovo = new JButton("Novo");
		btnRemover = new JButton("Remover");
		btnSair = new JButton("Sair");
		btnEditar = new JButton ("Editar");
		
		barraRolagem = new JScrollPane(tblFilial);


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
		janela.setTitle("Lista de Filiais");
		janela.setSize(700, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		tblFilial.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblFilial.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblFilial.getColumnModel().getColumn(5).setPreferredWidth(60);
		tblFilial.getColumnModel().getColumn(5).setPreferredWidth(100);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FilialController();

		if (fonte == btnNovo) {
			control.novaFilial();
			janela.dispose();

		}
		if(fonte== btnEditar) {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo a ser editado"));
			control.editaFilial(id);
			janela.dispose();
			
		}
		if (fonte == btnRemover) {

			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo a ser removido"));
			try {
				control.deletaFilial(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			janela.dispose();
			JOptionPane.showMessageDialog(null, "Filial Removida com sucesso");
		}
		if (fonte == btnSair) {
			janela.dispose();
		}

	}
}
