package view.funcionario;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FuncionarioController;

import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.Funcionario;

public class CadFuncionario extends JFrame implements ActionListener {
	private JFrame janela;

	JPanel panel;
	JButton btnSalvar;

	JButton btnCancelar;
	JTextField txtMatricula;
	JTextField txtNome;
	JTextField txtCPF;
	JTextField txtDataNasc;
	JTextField txtCargo;
	JTextField txtSalario;
	JTextField txtSetor;
	JTextField txtStatus;

	public CadFuncionario() {
		janela = new JFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		JTextField txtMatricula = new JTextField(6);
		JTextField txtNome = new JTextField(30);
		JTextField txtCPF = new JTextField(12);
		JTextField txtDataNasc = new JTextField(8);
		JTextField txtCargo = new JTextField(30);
		JTextField txtSalario = new JTextField(20);
		JTextField txtSetor = new JTextField(30);
		JTextField txtEPI = new JTextField(1);
		JTextField txtStatus = new JTextField(1);

		JLabel lblMatricula = new JLabel("Matricula");
		JLabel lblNome = new JLabel("Nome");
		JLabel lblCPF = new JLabel("CPF");
		JLabel lblDataNasc = new JLabel("Data de Nascimento");
		JLabel lblCargo = new JLabel("Cargo");
		JLabel lblSalario = new JLabel("Salario");
		JLabel lblSetor = new JLabel("Setor");
		JLabel lblEPI = new JLabel("EPI");
		JLabel lblStatus = new JLabel("Status");

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
		panel.add(btnSalvar);
		panel.add(btnCancelar);

		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Funcionario");
		janela.setSize(400, 300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnSalvar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if (fonte == btnSalvar) {
			FuncionarioController col = new FuncionarioController();
			col.recebeDados(txtNome.getText(), txtMatricula.getText(), txtCPF.getText(), txtDataNasc.getText(),
					txtCargo.getText(), txtSalario.getText(), txtSetor.getText(),
					txtStatus.getText());

		}
	}
}