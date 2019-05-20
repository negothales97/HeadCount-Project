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
	private FilialDAO dao;	
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
	
	private JTable tblFilial;
	
	private JScrollPane barraRolagem;
	
	public ListaFilial() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "CNPJ", "Insc. Estadual"};
			dao = new FilialDAO();
	
			List<Filial> filiais = dao.read("Ka");
			Object[][] dados = new Object[filiais.size()][4];   
	           for (int i = 0; i < filiais.size(); i++) {   
	        	   Filial filial = filiais.get(i);   
	               dados[i][0] = filial.getId();   
	               dados[i][1] = filial.getNome();   
	               dados[i][2] = filial.getCnpj();
	               dados[i][3] = filial.getInscEstadual();
	           } 
		
			janela 	= new JFrame();
			contentPanel = new JPanel();
			panelGrid = new JPanel();
			container = new JPanel();
			
			boderLayout = new BorderLayout();
			gbLayout = new GridBagLayout();
			
			
			panelGrid.setLayout(gbLayout);
			contentPanel.setLayout(boderLayout);
			container.setLayout(new FlowLayout());
			
			barraRolagem = new JScrollPane();
			
			
			btnNovo 		= new JButton("Novo");
			btnRemover 		= new JButton("Remover");
			btnSair 		= new JButton("Sair");
			btnPesquisar	= new JButton("Pesquisar");
			
			txtPesquisar 	= new JTextField(10);
			
			
			tblFilial = new JTable(dados, colunas);
			tblFilial.setSize(container.getWidth(), container.getHeight());

			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.insets=new Insets(5,5,5,5);
			
			panelGrid.add(btnNovo, gbc);
			panelGrid.add(btnSair, gbc );
			panelGrid.add(btnRemover, gbc);
			panelGrid.add(txtPesquisar, gbc);
			panelGrid.add(btnPesquisar,gbc );
			container.add(tblFilial, gbc);

			contentPanel.add(BorderLayout.NORTH, panelGrid);
			contentPanel.add(BorderLayout.CENTER, container);
			
			btnNovo.addActionListener(this);
			btnRemover.addActionListener(this);
			btnSair.addActionListener(this);
			btnPesquisar.addActionListener(this);
			
			
			janela.setContentPane(contentPanel);
			janela.setTitle("Lista de Filiais");
			janela.setSize(600,400);
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
            
        }if(fonte == btnRemover) {
        	
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o c�digo a ser removido"));
        	try {
				control.deletaFilial(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	janela.dispose();
        	JOptionPane.showMessageDialog(null, "Filial Removida com sucesso");
        }if(fonte == btnSair) {
			janela.dispose();
		}if(fonte == btnPesquisar) {
			txtPesquisar.getText();
		}
			
	}
}
