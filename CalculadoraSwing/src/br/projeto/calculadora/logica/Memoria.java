package br.projeto.calculadora.logica;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
		SOMA, SUBTRACAO, DIVISAO, MULTIPLICACAO, VIRGULA, RESULTADO, LIMPAR, NUMERO;
	}
	
	private static Memoria instanceMemoria = new Memoria();
	
	private TipoComando ultimoComando = null;
	private boolean substituir = false;
	
	private String textoAtual = "";
	private String textoBuffer = "";
	
	
	private List<ObserverMemoria> observersList = new ArrayList<>();
	
	public void addObserverMemory(ObserverMemoria o) {
		observersList.add(o);
	}
	
	private Memoria() {}
	
	public static Memoria getInstance() {
		return instanceMemoria;
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void processarComando(String valor) {
		
		TipoComando tipoComando = detectarTipoComando(valor);
		
		if(tipoComando == null) {
			return;
			
		} else if (tipoComando == TipoComando.LIMPAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimoComando = null;
			
		} else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultado();
			textoBuffer = textoAtual;
			ultimoComando = tipoComando;
		}
		
		observersList.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultado() {
		if (ultimoComando == null || ultimoComando == TipoComando.RESULTADO) {
			return textoAtual;
		}
		
		double numBuffer = Double.parseDouble(textoBuffer.replace(",","."));
		double numAtual = Double.parseDouble(textoAtual.replace(",","."));
		
		double resultado = 0;
		
		if (ultimoComando == TipoComando.DIVISAO) {
			resultado = numBuffer / numAtual;
		} else if (ultimoComando == TipoComando.MULTIPLICACAO) {
			resultado = numBuffer * numAtual;
		}  else if (ultimoComando == TipoComando.SOMA) {
			resultado = numBuffer + numAtual;
		}  else if (ultimoComando == TipoComando.SUBTRACAO) {
			resultado = numBuffer - numAtual;
		} 
		
		String reString = Double.toString(resultado).replace(".", ",");
		
		boolean inteiro = reString.endsWith(",0");
		
		return inteiro ? reString.replace(",0", "") : reString;
	}

	private TipoComando detectarTipoComando(String valor) {
		if(textoAtual.isEmpty() && valor.equals("")) {
			return null;	
		}
		
		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;
	
		} catch (NumberFormatException e) {
			if ("AC".equals(valor)) {
				return TipoComando.LIMPAR;
				
			} else if ("/".equals(valor)) {
				return TipoComando.DIVISAO;
				
			} else if ("*".equals(valor)) {
				return TipoComando.MULTIPLICACAO;
				
			} else if ("-".equals(valor)) {
				return TipoComando.SUBTRACAO;
				
			} else if ("+".equals(valor)) {
				return TipoComando.SOMA;
				
			} else if ("=".equals(valor)) {
				return TipoComando.RESULTADO;
				
			} else if (",".equals(valor) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;	
			}
		
		}
		return null;
	}
}
