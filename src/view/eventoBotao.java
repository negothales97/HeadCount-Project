package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class eventoBotao extends JFrame {
	private JLabel lblBotao1;
	private JButton btBotao2;
	private JLabel lbBotao2;
	private JButton btBotao1;

	public eventoBotao() {
		setTitle("Evento Botão");
		this.setSize(319, 248);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		final int LARG_DEFAULT = 550;
		final int ALT_DEFAULT = 450;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2;
		this.setBounds(0, 0, 319, 248);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		{
			lblBotao1 = new JLabel();
			getContentPane().add(lblBotao1);
			lblBotao1.setText("Botão 1");
			lblBotao1.setBounds(19, 19, 61, 16);
		}
		{
			btBotao1 = new JButton();
			getContentPane().add(btBotao1);
			btBotao1.setText("Botão 1");
			btBotao1.setBounds(19, 47, 106, 23);
			btBotao1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("teste");
				}
			});
		}
		{
			lbBotao2 = new JLabel();
			getContentPane().add(lbBotao2);
			lbBotao2.setText("Botão 2");
			lbBotao2.setBounds(168, 19, 61, 16);
		}
		{
			btBotao2 = new JButton();
			getContentPane().add(btBotao2);
			btBotao2.setText("Botão 2");
			btBotao2.setBounds(168, 47, 96, 23);
			btBotao2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("teste2");
				}
			});
		}
	}

	public static void main(String args[]) {
		new eventoBotao().setVisible(true);
	}
}