package jogadores;

/**
 * Classe que armazena dados dos jogadores 
 * @author LAYLA E SIMAO
 *
 */

public class Jogador {

	private int jogId;
	private double jogSaldo;
	private int jogPosicao; // posicao no tabuleiro
	private int jogVoltas; // quantas voltas completas o jogador deu no tabuleiro
	private double jogAluguelPago;
	private double jogAluguelRecebido;
	private double jogTotalCompras;
	private int jogQtdePassaVez;
	private boolean jogAtivo; 
	
	/**
	 * Metodo que manipula os dados dos jogadores 
	 * @param jogId
	 * @param jogSaldo
	 */
	
	public Jogador(int jogId, double jogSaldo){
		this.jogId = jogId;
		this.jogSaldo = jogSaldo;
		this.jogPosicao = 1;
		this.jogVoltas = 0;
		this.jogAluguelPago = 0.0;
		this.jogAluguelRecebido = 0.0;
		this.jogTotalCompras = 0.0;
		this.jogQtdePassaVez = 0;
		this.jogAtivo = true;
		
	}

	public int getJogId() {
		return jogId;
	}

	public double getJogSaldo() {
		return jogSaldo;
	}

	public int getJogPosicao() {
		return jogPosicao;
	}


	public int getJogVoltas() {
		return jogVoltas;
	}

	public double getJogAluguelPago() {
		return jogAluguelPago;
	}

	public double getJogAluguelRecebido() {
		return jogAluguelRecebido;
	}

	
	public double getJogTotalCompras() {
		return jogTotalCompras;
	}	
	

	public int getJogQtdePassaVez() {
		return jogQtdePassaVez;
	}
	
	
	public boolean isJogAtivo() {
		return jogAtivo;
	}

	
	public void setJogId(int jogId) {
		this.jogId = jogId;
	}

	public void setJogSaldo(double jogSaldo) {
		this.jogSaldo = jogSaldo;
	}

	public void setJogPosicao(int jogPosicao) {
		this.jogPosicao = jogPosicao;
	}


	public void setJogVoltas(int jogVoltas) {
		this.jogVoltas = jogVoltas;
	}

	public void setJogAluguelPago(double jogAluguelPago) {
		this.jogAluguelPago = jogAluguelPago;
	}

	public void setJogAluguelRecebido(double jogAluguelRecebido) {
		this.jogAluguelRecebido = jogAluguelRecebido;
	}

	public void setJogTotalCompras(double jogTotalCompras) {
		this.jogTotalCompras = jogTotalCompras;
	}

	public void setJogQtdePassaVez(int jogQtdePassaVez) {
		this.jogQtdePassaVez = jogQtdePassaVez;
	}

	public void setJogAtivo(boolean jogAtivo) {
		this.jogAtivo = jogAtivo;
	}
	
	
}
