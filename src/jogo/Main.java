package jogo;

import estatistica.ConteudoArqEstatisticas;
/**
 * 
 * @author LAYLA E SIMAO
 *
 */

public class Main {

	public static void main(String[] args) {
		/*
		Jogadas jogs = new Jogadas("tabuleiro1", "jogadas");
		jogs.inicializaLargada();
		jogs.executarJogadas(); */
		
		ConteudoArqEstatisticas cae = new ConteudoArqEstatisticas("tabuleiro1", "jogadas", "estatisticas1");
		cae.gerarConteudoArqEstatisticas();
		

		
		

	}

}
