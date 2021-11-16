import java.util.Arrays;
import java.util.Date;
import java.util.List;

class Jv018{  
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
		// Declara List
		List<String> items = null;
		// Declara uma linha com campos separadops por vírgulas
		String line = "18, Fulano de tal, Belo Horizonte, MG";
		System.out.println("Linha lida: "+line);
		// Explode a linha em campos, usando a vírgula como separador
		// e coloca os resultados em elementos, a partir do índice 0, em um array
		items = Arrays.asList(line.split("\\s*,\\s*"));
		System.out.println("Item convertidos em Array: "+items);
		nome = items.get(0);
		System.out.println("Número: "+nome);
		nome = items.get(1);
		System.out.println("Nome: "+nome);
		nome = items.get(2);
		System.out.println("Municípío: "+nome);
		nome = items.get(3);
		System.out.println("UF: "+nome);
		}
	}  