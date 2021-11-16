import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

class Jv019{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis E INICIALIZAÇÃO.
		String nome = "";
		int tamanho = nome.length();
		String[] temp = new String[3];
		FileReader fr = null;
		// Declara ArrayList
		List<List<String>> records = new ArrayList<>();
		List<String> items = null;
		// Try determina um contexto ou CLOSURE como no javascript
		try {
			// No caso da referência ser apenas o nome do arquivo,
			// indica que ele está NO MESMO DIRETÓRIO do programa Java
			fr = new FileReader("Dados.csv");
			// Captura uma possível exceção
			} catch(Exception e){
				e.printStackTrace(); 
				}
		
		// Se a variável "fr" não for declarada fora do escopo do TRY,
		// ela não é reconhecida quando de sua chamada em new Scanner
		Scanner in = new Scanner(fr);
		// Imprime o tipo da variável, confirmando seu tipo
		// e um código interno que diz onde ela é guardada
		System.out.println("Tipo de fr: "+fr.getClass().getName()+" Hashcode: "+fr.hashCode());
		// Lê o "input" resultante do Scanner do arquivo apontado pela variável "fr",
		// linha por linha e o armazena em uma variável que é um Array de arrays "records"
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("Linha lida: "+line);
			items = Arrays.asList(line.split("\\s*,\\s*"));
			System.out.println("itens convertidos em Array: "+items);
			records.add(items);
			
			}		
		
		System.out.println("");
		}
	}  