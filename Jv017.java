import java.util.Date;
import java.util.Scanner;
import java.io.FileReader;

class Jv017{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis E INICIALIZAÇÃO.
		String nome = "Joao Cabral de Melo Neto";
		int tamanho = nome.length();
		String[] temp = new String[3];
		FileReader fr = null;
		// Try determina um contexto ou CLOSURE como no javascript
		try {
			// No caso da referência ser apenas o nome do arquivo,
			// indica que ele está NO MESMO DIRETÓRIO do programa Java
			fr = new FileReader("Dados.csv");
			} catch(Exception e){ e.printStackTrace(); }
		
		// Se a variável "fr" não for declarada fora do escopo do TRY,
		// ela não é reconhecida quando de sua chamada em new Scanner
		Scanner in = new Scanner(fr);
		// Imprime o tipo da variável, confirmando seu tipo
		// e um código interno que diz onde ela é guardada
		System.out.println("Tipo de fr: "+fr.getClass().getName()+" Hashcode: "+fr.hashCode());
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println(line);
			}		
		System.out.println("");
		}
	}  