package manipuladorArquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * Classe que lê do arquivo 
 * @author LAYLA E SIMAO
 *
 */


public class LerArquivo {
	
	private FileReader arq;
	BufferedReader lerArq;
	
	/**
	 * Metodo que le do arquivo e tem como parametro o nome
	 * @param nome
	 */
	public LerArquivo(String nome) {
		
		try {			
			arq = new FileReader(nome + ".txt");
			
		} catch (FileNotFoundException e) {			
			JOptionPane.showMessageDialog(null, "Erro ao criar o FileReader! \n " + e.getMessage());	
			
		}
		
		lerArq = new BufferedReader(arq);
		
		
	}
	
	
	
	public List<String> coletarLinhasArquivo(){
				
		String linha = "";
		List<String> linhasArquivo = new ArrayList<>();
		
		
		try {
			linha = lerArq.readLine();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na Leitura nas Linhas do Arquivo! \n" + e.getMessage());
			return null;
			
		}
		
		
		
		while (linha != null){
			
			if (linhasArquivo.size() == 0)
				linhasArquivo.add(linha);
			else
				linhasArquivo.add(linha + ";");

			try {
				linha = lerArq.readLine();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erro na Leitura nas Linhas do Arquivo! \n" + e.getMessage());
				return null;
				
			}					
		}
		
		try {
			arq.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Fechar o Arquivo! \n" + e.getMessage());
		}
		
		return linhasArquivo;
		
	}
	
	
	
	
	

}
