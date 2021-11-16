import java.util.Date;

class Jv014{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis
		String nome = "Joao Cabral de Melo Neto";
		int tamanho = nome.length();
		String[][] minhasContas = { {"1.1", "1.2"}, {"2.1","2.2", "2.3"}, { "3.1", "3.2","3.3", "3.4" } };
		for(int linha = 0; linha < minhasContas.length; linha++){
			//FAZ LOOP PELAS COLUNAS DA LINHA ATUAL
			for( int coluna = 0; coluna < minhasContas[linha].length; coluna++){
				System.out.printf("%s ", minhasContas[linha][coluna]);
				}
			System.out.println();
			}
		System.out.println("");
		}
	}  