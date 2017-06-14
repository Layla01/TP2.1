package tabuleiro;

import java.util.ArrayList;
import java.util.List;

import manipuladorArquivo.LerArquivo;
import tiposImoveis.Comercio;
import tiposImoveis.Hospital;
import tiposImoveis.Hotel;
import tiposImoveis.Imovel;
import tiposImoveis.Industria;
import tiposImoveis.PasseAVez;
import tiposImoveis.Residencia;
import tiposImoveis.Start;

/**
 * Classe responsavel por manipular o tabuleiro do jogo
 * @author LAYLA E SIMAO
 *
 */

public class Tabuleiro {

	private int qtdePosicoes;
	private List<String> linhasArquivo;
	private LerArquivo objLerArquivo;
	private List<Imovel> tabuleiro;
	private int tamanho;
	
	public Tabuleiro(String nomeArq){
		objLerArquivo = new LerArquivo(nomeArq);
		tabuleiro = new ArrayList<>();
		tabuleiro.add(0, null); // a primeira posição, a posição zero, será descartada!
		linhasArquivo = new ArrayList<>();
		linhasArquivo = objLerArquivo.coletarLinhasArquivo();
	}
	
	
	/**
	 *Metodo responsavel por povoar o tabuleiro do jogo.
	 *
	 */
	public void preencheTabuleiro(){
		

		qtdePosicoes = Integer.parseInt(linhasArquivo.get(0));	
		tamanho = qtdePosicoes;

		String acumulado = "";
		int marcador = 0;
		int posicao = 0;
		int tipo_posicao = 0;
		int tipo_imovel = 0;
		int valor_imovel = 0;
		double taxa_aluguel = 0.0;
		
	
		
		for (int i = 1; i <= qtdePosicoes; i++) {
			
			marcador = 0;

			
			for (int j = 0; j < linhasArquivo.get(i).length(); j++) {
			
						
				if (linhasArquivo.get(i).charAt(j) != ';')
					acumulado += linhasArquivo.get(i).charAt(j);
				
				else{
						
					if (marcador == 1) // coleta posicao
						posicao = Integer.parseInt(acumulado);
					
					
					else if (marcador == 2){ // coleta o tipo da posicao (passe a vez, start, alguma residencia)
						tipo_posicao = Integer.parseInt(acumulado);
											
						if (tipo_posicao == 1) // ja insere de uma vez caso a posicao seja de start
							tabuleiro.add(posicao, new Start());
						
						else if (tipo_posicao == 2) // ja insere de uma vez caso a posicao seja de passe a vez
							tabuleiro.add(posicao, new PasseAVez());
					
					}
					
					else if (marcador == 3) // coleta o tipo de imovel: 1- Res, 2- Com. e assim por diante
						tipo_imovel = Integer.parseInt(acumulado);
					
					else if (marcador == 4) // coleta o valor do imovel
						valor_imovel = Integer.parseInt(acumulado);
					
					else if (marcador == 5){ // coleta o valor da taxa de aluguel e já faz as inserções dos imoveis do tabuleiro
																		
						taxa_aluguel = Double.parseDouble(acumulado);
						
						if (tipo_posicao == 3){
							
							if (tipo_imovel == 1)
								tabuleiro.add(posicao, new Residencia(valor_imovel, taxa_aluguel));
							
							else if (tipo_imovel == 2)
								tabuleiro.add(posicao, new Comercio(valor_imovel, taxa_aluguel));
							
							else if (tipo_imovel == 3)
								tabuleiro.add(posicao, new Industria(valor_imovel, taxa_aluguel));
							
							else if (tipo_imovel == 4)
								tabuleiro.add(posicao, new Hotel(valor_imovel, taxa_aluguel));
							
							else if (tipo_imovel == 5)
								tabuleiro.add(posicao, new Hospital(valor_imovel, taxa_aluguel));
														
							
						}
						
					}
					
					
					acumulado = "";	
					marcador++;			
					
					
				}
								
			}
			
		}
			
		
	}
	
	public int getTamanho() {
		return tamanho;
	}


	public List<Imovel> getTabuleiro(){
		return this.tabuleiro;
	}
	
	
	public int posicaoStart(){
		
		for (int i = 0; i < tabuleiro.size(); i++) {
			
			if (tabuleiro.get(i) instanceof Start)
				return i;
			
		}

		return 0;
		
	}
	

}
