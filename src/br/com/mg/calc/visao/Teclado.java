package br.com.mg.calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.mg.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {
	
	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(97, 100, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);
	public Teclado() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
				
		c.gridwidth = 2;
		addBtn("AC", COR_CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1;
		addBtn("%", COR_CINZA_ESCURO, c, 2, 0);
		addBtn("/", COR_LARANJA, c, 3, 0);
		
		addBtn("7", COR_CINZA_CLARO, c, 0, 1);
		addBtn("8", COR_CINZA_CLARO, c, 1, 1);
		addBtn("9", COR_CINZA_CLARO, c, 2, 1);
		addBtn("*", COR_LARANJA, c, 3, 1);
		
		addBtn("4", COR_CINZA_CLARO, c, 0, 2);
		addBtn("5", COR_CINZA_CLARO, c, 1, 2);
		addBtn("6", COR_CINZA_CLARO, c, 2, 2);
		addBtn("-", COR_LARANJA, c, 3, 2);
		
		addBtn("1", COR_CINZA_CLARO, c, 0, 3);
		addBtn("2", COR_CINZA_CLARO, c, 1, 3);
		addBtn("3", COR_CINZA_CLARO, c, 2, 3);
		addBtn("+", COR_LARANJA, c, 3, 3);
		
		c.gridwidth = 2;
		addBtn("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		addBtn(",", COR_CINZA_CLARO, c, 2, 4);
		addBtn("=", COR_LARANJA, c, 3, 4);
	
	}
	
	private void addBtn(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			Memoria.getInstancia().executarComando(btn.getText());;
		}
	}
	
}
