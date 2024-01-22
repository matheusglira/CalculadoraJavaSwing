package br.com.mg.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.mg.calc.modelo.Memoria;
import br.com.mg.calc.modelo.MemoriaObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver{
	
	private final JLabel label;
	
	public Display() {
		Memoria.getInstancia().addObserver(this);
		
		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.BOLD, 26));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 27));
		
		add(label);
	}
	
	@Override
	public void valorAlterado(String newValor) {
		label.setText(newValor);
	}
}
