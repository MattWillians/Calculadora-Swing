package br.projeto.calculadora;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import br.projeto.calculadora.tela.DisplayCalculadora;
import br.projeto.calculadora.tela.TecladoCalculadora;

@SuppressWarnings("serial")
public class MainCalculadora extends JFrame {
	
	public MainCalculadora() {
	
		organizarLayout();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(337,509);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void organizarLayout() {
		setLayout(new BorderLayout());
		
		DisplayCalculadora displayCalculadora = new DisplayCalculadora();
		displayCalculadora.setPreferredSize(new Dimension(this.getWidth(), 90));
		add(displayCalculadora, BorderLayout.NORTH);
		
		TecladoCalculadora tecladoCalculadora = new TecladoCalculadora();
		tecladoCalculadora.setPreferredSize(new Dimension(this.getWidth(), (this.getHeight() - displayCalculadora.getHeight())));
		add(tecladoCalculadora, BorderLayout.CENTER);
		setAlwaysOnTop(true);
	}

	public static void main(String[] args) {
		new MainCalculadora();
	}
}
