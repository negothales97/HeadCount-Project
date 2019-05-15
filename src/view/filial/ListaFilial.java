package view.filial;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.FilialController;
import model.dao.FilialDAO;
import model.vo.Filial;

public class ListaFilial extends JFrame implements ActionListener {
	private FilialController control;
	private JFrame janela;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
	
	private JTable tblFilial;
	
	private JScrollPane barraRolagem;
	
	public ListaFilial() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "CNPJ", "Insc. Estadual"};
		FilialDAO dao = new FilialDAO();
	
			List<Filial> filiais = dao.read();
			Object[][] dados = new Object[filiais.size()][4];   
	           for (int i = 0; i < filiais.size(); i++) {   
	        	   Filial filial = filiais.get(i);   
	               dados [i][0] = filial.getId();   
	               dados[i][1] = filial.getNome();   
	               dados[i][2] = filial.getCnpj();
	               dados[i][3] = filial.getInscEstadual();
	           } 
		
			janela 	= new JFrame();
			setLayout(new FlowLayout());
			Container c = getContentPane();
			barraRolagem = new JScrollPane();
			
			btnNovo 		= new JButton("Novo");
			btnRemover 		= new JButton("Remover");
			btnSair 		= new JButton("Sair");
			btnPesquisar	= new JButton("Pesquisar");
			
			txtPesquisar 	= new JTextField(10);
			
			
			tblFilial = new JTable(dados, colunas);
			tblFilial.getWidth();
		
			c.add(tblFilial);
			c.add(btnNovo);
			c.add(btnSair);
			c.add(btnRemover);
			c.add(txtPesquisar);
			c.add(btnPesquisar);
			
			btnNovo.addActionListener(this);
			btnRemover.addActionListener(this);
			btnSair.addActionListener(this);
			
			
			janela.setContentPane(c);
			janela.setTitle("Lista de Filiais");
			janela.setSize(600,400);
			janela.setVisible(true);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FilialController();
		if (fonte == btnNovo) {
            control.novaFilial();
        }if(fonte == btnRemover) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o código a ser removido"));
        	try {
				control.deletaFilial(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		if(fonte == btnSair) {
			System.exit(0);
		}
			
	}
}
