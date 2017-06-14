package tiposImoveis;

/**
 * Classe responsavel por manipular dados de todos os imoveis 
 * @author LAYLA E SIMAO
 *
 */
public abstract class Imovel {


	private String imvDescricao;
	private double imvValor;
	private double imvTaxaAluguel;
	private int imvIdProprietario;
	
	/**
	 * 
	 * @param imvDescricao
	 * @param imvValor
	 * @param imvTaxaAluguel
	 */
	public Imovel(String imvDescricao, int imvValor, double imvTaxaAluguel) {
		
		this.imvDescricao = imvDescricao;
		this.imvValor = imvValor;
		this.imvTaxaAluguel = imvTaxaAluguel;
		imvIdProprietario = 0; // Cada imóvel inicialmente pertence ao banco, que possui id = 0 (REGRA 1)
	
	}


	public String getImvDescricao() {
		return imvDescricao;
	}


	public double getImvValor() {
		return imvValor;
	}


	public double getImvTaxaAluguel() {
		return imvTaxaAluguel;
	}


	public int getImvIdProprietario() {
		return imvIdProprietario;
	}


	public void setImvIdProprietario(int imvIdProprietario) {
		this.imvIdProprietario = imvIdProprietario;
	}
	
	
	
}
