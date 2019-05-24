package view;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.FilialController;
import model.vo.Endereco;
import model.vo.Filial;

public class Login implements ActionListener {
	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelCenter;
	private JPanel panelBottom;
	private GridBagLayout gbl;
	private BorderLayout bl;

	private JPasswordField txtPassword;
	private JTextField txtLogin;

	private JLabel lblPassword;
	private JLabel lblLogin;

	private JButton btnOk;
	private JButton btnCancelar;

	private HeadCount headCount;

	public Login() {

		janela = new JFrame();
		contentPanel = new JPanel();
		panelCenter = new JPanel();
		panelBottom = new JPanel();

		bl = new BorderLayout();
		gbl = new GridBagLayout();

		lblLogin = new JLabel("Login");
		lblPassword = new JLabel("Senha");

		txtLogin = new JTextField(15);
		txtPassword = new JPasswordField(15);

		btnOk = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");

		contentPanel.setLayout(bl);
		panelCenter.setLayout(gbl);
		panelBottom.setLayout(gbl);

		contentPanel.add(BorderLayout.CENTER, panelCenter);
		contentPanel.add(BorderLayout.NORTH, panelBottom);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridy = 1;
		gbc.anchor = 13;
		panelCenter.add(lblLogin, gbc);
		gbc.anchor = 17;
		panelCenter.add(txtLogin, gbc);

		gbc.gridy = 2;
		gbc.anchor = 13;
		panelCenter.add(lblPassword, gbc);
		gbc.anchor = 17;
		panelCenter.add(txtPassword, gbc);

		gbc.gridy = 1;
		gbc.anchor = 13;
		panelBottom.add(btnOk, gbc);
		gbc.anchor = 17;
		panelBottom.add(btnCancelar, gbc);

		btnOk.addActionListener(this);
		btnCancelar.addActionListener(this);

		contentPanel.add(BorderLayout.CENTER, panelCenter);
		contentPanel.add(BorderLayout.SOUTH, panelBottom);

		janela.setContentPane(contentPanel);
		janela.setTitle("Login");
		janela.setSize(250, 200);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();

		if (fonte == btnCancelar) {
			janela.dispose();

		}
		if (fonte == btnOk) {
			headCount = new HeadCount();
			janela.dispose();
		}
	}

}
