import java.util.Date;

class Jv016{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis
		String nome = "Joao";
		int tamanho = nome.length();
		String[][] minhasContas = { {"1.1", "1.2"}, {"2.1","2.2", "2.3"}, { "3.1", "3.2","3.3", "3.4" } };
		String[] temp = new String[3];
		System.arraycopy( minhasContas[0], 0, temp, 0, 2 );
		temp[2] = "novo";
		minhasContas[0] = temp;
		
		for (String[] innerArray : minhasContas){
			//FAZ LOOP PELAS COLUNAS DA LINHA ATUAL
			for( String item : innerArray ){
				System.out.printf("%s ", item);
				}
			System.out.println();
			}
		System.out.println("");
		}
	}  