package br.projeto.calculadora.tela;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.projeto.calculadora.logica.Memoria;

@SuppressWarnings("serial")
public class TecladoCalculadora extends JPanel implements ActionListener {
	
	Memoria memoria = Memoria.getInstance();
	
	private final Color CINZA_MEDIO = new Color(56, 54, 54);
	private final Color CINZA_CLARO = new Color(71, 69, 69);
	private final Color AZUL_CLARO = new Color(53, 152, 232);

	public TecladoCalculadora() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		
		setLayout(layout);
		
		//lINHA 1
		constraints.gridwidth = 3;
		addBotao("AC", CINZA_MEDIO, constraints, 0,0);
		constraints.gridwidth = 1;
		addBotao("/", CINZA_MEDIO, constraints, 3,0);
		
		//linha 2
		addBotao("7", CINZA_CLARO, constraints, 0,1);
		addBotao("8", CINZA_CLARO, constraints, 1,1);
		addBotao("9", CINZA_CLARO, constraints, 2,1);
		addBotao("*", CINZA_CLARO, constraints, 3,1);
		//linha 3
		addBotao("4", CINZA_CLARO, constraints, 0,2);
		addBotao("5", CINZA_CLARO, constraints, 1,2);
		addBotao("6", CINZA_CLARO, constraints, 2,2);
		addBotao("-", CINZA_CLARO, constraints, 3,2);
		// linha 4
		addBotao("3", CINZA_CLARO, constraints, 0,3);
		addBotao("2", CINZA_CLARO, constraints, 1,3);
		addBotao("1", CINZA_CLARO, constraints, 2,3);
		addBotao("+", CINZA_CLARO, constraints, 3,3);
		//linha 5
		constraints.gridwidth = 2;
		addBotao("0", CINZA_CLARO, constraints, 0,4);
		constraints.gridwidth = 1;
		addBotao(",", CINZA_CLARO, constraints, 2,4);
		addBotao("=", AZUL_CLARO, constraints, 3,4);
		
		setBackground(Color.LIGHT_GRAY);
	}

	private void addBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = x;
		c.gridy = y;
		BotaoCalculadora botaoCalculadora = new BotaoCalculadora(texto, cor);
		
		botaoCalculadora.addActionListener(this);
		add(botaoCalculadora, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botaoPressionado = (JButton) e.getSource();
			memoria.processarComando(botaoPressionado.getText());
		}
	}
}
