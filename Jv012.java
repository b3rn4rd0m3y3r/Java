import java.util.Date;

class Jv012{  
	// Chamada java Jv012
	// Iteração avançada de array
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  
		// Declaração de variáveis
		String nome = "Joao";
		int tamanho = nome.length();
		int[] minhasContas = new int[10];
		minhasContas[0] = 11;
		minhasContas[1] = 22;
		minhasContas[4] = 33;
		minhasContas[7] = 44;
		//minhasContas[12] = 122;
		for( int i : minhasContas ){
			System.out.println(i);
			}
		System.out.println("");
		}
	}  