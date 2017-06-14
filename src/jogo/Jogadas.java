package jogo;

import jogadores.Jogadores;
import tabuleiro.Tabuleiro;
import tiposImoveis.PasseAVez;

/**
 * 
 * @author LAYLA E SIMAO
 *
 */

public class Jogadas {
	
	private Jogadores jogadores;
	private Tabuleiro tabuleiro;
	private int qtdeRodadas;
	
	/**
	 * 
	 * @param nomeArqTabuleiro
	 * @param nomeArqJogadas
	 */
	
	public Jogadas(String nomeArqTabuleiro, String nomeArqJogadas){
		
		tabuleiro = new Tabuleiro(nomeArqTabuleiro);
		tabuleiro.preencheTabuleiro();
		jogadores = new Jogadores(nomeArqJogadas);
		jogadores.cadastraJogadores();
		qtdeRodadas = 0;
		
	}
	
		
	public int getQtdeRodadas() {
		return qtdeRodadas;
	}



	public void inicializaLargada(){ // Coloca todos os jogadores na posicao de Start
		
		for (int i = 1; i < jogadores.getJogadores().size(); i++)
			jogadores.getJogadores().get(i).setJogPosicao(tabuleiro.posicaoStart());
		
	}
	
	
	// Implementação da REGRA 6: Se o jogador tiver saldo menor que zero, suas propriedades retornam ao Banco e podem ser compradas pelos outros jogadores;
	private boolean analisaSaldoPositivoJogador(int id){
		
		boolean positivo = true;
		
		// o jogador em questao possui saldo negativo
		if (jogadores.getJogadores().get(id).getJogSaldo() < 0){
			positivo = false;
			
			// esse artificio, do atributo JogFalso, serve para que o jogador inativo devolva os imoveis ao banco "mais de uma vez", evitando que o tabuleiro seja 
			// percorrido mais de uma vez, desnecessariamente;
			jogadores.getJogadores().get(id).setJogAtivo(false);
			
			// suas propriedades sao devolvidas ao banco
			// somente uma vez, quando está ativo e passa a ser inativo
			for (int i = 1; i < tabuleiro.getTamanho() && jogadores.getJogadores().get(id).isJogAtivo(); i++) {
				
				if (tabuleiro.getTabuleiro().get(i).getImvIdProprietario() == id)
					tabuleiro.getTabuleiro().get(i).setImvIdProprietario(0);
				
			}
			
		}
		
		return positivo;
		
	}
	
	
	// Implementação da REGRA 8: O sistema deverá terminar o jogo quando restar apenas um jogador com saldo positivo
	// A função abaixo verifica se há somente um jogador com saldo positivo
	private boolean jogadorUnico(){
		
		int qtdeJogadoresAtivos = 0;
		
		// calcula a quantidade de jogadores com saldo positivo
		for (int i = 1; i < jogadores.getJogadores().size(); i++) {
			
			if (jogadores.getJogadores().get(i).getJogSaldo() > 0)
				qtdeJogadoresAtivos++;
			
		}
		
		
		// se for somente 1 jogador ativo, retorna verdadeiro, se for diferente disso, falso
		if (qtdeJogadoresAtivos == 1)
			return true;
		else
			return false;
		
	}
	
	
	
	
	public void executarJogadas(){
		
		int marcador = 0;
		String acumulado = "";
		int id_jogador = 0;
		int valor_dado = 0;
		int posicao_inicial = 0;
		int posicao_destino = 0;
		double valor_aluguel = 0.0;

		// A primeira linha do arquivo, linha 0, não é uma linha que descreve uma rodada em si
		// Implementação da REGRA 8: O sistema deverá terminar o jogo quando restar apenas um jogador com saldo positivo, 
		// ou quando for lido no arquivo de entrada a instrução DUMP;
		
		for (int i = 1; i <= jogadores.getNroInstrucoes() 
				&& (!jogadores.getLinhasArquivo().get(i).equals("DUMP;")) && (jogadorUnico() == false); i++) {
			
			marcador = 0;
						
			for (int j = 0; j < jogadores.getLinhasArquivo().get(i).length(); j++) {
				
				if (jogadores.getLinhasArquivo().get(i).charAt(j) != ';')
					acumulado += jogadores.getLinhasArquivo().get(i).charAt(j);
				
				
				else{
					
					if (marcador == 1)
						id_jogador = Integer.parseInt(acumulado);
					
					else if (marcador == 2)
						valor_dado = Integer.parseInt(acumulado);
						
					marcador++;
					acumulado = "";
					
				}
				
				
			}
			
			
			// Implementação da REGRA 6: Se o jogador tiver saldo menor que zero (saldo negativo), as jogadas do arquivo correspondente a ele deverão ser ignoradas
			if (! analisaSaldoPositivoJogador(id_jogador))
				continue;
			
			// isso nao quer dizer que o jogador 1 esta ativo. Mas, se o primero jogador é analisado, podendo 
			// efetuar ou nao uma jogada, isso quer dizer que uma nova jogada se inicia
			if (id_jogador == 1)
				qtdeRodadas++;
			
			
			
			posicao_inicial = jogadores.getJogadores().get(id_jogador).getJogPosicao();
				
			// verifica caso a nova posicao do jogador nao ultrapasse o tabuleiro (nao deu volta)
			if (posicao_inicial + valor_dado <= tabuleiro.getTamanho()){ 
				
				// atualiza posicao do jogador no tabuleiro
				jogadores.getJogadores().get(id_jogador).setJogPosicao( 
					posicao_inicial + valor_dado  );
				
				posicao_destino = jogadores.getJogadores().get(id_jogador).getJogPosicao();
				
				
				// Implementação da REGRA 7: Cada vez que um jogador passar pela a posição inicial deverá receber um valor de 500 reais do Banco
				// verifica se passou a posição de start
				if ( (posicao_inicial < tabuleiro.posicaoStart()) && (posicao_destino >= tabuleiro.posicaoStart()) ){
					
					// O banco retira 500 reais
					jogadores.getJogadores().get(0).setJogSaldo( jogadores.getJogadores().get(0).getJogSaldo() - 500);
					
					// e passa para o jogador
					jogadores.getJogadores().get(id_jogador).setJogSaldo( jogadores.getJogadores().get(id_jogador).getJogSaldo() + 500);
						
				}
				
				
			// caso ultrapasse	
			}else{
				
				//atualiza posição do jogador no tabuleiro
				jogadores.getJogadores().get(id_jogador).setJogPosicao( 
						posicao_inicial + valor_dado - tabuleiro.getTamanho() );
				
				posicao_destino = jogadores.getJogadores().get(id_jogador).getJogPosicao();
							
				
				// atualiza o numero de voltas dadas no tabuleiro pelo jogador
				jogadores.getJogadores().get(id_jogador).setJogVoltas( 
						jogadores.getJogadores().get(id_jogador).getJogVoltas() + 1);
				
				
				// Implementação da REGRA 7: Cada vez que um jogador passar pela a posição inicial deverá receber um valor de 500 reais do Banco
				// verifica se passou a posição de start
				if ( (posicao_inicial < tabuleiro.posicaoStart()) || (posicao_destino >= tabuleiro.posicaoStart()) ){
					
					// O banco retira 500 reais
					jogadores.getJogadores().get(0).setJogSaldo( jogadores.getJogadores().get(0).getJogSaldo() - 500);
					
					// e passa para o jogador
					jogadores.getJogadores().get(id_jogador).setJogSaldo( jogadores.getJogadores().get(id_jogador).getJogSaldo() + 500);
						
				}
					
				
			}
			
			// Implementação da REGRA 5
			// Caso o jogador tenha caído em uma posição do tabuleiro que tem um passe a vez, o jogador não paga nada e uma nova instrução deverá ser lida;
			if (tabuleiro.getTabuleiro().get(posicao_destino) instanceof PasseAVez){
				
				// contabilizar a quantidade de passa a vez que cada jogador teve
				jogadores.getJogadores().get(id_jogador).setJogQtdePassaVez( 
						jogadores.getJogadores().get(id_jogador).getJogQtdePassaVez() + 1);
				
				//lê uma nova instrução, diretamente
				continue;
			}
				
			
			

			// Implementação da REGRA 3
			//Caso a posição do tabuleiro que o jogador caiu, for um imóvel do Banco a ação será: jogador deverá comprar o imóvel.
			if (tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario() == 0){
				
				
				// verifica se o jogador possui saldo suficiente:
				if (jogadores.getJogadores().get(id_jogador).getJogSaldo() >= 
						tabuleiro.getTabuleiro().get(posicao_destino).getImvValor()){
					
					// retira o dinheiro do saldo do jogador:
					jogadores.getJogadores().get(id_jogador).setJogSaldo( 
							jogadores.getJogadores().get(id_jogador).getJogSaldo() - tabuleiro.getTabuleiro().get(posicao_destino).getImvValor() );
					
					// passa o dinheiro para o banco
					jogadores.getJogadores().get(0).setJogSaldo(
							jogadores.getJogadores().get(0).getJogSaldo() + tabuleiro.getTabuleiro().get(posicao_destino).getImvValor() );
					
					// passa o imóvel para o jogador:
					tabuleiro.getTabuleiro().get(posicao_destino).setImvIdProprietario( id_jogador );
					
					 
					// contabiliza no valor total gasto na compra de imoveis do jogador
					jogadores.getJogadores().get(id_jogador).setJogTotalCompras(
							jogadores.getJogadores().get(id_jogador).getJogTotalCompras() + tabuleiro.getTabuleiro().get(posicao_destino).getImvValor()
							);
				}	
				
			// Implementação da REGRA 4
			// Caso o imóvel seja de outro jogador a ação será: jogador deverá pagar o valor do aluguel.
			}else if (tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario() != id_jogador){
						// nao faz sentido o jogador pagar aluguel do imovel que ele é dono, pra ele mesmo
					
					
				valor_aluguel = tabuleiro.getTabuleiro().get(posicao_destino).getImvValor() * 
						tabuleiro.getTabuleiro().get(posicao_destino).getImvTaxaAluguel()/100;
					
				// retira o dinheiro do saldo do jogador que chegou no imovel
				jogadores.getJogadores().get(id_jogador).setJogSaldo( 
						jogadores.getJogadores().get(id_jogador).getJogSaldo() - valor_aluguel );
					
					
				// passa o dinheiro para o jogador que é o dono desse imóvel
				jogadores.getJogadores().get( tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario() ).setJogSaldo(
						jogadores.getJogadores().get(tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario()).getJogSaldo()
						+ valor_aluguel );
					
				//contabiliza a quantidade monentaria de aluguel pago por cada jogador
				jogadores.getJogadores().get(id_jogador).setJogAluguelPago( 
						jogadores.getJogadores().get(id_jogador).getJogAluguelPago() + valor_aluguel);
					
					
				// contabiliza a quantidade monentaria de aluguel recebido por cada jogador
				jogadores.getJogadores().get( tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario() ).setJogAluguelRecebido(
						jogadores.getJogadores().get( tabuleiro.getTabuleiro().get(posicao_destino).getImvIdProprietario() ).getJogAluguelRecebido()
						+ valor_aluguel );
					
					
			}
				
			
		}
		
		
	}


	public Jogadores jogadoresArquivo() {
		return jogadores;
	} 
	
	
}
