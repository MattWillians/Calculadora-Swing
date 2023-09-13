package br.projeto.calculadora.tela;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BotaoCalculadora extends JButton {
	
	public BotaoCalculadora(String label, Color cor) {
		// TODO
		setText(label);
		setForeground(Color.WHITE);
		
		setFont(new Font("courier", Font.PLAIN, 25));
		setOpaque(true);
		setBackground(cor);
		setBorder(BorderFactory.createLineBorder(new Color(31, 29, 29)));
	}
}
