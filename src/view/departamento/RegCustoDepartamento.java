package view.departamento;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegCustoDepartamento {
	
	private JFrame janela;
	private JPanel panelPrincipal;
	private GridBagLayout gbLayout;
	private JButton btnRegCusto;
	private JComboBox<String> cmbFilial;
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
		
		janela.setContentPane(panelPrincipal);
		janela.setTitle("Registro de Custo de Departamento");
		janela.setSize(400,100);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
