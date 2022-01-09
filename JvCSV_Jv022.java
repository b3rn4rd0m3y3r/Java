import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.FileReader;

class Jv022{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis E INICIALIZAÇÃO.
		String numero = "";
		String nome = "";
		String uf = "";
		String dtreg = "";
		int tamanho = nome.length();
		String[] temp = new String[3];
		FileReader fr = null;
		//List<String> DtReg = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat port = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
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
		String line = in.nextLine();
		// Imprime os títulos de coluna
		trataCab(line);
		System.out.println("=====================================");
		while (in.hasNextLine()) {
			line = in.nextLine();
			System.out.println("Linha lida: "+line);
			// Imprime os campos de cada registro
			trataReg(line, sdf, port);
			System.out.println("-----------------------------------------------------------------");
			}		
		records.add(items);
		System.out.println("");
		}
	public static void trataCab(String line){
			List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
			
			// Exibe os itens
			System.out.println("Item convertidos em Array: "+items);
			System.out.println("Número: "+ items.get(0));
			System.out.println("Nome: "+ items.get(1));
			System.out.println("UF: "+ items.get(2));
			System.out.println("Data: "+ items.get(3));
		}
	public static void trataReg(String line, SimpleDateFormat sdf, SimpleDateFormat port){
			List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
			Date data = new Date();
			// Exibe os itens
			System.out.println("Item convertidos em Array: "+items);
			System.out.println("Número: "+ items.get(0));
			System.out.println("Nome: "+ items.get(1));
			System.out.println("UF: "+ items.get(2));
				try {
					data = sdf.parse(items.get(3));
					} catch (Exception e){
					e.printStackTrace();
					}
				System.out.println("Data: "+port.format(data));
		}
	}  