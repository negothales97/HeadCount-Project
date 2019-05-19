package view.dependente;

import java.awt.Container;
import java.awt.FlowLayout;
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

import controller.DependenteController;
import controller.DependenteController;
import model.dao.DependenteDAO;
import model.vo.Dependente;

public class ListaDependente extends JFrame implements ActionListener {
	private DependenteController control;
	private JFrame janela;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
	
	private JTable tblDependente;
	
	private JScrollPane barraRolagem;
	
	public ListaDependente() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "Centro de Custo", "Orçamento"};
		DependenteDAO dao = new DependenteDAO();
	
			List<Dependente> dependentes = dao.read();
			Object[][] dados = new Object[dependentes.size()][4];   
	           for (int i = 0; i < dependentes.size(); i++) {   
	        	   Dependente dependente = dependentes.get(i);   
	               dados [i][0] = dependente.getid();   
	               dados[i][1] = dependente.getNome();   
	               dados[i][2] = dependente.getCpf();
	               dados[i][3] = dependente.getDataNasc();
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
			
			
			tblDependente= new JTable(dados, colunas);
			tblDependente.getWidth();
		
			c.add(tblDependente);
			c.add(btnNovo);
			c.add(btnSair);
			c.add(btnRemover);
			c.add(txtPesquisar);
			c.add(btnPesquisar);
			
			btnNovo.addActionListener(this);
			btnRemover.addActionListener(this);
			btnSair.addActionListener(this);
			
			
			janela.setContentPane(c);
			janela.setTitle("Lista de Dependentes");
			janela.setSize(600,400);
			janela.setVisible(true);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DependenteController();
		if (fonte == btnNovo) {
            control.novoDependente();
        }if(fonte == btnRemover) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o código a ser removido"));
        	try {
				control.deletaDependente(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		if(fonte == btnSair) {
			System.exit(0);
		}
			
	}
}
