package view.departamento;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.DepartamentoController;

public class RegCustoDepartamento implements ActionListener{
	
	private JFrame janela;
	private JPanel panelPrincipal;
	private JButton btnRegCusto;
	private JComboBox<String> cmbFilial;
	private DepartamentoController control;
	private JComboBox<String> cmbDepartamento;
	
	public RegCustoDepartamento(){
		janela 	= new JFrame();
		panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new FlowLayout());
		btnRegCusto = new JButton("Registrar Custo");
		
		String[] opcoes1 = { "Filial1", "Filial2", "Filial3"};
		cmbFilial = new JComboBox<>(opcoes1);
		
		String[] opcoes2 = { "Departamento1", "Departamento2", "Departamento3"};
		cmbDepartamento = new JComboBox<String>(opcoes2);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(50, 50, 50, 50);
		panelPrincipal.add(cmbFilial, gbc);
		panelPrincipal.add(cmbDepartamento, gbc);
		panelPrincipal.add(btnRegCusto, gbc);
		
		btnRegCusto.addActionListener(this);
		
		janela.setContentPane(panelPrincipal);
		janela.setTitle("Registro de Custo de Departamento");
		janela.setSize(400,100);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		control = new DepartamentoController();

		if (fonte == btnRegCusto) {
			janela.dispose();
			control.formRegistraCusto();
		}
	}

}
