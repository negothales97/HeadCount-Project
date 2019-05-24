package view.departamento;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfirmaRCD {

	private JFrame janela;

	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;

	private GridBagLayout gbLayout;
	private BorderLayout boderLayout;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private JLabel lblFilial;
	private JLabel lblDepartamento;
	private JLabel lblValorCusto;

	private JTextField txtFilial;
	private JTextField txtDepartamento;
	private JTextField txtValorCusto;

	public ConfirmaRCD() {

		janela = new JFrame();

		contentPanel = new JPanel();
		panelGridTop = new JPanel();
		panelGridBottom = new JPanel();

		gbLayout = new GridBagLayout();
		boderLayout = new BorderLayout();

		panelGridTop.setLayout(gbLayout);
		panelGridBottom.setLayout(gbLayout);
		contentPanel.setLayout(boderLayout);

		btnSalvar = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");

		lblDepartamento = new JLabel("Departamento");
		lblFilial = new JLabel("Filial");
		lblValorCusto = new JLabel("Valor de Custo");

		txtDepartamento = new JTextField(15);
		txtFilial = new JTextField(15);
		txtValorCusto = new JTextField(15);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 5);

		gbc.anchor = 13;
		panelGridTop.add(lblFilial, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtFilial, gbc);

		gbc.gridy = 1;
		gbc.anchor = 13;
		panelGridTop.add(lblDepartamento, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtDepartamento, gbc);

		gbc.gridy = 2;
		gbc.anchor = 13;
		panelGridTop.add(lblValorCusto, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtValorCusto, gbc);

		gbc.gridy = 0;
		panelGridBottom.add(btnSalvar, gbc);
		panelGridBottom.add(btnCancelar, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);

		janela.setContentPane(contentPanel);
		janela.setTitle("Registro de Custo de Departamento");
		janela.setSize(350, 200);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
