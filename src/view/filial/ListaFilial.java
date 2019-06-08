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

import controller.FilialController;
import model.dao.FilialDAO;
import model.vo.Filial;

public class ListaFilial extends JFrame implements ActionListener {
	private FilialController control;
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;

	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnSair;

	private JTable tblFilial;
	private JScrollPane barraRolagem;
	private String[] colunas = { "Codigo", "Nome", "CNPJ", "Insc. Estadual", "Rua", "Num", "Bairro" };
	private List<Filial> filiais;
	private Object[][] dados;

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

		tblFilial = new JTable(dados, colunas);
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
