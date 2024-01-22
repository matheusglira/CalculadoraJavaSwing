package br.com.mg.calc.modelo;

@FunctionalInterface
public interface MemoriaObserver {

	void valorAlterado(String newValor);
}
