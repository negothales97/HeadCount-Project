package view.funcionario;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CargoController;
import controller.DepartamentoController;
import controller.FilialController;
import controller.FuncionarioController;

import java.util.List;

import model.vo.Cargo;
import model.vo.Departamento;
import model.vo.Filial;
import model.vo.Funcionario;

public class EditFuncionario extends JFrame implements ActionListener {
	private JFrame janela;
	private FuncionarioController control;
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
	private JLabel lblDepartamento;
	private JLabel lblFilial;
	private List<Filial> filiais;


	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxCargo;
	private JComboBox<String> comboBoxFilial;
	private FilialController filialControl;
	private Funcionario funcionario;

	public EditFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		
		comboBoxCargo = new JComboBox<String>();
		comboBoxDepartamento = new JComboBox<String>();
		comboBoxFilial = new JComboBox<String>();

		txtNome = new JTextField(30);
		txtCPF = new JTextField(12);
		txtDataNasc = new JTextField(8);

		lblNome = new JLabel("Nome");
		lblCPF = new JLabel("CPF");
		lblDataNasc = new JLabel("Data de Nascimento");
		lblCargo = new JLabel("Cargo");
		lblFilial = new JLabel("Filial");
		lblDepartamento = new JLabel("Departamento");
		
		txtNome.setText(funcionario.getNome());
		txtCPF.setText(funcionario.getCpf());
		txtDataNasc.setText(funcionario.getDatanasc());
//		comboBoxCargo.setSelectedIndex(funcionario.getCargo_id());
//		comboBoxDepartamento.setSelectedIndex(funcionario.getDepartamento_id());
//		comboBoxFilial.setSelectedIndex(funcionario.getFilial_id());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		try {
				cargoController = new CargoController();
				List<Cargo> masterCargo = cargoController.getCargos();
				for(int i =0;i<masterCargo.size();i++) {
					comboBoxCargo.addItem(masterCargo.get(i).getNome());
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
		try {
			departamentoController = new DepartamentoController();
			List<Departamento> masterDepartamento = departamentoController.getDepartamentos();
			for (int i =0; i< masterDepartamento.size(); i++) {
				comboBoxDepartamento.addItem(masterDepartamento.get(i).getNome());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		filialControl = new FilialController();
			List<Filial> master = filialControl.getFiliais();
			for (int i = 0; i < master.size(); i++) {
				comboBoxFilial.addItem(master.get(i).getNome());}
		 		

	

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
		janela.setSize(600, 350);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new FuncionarioController();

		if (fonte == btnVoltar) {
			janela.dispose();
			control.listaFuncionario();
		}

		if (fonte == btnSave) {
			FuncionarioController col = new FuncionarioController();
			Funcionario funcionario = new Funcionario(txtNome.getText(), txtCPF.getText(), txtDataNasc.getText(), comboBoxFilial.getSelectedIndex(),comboBoxDepartamento.getSelectedIndex(),comboBoxFilial.getSelectedIndex());
			if (col.updateFuncionario(funcionario)) {
				janela.dispose();
			}
			
		}
	}
	

}