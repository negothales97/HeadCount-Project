package view.funcionario;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FilialController;
import controller.FuncionarioController;

import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.Endereco;
import model.vo.Filial;
import model.vo.Funcionario;

public class CadFuncionario extends JFrame implements ActionListener {
	private JFrame janela;

	private JPanel panel;
	private JButton btnSave;

	private JButton btnCancelar;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JTextField txtCargo;
	private JTextField txtSalario;
	private JTextField txtSetor;
	private JTextField txtStatus;
	private JTextField txtEPI;
	
	private JLabel lblMatricula;
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblDataNasc;
	private JLabel lblCargo;
	private JLabel lblSalario;
	private JLabel lblSetor;
	private JLabel lblEPI;
	private JLabel lblStatus;

	public CadFuncionario() {
		janela = new JFrame();

		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		btnSave 		= new JButton("Salvar");
		btnCancelar 	= new JButton("Cancelar");

		txtMatricula 	= new JTextField(6);
		txtNome			= new JTextField(30);
		txtCPF 			= new JTextField(12);
		txtDataNasc 	= new JTextField(8);
		txtCargo 		= new JTextField(30);
		txtSalario 		= new JTextField(20);
		txtSetor 		= new JTextField(30);
		txtStatus 		= new JTextField(1);
		txtEPI 			= new JTextField(4);

		lblMatricula	= new JLabel("Matricula");
		lblNome 		= new JLabel("Nome");
		lblCPF 			= new JLabel("CPF");
		lblDataNasc 	= new JLabel("Data de Nascimento");
		lblCargo 		= new JLabel("Cargo");
		lblSalario 		= new JLabel("Salario");
		lblSetor 		= new JLabel("Setor");
		lblEPI 			= new JLabel("EPI");
		lblStatus 		= new JLabel("Status");

		panel.add(lblMatricula);
		panel.add(txtMatricula);
		panel.add(lblNome);
		panel.add(txtNome);
		panel.add(lblCPF);
		panel.add(txtCPF);
		panel.add(lblDataNasc);
		panel.add(txtDataNasc);
		panel.add(lblCargo);
		panel.add(txtCargo);
		panel.add(lblSalario);
		panel.add(txtSalario);
		panel.add(lblSetor);
		panel.add(txtSetor);
		panel.add(lblEPI);
		panel.add(txtEPI);
		panel.add(lblStatus);
		panel.add(txtStatus);
		panel.add(btnSave);
		panel.add(btnCancelar);
		
		btnSave.addActionListener(this);
		
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Funcionario");
		janela.setSize(400, 300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == btnSave) {
			FuncionarioController col = new FuncionarioController();
			Funcionario funcionario = new Funcionario(txtNome.getText(), txtCPF.getText(), txtDataNasc.getText());
        	try {
        		col.criaFuncionario(funcionario);
				JOptionPane.showMessageDialog(null, "Funcionario Cadastrado com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
}