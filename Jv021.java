import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.FileReader;

class Jv021{  
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
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("Linha lida: "+line);
			items = Arrays.asList(line.split("\\s*,\\s*"));
			records.add(items);
			// Exibe os itens
			System.out.println("Item convertidos em Array: "+items);
			numero = items.get(0);
			System.out.println("Número: "+numero);
			nome = items.get(1);
			System.out.println("Nome: "+nome);
			uf = items.get(2);
			System.out.println("UF: "+uf);
			dtreg = items.get(3);
			// Converte em data
			if( !dtreg.equals("DataEntrada") ){
				try {
					data = sdf.parse(dtreg);
					} catch (Exception e){
					e.printStackTrace();
					}
				//nome = DtReg.get(2)+"/"+DtReg.get(1)+"/"+DtReg.get(0);
				System.out.println("Data: "+port.format(data));
				} else {
				System.out.println("Data: N/A");
				}
			}		
		
		System.out.println("");
		}
	}  