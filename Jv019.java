import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;
import java.io.FileReader;

class Jv019{  
	// Chamada java Jv019
	// Leitura de um CSV e grava��o de Arraylist
	// Itera��o avan�ada de array
	// Propriedades da classe
	// Vers�o
	String versao = "1.0";
	public static void main(String args[]){  
		// 1 - Declara��o de vari�veis E INICIALIZA��O.
		String nome = "";
		int tamanho = nome.length();
		String[] temp = new String[3];
		FileReader fr = null;
		// 2 - Declara ArrayList
		List<List<String>> records = new ArrayList<>();
		List<String> items = null;
		// 3 -Try determina um contexto ou CLOSURE como no javascript
		try {
			// No caso da refer�ncia ser apenas o nome do arquivo,
			// indica que ele est� NO MESMO DIRET�RIO do programa Java
			fr = new FileReader("Dados.csv");
			// Captura uma poss�vel exce��o
			} catch(Exception e){
				e.printStackTrace(); 
				}
		
		// 4 - Se a vari�vel "fr" n�o for declarada fora do escopo do TRY,
		// ela n�o � reconhecida quando de sua chamada em new Scanner
		Scanner in = new Scanner(fr);
		// 5 - Imprime o tipo da vari�vel, confirmando seu tipo
		// e um c�digo interno que diz onde ela � guardada
		System.out.println("Tipo de fr: "+fr.getClass().getName()+" Hashcode: "+fr.hashCode());
		// 6 - L� o "input" resultante do Scanner do arquivo apontado pela vari�vel "fr",
		// linha por linha e o armazena em uma vari�vel que � um Array de arrays "records"
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("Linha lida: "+line);
			items = Arrays.asList(line.split("\\s*,\\s*"));
			System.out.println("itens convertidos em Array: "+items);
			records.add(items);
			}		
		// 7 - Impress�o da Stream (cadeia de bytes) do Array de arrays records
		System.out.println("-------------------------------------------------");
		System.out.println(records.stream().collect(Collectors.toList()) );
		System.out.println("-------------------------------------------------");
		// 8 - Envia a stream para o arranjo
		String[][] arranjo = records.stream()
			.map(l -> l.stream().toArray(String[]::new))
			.toArray(String[][]::new);		
		
		// 9 - Imprime o arranjo utilizando loop
		
		for (String[] innerArray : arranjo){
			//FAZ LOOP PELAS COLUNAS DA LINHA ATUAL
			for( String item : innerArray ){
				System.out.printf("%s ", item);
				}
			System.out.println();
			}
		}

			}  