import java.util.Date;
import java.util.Scanner;
import java.io.FileReader;

class Jv017{  
	// Chamada java Jv012
	// Itera��o avan�ada de array
	// Propriedades da classe
	// Vers�o
	String versao = "1.0";
	public static void main(String args[]){  
		// Declara��o de vari�veis E INICIALIZA��O.
		String nome = "Joao Cabral de Melo Neto";
		int tamanho = nome.length();
		String[] temp = new String[3];
		FileReader fr = null;
		// Try determina um contexto ou CLOSURE como no javascript
		try {
			// No caso da refer�ncia ser apenas o nome do arquivo,
			// indica que ele est� NO MESMO DIRET�RIO do programa Java
			fr = new FileReader("Dados.csv");
			} catch(Exception e){ e.printStackTrace(); }
		
		// Se a vari�vel "fr" n�o for declarada fora do escopo do TRY,
		// ela n�o � reconhecida quando de sua chamada em new Scanner
		Scanner in = new Scanner(fr);
		// Imprime o tipo da vari�vel, confirmando seu tipo
		// e um c�digo interno que diz onde ela � guardada
		System.out.println("Tipo de fr: "+fr.getClass().getName()+" Hashcode: "+fr.hashCode());
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println(line);
			}		
		System.out.println("");
		}
	}  