package view.cargo;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.SQLException;

import controller.CargoController;
import model.vo.Endereco;
import model.vo.Cargo;

public class EditCargo extends JFrame implements ActionListener {
	private JFrame janela;
	private CargoController control;
	private Cargo cargo;

	private JPanel contentPanel;
	private JPanel panelGridTop;
	private JPanel panelGridBottom;

	private BorderLayout boderLayout;
	private GridBagLayout gbLayout;

	private JButton btnSalvar;
	private JButton btnVoltar;

	private JLabel lblCargo;

	private JTextField txtCargo;

	public EditCargo(Cargo cargo) {
		this.cargo = cargo;
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

		lblCargo = new JLabel("Cargo");

		txtCargo = new JTextField(25);
		
		txtCargo.setText(cargo.getNome());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = 13;
		panelGridTop.add(lblCargo, gbc);
		gbc.anchor = 17;
		panelGridTop.add(txtCargo, gbc);

		panelGridBottom.add(btnSalvar, gbc);
		panelGridBottom.add(btnVoltar, gbc);

		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);

		contentPanel.add(BorderLayout.NORTH, panelGridTop);
		contentPanel.add(BorderLayout.CENTER, panelGridBottom);

		janela.setContentPane(contentPanel);
		janela.setTitle("Cadastro de Cargos");
		janela.setSize(450, 300);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new CargoController();

		if (fonte == btnVoltar) {
			janela.dispose();
			try {
				control.listaCargo();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		if (fonte == btnSalvar) {
			this.cargo.setNome(txtCargo.getText());
			
			control.updateCargo(cargo);
			janela.dispose();
		}

	}
}