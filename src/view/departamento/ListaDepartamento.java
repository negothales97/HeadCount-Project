package view.departamento;

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

import controller.DepartamentoController;
import controller.DepartamentoController;
import model.dao.DepartamentoDAO;
import model.vo.Departamento;

public class ListaDepartamento extends JFrame implements ActionListener {
	private DepartamentoController control;
	private JFrame janela;
	
	private JButton btnNovo;
	private JButton btnSair;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JTextField txtPesquisar;
	
	private JTable tblDepartamento;
	
	private JScrollPane barraRolagem;
	
	public ListaDepartamento() throws SQLException {
		String[] colunas = { "Codigo", "Nome", "Centro de Custo", "Orçamento"};
		DepartamentoDAO dao = new DepartamentoDAO();
	
			List<Departamento> departamentos = dao.read();
			Object[][] dados = new Object[departamentos.size()][4];   
	           for (int i = 0; i < departamentos.size(); i++) {   
	        	   Departamento departamento = departamentos.get(i);   
	               dados [i][0] = departamento.getId();   
	               dados[i][1] = departamento.getNome();   
	               dados[i][2] = departamento.getCentroCusto();
	               dados[i][3] = departamento.getOrcamento();
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
			
			
			tblDepartamento= new JTable(dados, colunas);
			tblDepartamento.getWidth();
		
			c.add(tblDepartamento);
			c.add(btnNovo);
			c.add(btnSair);
			c.add(btnRemover);
			c.add(txtPesquisar);
			c.add(btnPesquisar);
			
			btnNovo.addActionListener(this);
			btnRemover.addActionListener(this);
			btnSair.addActionListener(this);
			
			
			janela.setContentPane(c);
			janela.setTitle("Lista de Departamentos");
			janela.setSize(600,400);
			janela.setVisible(true);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DepartamentoController();
		if (fonte == btnNovo) {
            control.novoDepartamento();
        }if(fonte == btnRemover) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o código a ser removido"));
        	try {
				control.deletaDepartamento(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		if(fonte == btnSair) {
			System.exit(0);
		}
			
	}
}
