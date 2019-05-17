package view.dependente;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.dao.DependenteTM;

public class DependenteView {

	private JFrame janela;
	
	private JPanel panel;
	private JTabbedPane panelTabulado;
	
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquis;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnPesquisar;
	
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtDataNasc;
	private JTextField txtParentesco;
	private JTextField txtPesquisar;
	
	private JLabel lblCodigo;
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblDataNasc;
	private JLabel lblParentesco;
	
	private DependenteTM Modelo;
	
	private String[] coluna = { "Codigo", "Nome", "CPF", "Data Nasc", "Parentesco" };
	private Object[][] data = {{"Codigo","Nome","CPF","Data Nasc","Parentesco"}};
	
	private JTable tblDependente;
	
	private void Botoes (Boolean I, Boolean A, Boolean E, Boolean C, Boolean S) {
		btnIncluir.setEnabled(I);
		btnEditar.setEnabled(A);
		btnExcluir.setEnabled(E);
		btnCancelar.setEnabled(C);
		btnSalvar.setEnabled(S);
		
	}
	
    
    
    public void LoadTable(){
        Modelo = new DependenteTM(DependenteController.getDependente());
        tblDependente.setModel(Modelo);
        
        tblDependente.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDependente.getColumnModel().getColumn(1).setPreferredWidth(250);
    }
	
	private void HabilitaTxt (Boolean A) {
		txtCodigo.setEnabled(A);
		txtNome.setEnabled(A);
		txtCPF.setEnabled(A);
		txtDataNasc.setEnabled(A);
		txtParentesco.setEnabled(A);
		
	}
	
	private void LimpaTxt (Boolean L) {
		if (L == true) {
			txtCodigo.setText(null);
			txtNome.setText(null);
			txtCPF.setText(null);
			txtDataNasc.setText(null);
			txtParentesco.setText(null);
		}
	}
	
	public DependenteView() {
		criaComponentes();
		Botoes(true, false, false, false, false);
		HabilitaTxt(false);
		
	}
	

	private void criaComponentes() {
		

		
		janela = new JFrame();

		JPanel panel = new JPanel();
//		panel.setLayout(new JTabbedPane());

		btnSalvar = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");
		btnPesquisar = new JButton("Pesquisar");
		btnIncluir = new JButton("Incluir");
		btnEditar = new JButton("Editar");
		btnExcluir = new JButton("Excluir");

		txtCodigo = new JTextField(6);
		txtNome = new JTextField(30);
		txtCPF = new JTextField(12);
		txtDataNasc = new JTextField(8);
		txtParentesco = new JTextField(30);
		txtPesquisar = new JTextField(15);
		
		tblDependente = new JTable(data, coluna);
		
		lblCodigo = new JLabel("Codigo");
		lblNome = new JLabel("Nome");
		lblCPF = new JLabel("CPF");
		lblDataNasc = new JLabel("Data de Nascimento");
		lblParentesco = new JLabel("Parentesco");
		
		panel.add(lblCodigo);
		panel.add(txtCodigo);
		panel.add(lblNome);
		panel.add(txtNome);
		panel.add(lblCPF);
		panel.add(txtCPF);
		panel.add(lblDataNasc);
		panel.add(txtDataNasc);
		panel.add(lblParentesco);
		panel.add(txtParentesco);
		panel.add(btnIncluir);
		panel.add(btnSalvar);
		panel.add(btnCancelar);
		panel.add(btnEditar);
		panel.add(btnExcluir);
		panel.add(btnPesquisar);
		panel.add(txtPesquisar);
		panel.add(tblDependente);
		
		btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
		
		janela.setContentPane(panel);
		janela.setTitle("Cadastro de Funcionario");
		janela.setSize(600, 400);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void tbl_alunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_alunosMouseClicked
        Botoes(true,true,true,false,false);
        int index = tblDependente.getSelectedRow();
        if(index>=0 && index<Modelo.getRowCount()){
            String temp[] = Modelo.getAluno(index);
            txtCodigo.setText(temp[0]);
            txtNome.setText(temp[1]);
            txtCPF.setText(temp[2]);
            txtParentesco.setText(temp[3]);
        }
    }
	
	private void btnIncluirActionPerformed(ActionEvent evt) {
		Botoes(false, false, false, true, true);
		HabilitaTxt(true);
		LimpaTxt(true);
	}
	
	private void btnEditarActionPerformed(ActionEvent evt) {
		Botoes(false, false, false, true, true);
		HabilitaTxt(true);
	}
	
	private void btnExcluirActionPerformed(ActionEvent evt) {
		
	}
	
	private void btnSalvarActionPerformed(ActionEvent evt) {
		 if( DependenteController.SalvarDependente(txtCodigo.getText(),txtNome.getText(),txtCPF.getText(),txtDataNasc.getText(),txtParentesco.getText()) ){
//	            this.LoadTable();
	            JOptionPane.showMessageDialog(null, "Dependente salvo com sucesso!");
	        }else
	            JOptionPane.showMessageDialog(null, "Erro ao salvar o Dependente!");
		Botoes(true, true, true, false, false);
		HabilitaTxt(false);
	}
		
	private void btnCancelarActionPerformed(ActionEvent evt) {
		Botoes(true, true, true, false, false);
		HabilitaTxt(false);
		LimpaTxt(true);
	}
	
	private void btnPesquisarActionPerformed(ActionEvent evt) {

	}
}