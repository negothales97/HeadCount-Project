package view.departamento;

import java.awt.FlowLayout;
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
import model.vo.Endereco;
import model.vo.Filial;


public class CadDepartamento extends JFrame implements ActionListener{
	private JFrame janela;
	private JPanel panel;
	private DepartamentoController control;
	
	private JButton btnSalvar;
	private JButton btnVoltar;
	
	private JLabel lblNome;
	private JLabel lblCentroCusto;
	private JLabel lblOrcamento;
	
	private JTextField txtNome;
	private JTextField txtCentroCusto;
	private JTextField txtOrcamento;
	
	public CadDepartamento() {
		
		janela 			= new JFrame();
		panel 			= new JPanel();
		panel.setLayout(new FlowLayout());
		
		btnSalvar 		= new JButton("Salvar");
		btnVoltar 		= new JButton("Cancelar");		
		
		lblNome 		= new JLabel("Nome do Departamento");
		lblCentroCusto	= new JLabel("Centro de Custo");
		lblOrcamento	= new JLabel("Orçamento (R$)");
		
		
		
		txtNome 		= new JTextField(15);
		txtCentroCusto 	= new JTextField(15);
		txtOrcamento 	= new JTextField(15);
		
		btnSalvar.addActionListener(this);
				
		panel.add(lblNome);
		panel.add(txtNome);
		panel.add(lblCentroCusto);
		panel.add(txtCentroCusto);
		panel.add(lblOrcamento);
		panel.add(txtOrcamento);	
		panel.add(btnSalvar);
		panel.add(btnVoltar);
		
		
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Departamentos");
		janela.setSize(400,300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == btnVoltar) {
            this.dispose();
        }if(fonte == btnSalvar) {
        	double orcamento = Double.parseDouble(txtOrcamento.getText());
        	Departamento departamento = new Departamento(txtNome.getText(), txtCentroCusto.getText(), orcamento);
        	control = new DepartamentoController();
        	try {
				control.criaDepartamento(departamento);
				JOptionPane.showMessageDialog(null, "Departamento Cadastrado com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
		
	}
	
}