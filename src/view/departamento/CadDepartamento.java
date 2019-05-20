package view.departamento;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DepartamentoController;
import controller.FilialController;
import model.vo.Departamento;


public class CadDepartamento extends JFrame implements ActionListener{
	private JFrame janela;
	private DepartamentoController control;
	private Departamento departamento;
	
	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;
	
	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;
	
	private JButton btnSalvar;
	private JButton btnVoltar;
	
	private JLabel lblNome;
	private JLabel lblCentroCusto;
	private JLabel lblOrcamento;
	
	private JTextField txtNome;
	private JTextField txtCentroCusto;
	private JTextField txtOrcamento;
	
	public CadDepartamento() {
		
		janela 	= new JFrame();
		contentPanel = new JPanel();
		panelGridTop = new JPanel();
		panelGridBottom = new JPanel();
		
		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();
		
		panelGridTop.setLayout(gbLayout);
		panelGridBottom.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);
		
		btnSalvar 		= new JButton("Salvar");
		btnVoltar 		= new JButton("Cancelar");		
		
		lblNome 		= new JLabel("Nome do Departamento");
		lblCentroCusto	= new JLabel("Centro de Custo");
		lblOrcamento	= new JLabel("Orçamento (R$)");
		
		
		
		txtNome 		= new JTextField(15);
		txtCentroCusto 	= new JTextField(15);
		txtOrcamento 	= new JTextField(15);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		gbc.anchor=13;
		panelGridTop.add(lblNome, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtNome, gbc);
		
		gbc.gridy=1;
		gbc.anchor=13;
		panelGridTop.add(lblCentroCusto, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtCentroCusto, gbc);
		
		gbc.gridy=2;
		gbc.anchor=13;
		panelGridTop.add(lblOrcamento, gbc);
		gbc.anchor=17;
		panelGridTop.add(txtOrcamento, gbc);			
		
		panelGridBottom.add(btnSalvar);
		panelGridBottom.add(btnVoltar);
		
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);
		
		janela.setContentPane(contentPanel);
		janela.setTitle("Cadastro de Departamentos");
		janela.setSize(450,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
    	control = new DepartamentoController();
		
		if (fonte == btnVoltar) {
            janela.dispose();
            try {
				control.listaDepartamento();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
          
		} if(fonte == btnSalvar) {
        	double orcamento = Double.parseDouble(txtOrcamento.getText());
        	Departamento departamento = new Departamento(txtNome.getText(), txtCentroCusto.getText(), orcamento);
        	control = new DepartamentoController();
        	try {
				control.criaDepartamento(departamento);
				janela.dispose();
				JOptionPane.showMessageDialog(null, "Departamento Cadastrado com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		
	}
	
}