package view.funcionario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DependenteController;
import controller.FilialController;
import controller.FuncionarioController;

import java.util.Date;

import javax.swing.JOptionPane;

import model.vo.Endereco;
import model.vo.Filial;
import model.vo.Funcionario;

public class CadFuncionario extends JFrame implements ActionListener {
	private JFrame janela;
	private FuncionarioController control;
	private Filial filial;

	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;

	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JPanel panel;

	private JButton btnSave;
	private JButton btnVoltar;

	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblDataNasc;
	private JLabel lblCargo;
	private JLabel lblSalario;
	private JLabel lblSetor;


	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JTextField txtCargo;
	private JTextField txtSalario;
	private JTextField txtSetor;

	public CadFuncionario() {
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGridTop = new JPanel();
		panelGridBottom = new JPanel();

		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();

		panelGridTop.setLayout(gbLayout);
		panelGridBottom.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);

		btnSave = new JButton("Salvar");
		btnVoltar = new JButton("Voltar");

		txtNome = new JTextField(30);
		txtCPF = new JTextField(12);
		txtDataNasc = new JTextField(8);
		txtCargo = new JTextField(30);
		txtSalario = new JTextField(20);
		txtSetor = new JTextField(30);

		lblNome = new JLabel("Nome");
		lblCPF = new JLabel("CPF");
		lblDataNasc = new JLabel("Data de Nascimento");
		lblCargo = new JLabel("Cargo");
		lblSalario = new JLabel("Salario");
		lblSetor = new JLabel("Setor");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridy = 1;
		gbc.anchor = 13;
		panelGridTop.add(lblNome, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtNome, gbc);

		gbc.gridy = 2;
		gbc.anchor = 13;
		panelGridTop.add(lblCPF, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtCPF, gbc);

		gbc.gridy = 3;
		gbc.anchor = 13;
		panelGridTop.add(lblDataNasc, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtDataNasc, gbc);

		gbc.gridy = 4;
		gbc.anchor = 13;
		panelGridTop.add(lblCargo, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtCargo, gbc);

		gbc.gridy = 5;
		gbc.anchor = 13;
		panelGridTop.add(lblSalario, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtSalario, gbc);

		gbc.gridy = 6;
		gbc.anchor = 13;
		panelGridTop.add(lblSetor, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtSetor, gbc);

		panelGridBottom.add(btnSave, gbc);
		panelGridBottom.add(btnVoltar, gbc);

		btnSave.addActionListener(this);
		btnVoltar.addActionListener(this);

		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);

		janela.setContentPane(contentPanel);
		janela.setTitle("Cadastro de Funcionarios");
		janela.setSize(500, 350);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FuncionarioController();

		if (fonte == btnVoltar) {
			janela.dispose();
			try {
				control.listaFuncionario();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		if (fonte == btnSave) {
			FuncionarioController col = new FuncionarioController();
			Funcionario funcionario = new Funcionario(txtNome.getText(), txtCPF.getText(), txtDataNasc.getText());
			try {
				col.criaFuncionario(funcionario);
				janela.dispose();
				JOptionPane.showMessageDialog(null, "Funcionario Cadastrado com sucesso");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
}