package view.dependente;

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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hsqldb.Constraint;

import controller.DependenteController;
import controller.FuncionarioController;
import model.vo.Dependente;
import model.vo.Funcionario;

public class EditDependente extends JFrame implements ActionListener {
	private JFrame janela;
	private DependenteController control;
	private Dependente filial;

	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;

	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JButton btnSalvar;
	private JButton btnVoltar;

	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblDataNasc;
	private JLabel lblFuncionario;

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JComboBox<String> cmbFuncionario;
	private FuncionarioController controlFunc;
	private Dependente dependente ;

	public EditDependente(Dependente dependente) {
		this.dependente = dependente;
		janela = new JFrame();
		contentPanel = new JPanel();
		panelGridTop = new JPanel();
		panelGridBottom = new JPanel();

		boderLayout = new BorderLayout();
		gbLayout = new GridBagLayout();

		panelGridTop.setLayout(gbLayout);
		panelGridBottom.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);

		btnSalvar = new JButton("Salvar");
		btnVoltar = new JButton("Voltar");

		lblNome = new JLabel("Nome");
		lblCPF = new JLabel("CPF");
		lblDataNasc = new JLabel("Data Nascimento");
		lblFuncionario = new JLabel("Parentesco");
		
		

		txtNome = new JTextField(15);
		txtCPF = new JTextField(11);
		txtDataNasc = new JTextField(8);
		cmbFuncionario = new JComboBox<String>();
		
		txtNome.setText(dependente.getNome());
		txtCPF.setText(dependente.getCpf());
		txtDataNasc.setText(dependente.getDataNasc());
		
		cmbFuncionario.addItem("SELECIONE....");
		try {
			controlFunc = new FuncionarioController();
			List<Funcionario> master= controlFunc.getFuncionarios();
			for (int i = 0; i< master.size(); i++) {
				cmbFuncionario.addItem(master.get(i).getNome());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = 13;
		panelGridTop.add(lblNome, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtNome, gbc);

		gbc.gridy = 1;
		gbc.anchor = 13;
		panelGridTop.add(lblCPF, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtCPF, gbc);

		gbc.gridy = 2;
		gbc.anchor = 13;
		panelGridTop.add(lblDataNasc, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtDataNasc, gbc);

		gbc.gridy = 3;
		gbc.anchor = 13;
		panelGridTop.add(lblFuncionario, gbc);
		gbc.anchor = 17;
		panelGridTop.add(cmbFuncionario, gbc);

		panelGridBottom.add(btnSalvar, gbc);
		panelGridBottom.add(btnVoltar, gbc);

		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);

		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);

		janela.setContentPane(contentPanel);
		janela.setTitle("Cadastro de Dependentes");
		janela.setSize(450, 300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DependenteController();

		if (fonte == btnVoltar) {
			janela.dispose();
			try {
				control.listaDependente();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		if (fonte == btnSalvar) {
			this.dependente.setNome(txtNome.getText());
			this.dependente.setCpf(txtCPF.getText());
			this.dependente.setDataNasc(txtDataNasc.getText());
			this.dependente.setFuncionario(cmbFuncionario.getSelectedIndex());
			control.updateDependente(this.dependente);
			janela.dispose();
			
		}

	}
}