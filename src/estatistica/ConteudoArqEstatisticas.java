package estatistica;

import java.util.ArrayList;

/**
 * Classe responsavel por apresentar dados estatisticos do jogo.
 * @author LAYLA E SIMAO
 */

import java.util.List;

import jogo.Jogadas;
import manipuladorArquivo.EscreverArquivo;

public class ConteudoArqEstatisticas {
	
	private Jogadas jogadas;
	private String arquivoEstatisticas;
	
	/**
	 * Metodo que coleta os dados 
	 * @param nomeArqTabuleiro
	 * @param nomeArqJogadas
	 * @param nomeArqEstatisticas
	 */
	
	public ConteudoArqEstatisticas(String nomeArqTabuleiro, String nomeArqJogadas, String nomeArqEstatisticas){
		
		jogadas = new Jogadas(nomeArqTabuleiro, nomeArqJogadas);
		jogadas.inicializaLargada();
		jogadas.executarJogadas();
		
		arquivoEstatisticas = nomeArqEstatisticas;
		
	}
	
	/**
	 * Metodo responsavel gerar o arquivo com os respectivos dados
	 
	 */
	public void gerarConteudoArqEstatisticas(){
		
		List<String> conteudo = new ArrayList<>();
		
		String linha = "";
		
		linha = "1:"+jogadas.getQtdeRodadas();
		conteudo.add(linha);
		
		linha = "2:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + jogadas.jogadoresArquivo().getJogadores().get(i).getJogVoltas();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);
		
		
		linha = "3:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + (int) jogadas.jogadoresArquivo().getJogadores().get(i).getJogSaldo();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);

		
		linha = "4:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + (int) jogadas.jogadoresArquivo().getJogadores().get(i).getJogAluguelRecebido();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);

		
		linha = "5:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + (int) jogadas.jogadoresArquivo().getJogadores().get(i).getJogAluguelPago();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);		
		
		
		
		linha = "6:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + (int) jogadas.jogadoresArquivo().getJogadores().get(i).getJogTotalCompras();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);
		
		
		linha = "7:";
		for (int i = 1; i < jogadas.jogadoresArquivo().getJogadores().size(); i++) {
			linha += i + "-" + jogadas.jogadoresArquivo().getJogadores().get(i).getJogQtdePassaVez();
			
			if (i != jogadas.jogadoresArquivo().getJogadores().size() - 1)
				linha += ";";
			
		}
		conteudo.add(linha);		
		
		
		EscreverArquivo ea = new EscreverArquivo(conteudo, arquivoEstatisticas);
		ea.gerarArquivo();
		
	}
	

}
