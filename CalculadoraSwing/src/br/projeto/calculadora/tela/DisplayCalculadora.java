package br.projeto.calculadora.tela;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.projeto.calculadora.logica.Memoria;
import br.projeto.calculadora.logica.ObserverMemoria;

@SuppressWarnings("serial")
public class DisplayCalculadora extends JPanel implements ObserverMemoria {
	
	private final JLabel label;
	
	public DisplayCalculadora() {
		 
		Memoria.getInstance().addObserverMemory(this);
		
		label = new JLabel(Memoria.getInstance().getTextoAtual());
		
		//Config Tela 
		setBackground(new Color(31, 29, 29));
		
		//Config Label 
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 40));
		
		add(label);
	}

	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
}
