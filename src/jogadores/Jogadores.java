package jogadores;


import java.util.ArrayList;
import java.util.List;

import manipuladorArquivo.LerArquivo;

/**
 * Classe 
 * @author LAYLA E SIMAO
 *
 */
public class Jogadores {
	
	private int nroInstrucoes;
	private int nroJogadores;
	private double saldoJogadores;
	private List<String> linhasArquivo;
	private LerArquivo objLerArquivo;
	private List<Jogador> jogadores; 
	// Para leitura no arquivo
	int marcador;
	String acumulado;

	/**
	 * 
	 * @param nomeArq
	 */
	
	
	public Jogadores(String nomeArq) {
		
		nroJogadores = 0;
		saldoJogadores = 0;
		
		objLerArquivo = new LerArquivo(nomeArq);
		jogadores = new ArrayList<>();
		
		linhasArquivo = new ArrayList<>();
		linhasArquivo = objLerArquivo.coletarLinhasArquivo();
		
	}
	

	public int getNroInstrucoes() {
		return nroInstrucoes;
	}

	
	
	public List<String> getLinhasArquivo() {
		return linhasArquivo;
	}

	/**
	 * Metodo que cadastra os jogadores
	 */
	

	public  void cadastraJogadores(){
		
		jogadores.add(0, new Jogador(0, 999999.00)); // O jogador 0 é o banco, que contem saldo infinito em caixa (REGRA 2)
		
		marcador = 0;
		acumulado = "";
				
		linhasArquivo.set(0, linhasArquivo.get(0) + "%");
		
		for (int i = 0; i < linhasArquivo.get(0).length(); i++) {
			
			if (linhasArquivo.get(0).charAt(i) != '%')
				acumulado += linhasArquivo.get(0).charAt(i);
			
			else{
				
				if (marcador == 0)
					nroInstrucoes = Integer.parseInt(acumulado);
					
				else if (marcador == 1)
					nroJogadores = Integer.parseInt(acumulado);
				
				else if (marcador == 2)
					saldoJogadores = Double.parseDouble(acumulado);
				
				marcador++;
				acumulado = "";
				
			}
			
		}
		
		

		for (int i = 1; i <= nroJogadores; i++)
			jogadores.add(i, new Jogador(i, saldoJogadores));
		
		
			
	}
	
	public List<Jogador> getJogadores(){
		return this.jogadores;
	}
	
	

}
