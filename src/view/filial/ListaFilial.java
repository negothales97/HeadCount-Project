package view.filial;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class ListaFilial extends JFrame {
	private JFrame janela;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnPesquisar;
	
	private JTable tblFilial;
	
	public ListaFilial() {
		janela 	= new JFrame();
		setLayout(new GridLayout(0, 3));
		Container c = getContentPane();
		
		btnNovo 		= new JButton("Novo");
		btnSair 		= new JButton("Sair");
		btnPesquisar	= new JButton("Pesquisar");
		Object[][] data = {
				{"Um","Dois","Treis"},
				{"Um","Dois","Treis"},
				{"Um","Dois","Treis"},
				{"Um","Dois","Treis"}
				};
		String[] coluna = { "Codigo", "Nome", "CNPJ", };
		tblFilial = new JTable(data, coluna);
		c.add(btnNovo);
		c.add(btnPesquisar);
		c.add(btnSair);
		c.add(tblFilial);
		
		
		janela.setContentPane(c);
		janela.setTitle("Lista de Filiais");
		janela.setSize(600,400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
