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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FilialController;
import model.vo.Endereco;
import model.vo.Filial;




public class CadFilial extends JFrame implements ActionListener{
	private JFrame janela;
	
	private JButton btnSalvar;
	private JButton btnVoltar;
	
	private JLabel lblCNPJ;
	private JLabel lblNome;
	private JLabel lblIE;
	private JLabel lblRua;
	private JLabel lblNumero;
	private JLabel lblBairro;
	
	private JTextField txtCNPJ;
	private JTextField txtNome;
	private JTextField txtIE;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	
	public CadFilial() {
		
		janela 	= new JFrame();
		setLayout(new GridLayout(0, 2));
		Container c = getContentPane();
		
		btnSalvar 			= new JButton("Salvar");
		btnVoltar= new JButton("Voltar");
		
		lblCNPJ 			= new JLabel("CNPJ");
		lblNome 			= new JLabel("Razão Social");
		lblIE 				= new JLabel("Inscrição Estadual");
		lblRua 				= new JLabel("Rua");
		lblNumero 			= new JLabel("Número");
		lblBairro 			= new JLabel("Bairro");
		
		txtCNPJ 			= new JTextField(15);
		txtNome 			= new JTextField(25);
		txtIE 				= new JTextField(10);
		txtRua 				= new JTextField(20);
		txtNumero			= new JTextField(5);
		txtBairro 			= new JTextField(12);
		
		c.add(lblCNPJ);
		c.add(txtCNPJ);
		c.add(lblNome);
		c.add(txtNome);
		c.add(lblIE);
		c.add(txtIE);
		c.add(lblRua);
		c.add(txtRua);
		c.add(lblNumero);
		c.add(txtNumero);
		c.add(lblBairro);
		c.add(txtBairro);
		c.add(btnSalvar);
		c.add(btnVoltar);
		
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		janela.setContentPane(c);
		janela.setTitle("Cadastro de Filiais");
		janela.setSize(600,400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == btnVoltar) {
            this.dispose();
        }if(fonte == btnSalvar) {
        	Filial filial = new Filial(txtNome.getText(), txtCNPJ.getText(), txtIE.getText());
        	Endereco endereco = new Endereco(txtRua.getText(), txtNumero.getText(), txtBairro.getText());
        	filial.setEndereco(endereco);
        	FilialController control = new FilialController();
        	try {
				control.criaFilial(filial);
				JOptionPane.showMessageDialog(null, "Filial Cadastrada com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		
	}
}