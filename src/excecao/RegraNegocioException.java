package excecao;
/**
 * Classe responsavel por possivéis excecoes e tratamentos 
 * @author LAYLA E SIMAO
 *
 */
public class RegraNegocioException extends Exception {

	private static final long serialVersionUID = 4041522257431100820L;


	public RegraNegocioException(){
		super();
	}
	
	
	/**
	 * Cria a exceção com a mensagem passada
	 * @param msg
	 */
	public RegraNegocioException(String msg){
		super(msg);
	}
	
}
