import java.util.Date;

class Jv015{  
	// Chamada java Jv012
	// Itera��o avan�ada de array
	// Propriedades da classe
	// Vers�o
	String versao = "1.0";
	public static void main(String args[]){  
		// Declara��o de vari�veis
		String nome = "Joao Cabral de Melo Neto";
		int tamanho = nome.length();
		String[][] minhasContas = { {"1.1", "1.2"}, {"2.1","2.2", "2.3"}, { "3.1", "3.2","3.3", "3.4" } };
		
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