package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.dao.FilialDAO;


public class CadFilial extends JFrame{
	private JFrame janela;
	private JPanel panel;
	
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
		panel 	= new JPanel();
		panel.setLayout(new FlowLayout());
		
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
		
		panel.add(lblCNPJ);
		panel.add(txtCNPJ);
		panel.add(lblNome);
		panel.add(txtNome);
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
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCep.getText();
				txtRua.getText();
				txtNumero.getText();
				txtComplemento.getText();
				txtBairro.getText();
				FilialDAO dao = new FilialDAO();
				try {
					dao.create(txtNome.getText(), txtCNPJ.getText(), txtIE.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Empresas");
		janela.setSize(400,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}