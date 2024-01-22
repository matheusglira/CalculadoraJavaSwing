package br.com.mg.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULTI, SUB, SOMA, IGUAL, VIRGULA, RESTO;
	}
	
	private static final Memoria instancia = new Memoria();
	
	private final List<MemoriaObserver> obs = new ArrayList<>();
	
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	private Memoria() {
		
	}
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void addObserver(MemoriaObserver o) {
		obs.add(o);
	}
	
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void executarComando(String texto) {
		
		TipoComando tipo = detectarTipo(texto);
		
		if(tipo == null) {
			return;
		}else if(tipo == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if(tipo == TipoComando.NUMERO || tipo == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = calcularResultado();
			
			ultimaOperacao = tipo;
			textoBuffer = textoAtual;
		}
		
		obs.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String calcularResultado() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numAtual = Double.parseDouble(textoAtual.replace(",", "."));
		double result = 0;
		
		if(ultimaOperacao == TipoComando.SOMA) {
			result = numBuffer + numAtual;
		}else if(ultimaOperacao == TipoComando.SUB) {
			result = numBuffer - numAtual;
		}else if(ultimaOperacao == TipoComando.MULTI) {
			result = numBuffer * numAtual;
		}else if(ultimaOperacao == TipoComando.DIV) {
			result = numBuffer / numAtual;
		}
		
		String resultFinal = Double.toString(result).replace(".", ",");
		boolean inteiro = resultFinal.endsWith(",0");
		
		return inteiro ? resultFinal.replace(",0", "") : resultFinal;
	}

	private TipoComando detectarTipo(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch(NumberFormatException e) {
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if("/".equals(texto)) {
				return TipoComando.DIV;
			} else if("*".equals(texto)) {
				return TipoComando.MULTI;
			} else if("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if("-".equals(texto)) {
				return TipoComando.SUB;
			}else if("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if(",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			}
		}
		
		return null;
	}
	
}
