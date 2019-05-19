package view.filial;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hsqldb.Constraint;

import controller.FilialController;
import model.vo.Endereco;
import model.vo.Filial;

public class CadFilial extends JFrame implements ActionListener{
	private JFrame janela;
	private FilialController control;
	private Filial filial;
	
	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;
	
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
	
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
		contentPanel = new JPanel();
		panelGridTop = new JPanel();
		panelGridBottom = new JPanel();
		
		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();
		
		panelGridTop.setLayout(gbLayout);
		panelGridBottom.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		
		btnSalvar 	= new JButton("Salvar");
		btnVoltar	= new JButton("Voltar");
		
		lblCNPJ 			= new JLabel("CNPJ");
		lblNome 			= new JLabel("Raz�o Social");
		lblIE 				= new JLabel("Inscri��o Estadual");
		lblRua 				= new JLabel("Rua");
		lblNumero 			= new JLabel("N�mero");
		lblBairro 			= new JLabel("Bairro");
		
		txtCNPJ 			= new JTextField(15);
		txtNome 			= new JTextField(25);
		txtIE 				= new JTextField(10);
		txtRua 				= new JTextField(20);
		txtNumero			= new JTextField(5);
		txtBairro 			= new JTextField(12);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		gbc.anchor=13;
		panelGridTop.add(lblCNPJ, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtCNPJ, gbc);
		
		gbc.gridy=1;
		gbc.anchor=13;
		panelGridTop.add(lblNome, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtNome, gbc);
		
		gbc.gridy=2;
		gbc.anchor=13;
		panelGridTop.add(lblIE, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtIE, gbc);
		
		gbc.gridy=3;
		gbc.anchor=13;
		panelGridTop.add(lblRua, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtRua, gbc);
		
		gbc.gridy=4;
		gbc.anchor=13;
		panelGridTop.add(lblNumero, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtNumero, gbc);
		
		gbc.gridy=5;
		gbc.anchor=13;
		panelGridTop.add(lblBairro, gbc);gbc.anchor=17;
		panelGridTop.add(txtBairro, gbc);gbc.anchor=13;
		
		
		panelGridBottom.add(btnSalvar);
		panelGridBottom.add(btnVoltar);
		
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);
		
		janela.setContentPane(contentPanel);
		janela.setTitle("Cadastro de Filiais");
		janela.setSize(450,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
    	control = new FilialController();
		
		if (fonte == btnVoltar) {
            janela.dispose();
            try {
				control.listaFilial();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            
        }if(fonte == btnSalvar) {
        	filial = new Filial(txtNome.getText(), txtCNPJ.getText(), txtIE.getText());
        	Endereco endereco = new Endereco(txtRua.getText(), txtNumero.getText(), txtBairro.getText());
        	filial.setEndereco(endereco);
        	
        	try {
				control.criaFilial(filial);
				janela.dispose();
				JOptionPane.showMessageDialog(null, "Filial Cadastrada com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		
	}
}