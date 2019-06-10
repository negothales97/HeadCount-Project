package view.departamento;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.FilialController;
import model.vo.Filial;

public class RelCustoDepartamento extends JFrame implements ActionListener {

	private JFrame janela;
	private JPanel contentPanel;
	private JPanel panelGrid;
	private Container container;
	private JComboBox<Filial> cmbFilial;
	private JLabel lblFilial;
	private JButton btnFiltrar;
	private JTable tblDepartamento;
	private FilialController filialControl;
	private JScrollPane barraRolagem;
	private String[] colunas= { "Filial", "Departamento", "Funcionarios", "Custo (R$)" };
	private Object[][] dados;

	public RelCustoDepartamento(){
		super();
	}
	
	public void criaJanela() {
		btnFiltrar = new JButton("Filtrar");
		lblFilial = new JLabel("Filial");
		cmbFilial = new JComboBox<>();
		
		filialControl = new FilialController();
		
		List<Filial> filiais = filialControl.getFiliais();
		for (int i = 0; i < filiais.size(); i++) {
			cmbFilial.addItem(filiais.get(i));
		}
		dados = new Object[1][4];

		janela = new JFrame();
		contentPanel = new JPanel();
		panelGrid = new JPanel();
		container = new JPanel();

		panelGrid.setLayout(new GridBagLayout());
		contentPanel.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());

		tblDepartamento = new JTable(dados, colunas);
		barraRolagem = new JScrollPane(tblDepartamento);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		panelGrid.add(lblFilial, gbc);
		panelGrid.add(cmbFilial, gbc);
		panelGrid.add(btnFiltrar, gbc);

		container.add(barraRolagem, gbc);

		contentPanel.add(BorderLayout.NORTH, panelGrid);
		contentPanel.add(BorderLayout.CENTER, container);
		
		btnFiltrar.addActionListener(this);

		janela.setContentPane(contentPanel);
		janela.setTitle("Relatorio do Custo de Departamento");
		janela.setSize(800, 600);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == btnFiltrar) {
			 Filial filial = (Filial) cmbFilial.getSelectedItem();
			 System.out.println(filial.getId());
		}
	}

}
