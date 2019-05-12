package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CadFilial extends JFrame{
	private JFrame janela;
	public CadFilial() {
		janela = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JButton btnSalvar 			= new JButton("Salvar");
		JButton btnCancelar 		= new JButton("Cancelar");
		
		JLabel lblCod 				= new JLabel("Código");
		JLabel lblCNPJ 				= new JLabel("CNPJ");
		JLabel lblRazao 			= new JLabel("Razãoo Social");
		JLabel lblIE 				= new JLabel("Inscrição Estadual");
		JLabel lblCep 				= new JLabel("CEP");
		JLabel lblRua 				= new JLabel("Rua");
		JLabel lblNumero 			= new JLabel("Número");
		JLabel lblComplemento 		= new JLabel("Complemento");
		JLabel lblBairro 			= new JLabel("Bairro");
		
		JTextField txtCod 			= new JTextField(6);
		JTextField txtCNPJ 			= new JTextField(15);
		JTextField txtRazao 		= new JTextField(50);
		JTextField txtIE 			= new JTextField(15);
		JTextField txtCep 			= new JTextField(15);
		JTextField txtRua 			= new JTextField(15);
		JTextField txtNumero		= new JTextField(15);
		JTextField txtComplemento 	= new JTextField(15);
		JTextField txtBairro 		= new JTextField(15);
		
		panel.add(lblCod);
		panel.add(txtCod);
		panel.add(lblCNPJ);
		panel.add(txtCNPJ);
		panel.add(lblRazao);
		panel.add(txtRazao);
		panel.add(lblIE);
		panel.add(txtIE);
		panel.add(lblCep);
		panel.add(txtCep);
		panel.add(lblRua);
		panel.add(txtRua);
		panel.add(lblNumero);
		panel.add(txtNumero);
		panel.add(lblComplemento);
		panel.add(txtComplemento);
		panel.add(lblBairro);
		panel.add(txtBairro);
		panel.add(btnSalvar);
		panel.add(btnCancelar);
		
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Empresas");
		janela.setSize(400,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}