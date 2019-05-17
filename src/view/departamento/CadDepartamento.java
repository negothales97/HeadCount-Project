package view.departamento;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CadDepartamento extends JFrame{
	private JFrame janela;
	private JPanel panel;
	
	private JButton btnSalvar;
	private JButton btnCancelar;
	
	private JLabel lblNome;
	private JLabel lblCentroCusto;
	private JLabel lblOrcamento;
	
	
	
	private JTextField txtNome;
	private JTextField txtCentroCusto;
	private JTextField txtOrcamento;
	
	public CadDepartamento() {
		
		janela 	= new JFrame();
		panel 	= new JPanel();
		panel.setLayout(new FlowLayout());
		
		btnSalvar 			= new JButton("Salvar");
		btnCancelar 		= new JButton("Cancelar");		
		
		lblNome 			= new JLabel("Nome do Departamento");
		lblCentroCusto		= new JLabel("Centro de Custo");
		lblOrcamento		= new JLabel("Orçamento (R$)");
		
		
		
		txtNome 			= new JTextField(25);
		txtCentroCusto 		= new JTextField(15);
		txtOrcamento 		= new JTextField(15);
				
		panel.add(lblNome);
		panel.add(txtNome);
		panel.add(lblCentroCusto);
		panel.add(txtCentroCusto);
		panel.add(lblOrcamento);
		panel.add(txtOrcamento);	
		panel.add(btnSalvar);
		panel.add(btnCancelar);
		
		
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Departamentos");
		janela.setSize(400,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}