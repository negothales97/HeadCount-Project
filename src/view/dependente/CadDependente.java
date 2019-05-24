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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hsqldb.Constraint;

import controller.DependenteController;
import model.vo.Dependente;

public class CadDependente extends JFrame implements ActionListener {
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
	private JLabel lblParentesco;

	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JTextField txtParentesco;


	public CadDependente() {
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
		lblParentesco = new JLabel("Parentesco");


		txtNome = new JTextField(15);
		txtCPF = new JTextField(11);
		txtDataNasc = new JTextField(8);
		txtParentesco = new JTextField(20);

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
		panelGridTop.add(lblParentesco, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtParentesco, gbc);

		panelGridBottom.add(btnSalvar);
		panelGridBottom.add(btnVoltar);

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
				control.ListaDependente();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
////		if (fonte == btnSalvar) {
//			Dependente dependente = new Dependente(txtNome.getText(), txtCPF.getText(), txtDataNasc.getText(), txtParentesco.getText());
//
//			try {
//				control.criaDependente(dependente);
//				janela.dispose();
//				JOptionPane.showMessageDialog(null, "Dependente Cadastrado com sucesso");
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}

	}
}