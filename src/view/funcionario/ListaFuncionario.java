package view.funcionario;


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
import controller.FuncionarioController;
import model.dao.FilialDAO;
import model.dao.FuncionarioDAO;
import model.vo.Filial;
import model.vo.Funcionario;

public class ListaFuncionario extends JFrame implements ActionListener {
	private FuncionarioController control;
	private JFrame janela;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
	
	private JTable tblFuncionario;
	
	private JScrollPane barraRolagem;
	
	public ListaFuncionario() throws SQLException {
		String[] colunas = { "Matricula", "Nome", "CPF", "Data Nasc"};
		FuncionarioDAO dao = new FuncionarioDAO();
	
		List<Funcionario> funcionarios = dao.read();
		Object[][] dados = new Object[funcionarios.size()][4];   
           for (int i = 0; i < funcionarios.size(); i++) {   
        	   Funcionario funcionario = funcionarios.get(i);   
               dados [i][0] = funcionario.getMatricula();   
               dados[i][1] = funcionario.getNome();   
               dados[i][2] = funcionario.getCpf();
               dados[i][3] = funcionario.getDatanasc();
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
			
			
			tblFuncionario = new JTable(dados, colunas);
			tblFuncionario.getWidth();
		
			c.add(tblFuncionario);
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
		control = new FuncionarioController();
		if (fonte == btnNovo) {
            control.novoFuncionario();
        }if(fonte == btnRemover) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o código a ser removido"));
        	try {
				control.deletaFuncionario(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		if(fonte == btnSair) {
			System.exit(0);
		}
			
	}
}
