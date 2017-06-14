package manipuladorArquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Classe que escreve no arquivo 
 * @author LAYLA E SIMAO
 *
 */
public class EscreverArquivo {
	
	private List<String> linhas;
	private String nomeArquivo;
	
	public EscreverArquivo(List<String> linhasArquivo, String nome){
		linhas = linhasArquivo;
		nomeArquivo = nome;
	
		
	}
	
	public void gerarArquivo(){
		
		FileWriter arq = null;
		
		/**
		 * Metodo responsavel por lancar um possivel erro ao criar o arquivo 
		 */
		
		try {
			arq = new FileWriter(nomeArquivo + ".txt");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar o Arquivo de Estatísticas! \n" + e.getMessage());
		}
		
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for (int i = 0; i < linhas.size(); i++) 
			gravarArq.println(linhas.get(i));
		
		/**
		 * Metodo responsavel por lancar um possivel erro ao fechar o arquivo 
		 */
		try {
			arq.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Fechar o Arquivo de Estatísticas! \n" + e.getMessage());
		}
	}

}
