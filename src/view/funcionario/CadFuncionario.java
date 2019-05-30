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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CargoController;
import controller.DepartamentoController;
import controller.DependenteController;
import controller.FilialController;
import controller.FuncionarioController;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.dao.FilialDAO;
import model.vo.Departamento;
import model.vo.Endereco;
import model.vo.Filial;
import model.vo.Funcionario;

public class CadFuncionario extends JFrame implements ActionListener {
	private JFrame janela;
	private FuncionarioController control;
	private Filial filial;
	private FilialController filialControl;
	private DepartamentoController departamentoController;
	private CargoController cargoController;
	

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
	private JLabel lblDepartamento;
	private JLabel lblFilial;
	private List<Filial> filiais;


	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JTextField txtCargo;
	private JTextField txtSalario;
	private JTextField txtSetor;
	private FilialDAO dao;

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
		lblSalario = new JLabel("Salario");
		lblSetor = new JLabel("Setor");
		lblCargo = new JLabel("Cargo");
		lblFilial = new JLabel("Filial");
		lblDepartamento = new JLabel("Departamento");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		JComboBox<String>comboBoxCargo = new JComboBox<String>();
		try {
				cargoController = new CargoController();
				String [] masterCargo = cargoController.comboBoxCargo();
				for(int i =0;i<masterCargo.length;i++) {
					comboBoxCargo.addItem(masterCargo[i]);
				}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		//trocar o objeto dessa collection
		JComboBox<String>comboBoxDepartamento = new JComboBox<String>();
		try {
			departamentoController = new DepartamentoController();
			String [] masterDepartamento =departamentoController.comboBoxDepartamento();
			for (int i =0; i< masterDepartamento.length; i++) {
				comboBoxDepartamento.addItem(masterDepartamento[i]);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		JComboBox<String>comboBoxFilial = new JComboBox<String>();
		  try { 
			  filialControl = new FilialController();
			  List<Filial> master =filialControl.comboBoxFilial();
			  for (int i = 0; i < master.size(); i++) {
			   comboBoxFilial.addItem(master.get(i).getNome());}
			  }
		  catch (SQLException e) { 
		  e.printStackTrace(); }
		 		

	

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
		panelGridTop.add(comboBoxCargo, gbc);

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
		
		gbc.gridy = 7;
		gbc.anchor = 13;
		panelGridTop.add(lblDepartamento, gbc);
		gbc.anchor = 17;
		panelGridTop.add(comboBoxDepartamento, gbc);
		
		gbc.gridy = 8;
		gbc.anchor = 13;
		panelGridTop.add(lblFilial, gbc);
		gbc.anchor = 17;
		panelGridTop.add(comboBoxFilial, gbc);
		


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