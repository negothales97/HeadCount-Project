package view.filial;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class CadFilial extends JFrame implements ActionListener{
	private JFrame janela;
	
	private JButton btnSalvar;
	private JButton btnCancelar;
	
	private JLabel lblCNPJ;
	private JLabel lblNome;
	private JLabel lblIE;
	
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblNumero;
	private JLabel lblComplemento;
	private JLabel lblBairro;
	
	private JTextField txtCNPJ;
	private JTextField txtNome;
	private JTextField txtIE;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	
	public CadFilial() {
		
		janela 	= new JFrame();
		setLayout(new GridLayout(0, 2));
		Container c = getContentPane();
		
		btnSalvar 			= new JButton("Salvar");
		btnCancelar 		= new JButton("Cancelar");
		
		lblCNPJ 			= new JLabel("CNPJ");
		lblNome 			= new JLabel("Razão Social");
		lblIE 				= new JLabel("Inscrição Estadual");
		lblCep 				= new JLabel("CEP");
		lblRua 				= new JLabel("Rua");
		lblNumero 			= new JLabel("Número");
		lblComplemento 		= new JLabel("Complemento");
		lblBairro 			= new JLabel("Bairro");
		
		txtCNPJ 			= new JTextField(15);
		txtNome 			= new JTextField(25);
		txtIE 				= new JTextField(10);
		txtCep 				= new JTextField(10);
		txtRua 				= new JTextField(20);
		txtNumero			= new JTextField(5);
		txtComplemento 		= new JTextField(10);
		txtBairro 			= new JTextField(12);
		
		c.add(lblCNPJ);
		c.add(txtCNPJ);
		c.add(lblNome);
		c.add(txtNome);
		c.add(lblIE);
		c.add(txtIE);
		c.add(lblCep);
		c.add(txtCep);
		c.add(lblRua);
		c.add(txtRua);
		c.add(lblNumero);
		c.add(txtNumero);
		c.add(lblComplemento);
		c.add(txtComplemento);
		c.add(lblBairro);
		c.add(txtBairro);
		c.add(btnSalvar);
		c.add(btnCancelar);
		
		btnSalvar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		janela.setContentPane(c);
		janela.setTitle("Cadastro de Filiais");
		janela.setSize(600,400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == btnCancelar) {
            dispose();
        } 
		System.out.println("Evento");
		
	}
}