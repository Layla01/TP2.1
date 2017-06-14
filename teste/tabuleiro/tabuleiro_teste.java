package tabuleiro;

import static org.junit.Assert.*;


import org.junit.Test;
/**
 * Classe para testes do jogo
 * @author LAYLA E SIMAO
 *
 */
public class tabuleiro_teste {

	@Test
	public void testTabuleiro() {
	 Tabuleiro tab= new Tabuleiro ("tabuleiro1");
	 assertEquals(tab.getTabuleiro().get(0),null);
	}

	/*@Test
	public void testPreencheTabuleiro() {
		("Not yet implemented");
	}*/

}
