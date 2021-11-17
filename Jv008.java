import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

class Jv008{  
	// Chamada java Jv008
	// Calcular a idade de uma pessoa
	// Propriedades da classe
	// Nome da classe
	String className = this.getClass().getSimpleName();	
	// Vers�o
	String versao = "1.0";
	public static void main(String args[]){  
		// 0 - Declara��o de vari�veis
		String s1;
		int anoNascto;
		// Define formato de extra��o das informa��es da data (Ano 4 d�gitos)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// 1 - Define um rastreador de entrada padr�o (teclado)
		Scanner sc = new Scanner(System.in);
		// 2 - Entrada dos dados
		System.out.print("Entre com o seu nome: ");
		s1 = sc.nextLine();
		System.out.print("Entre com o seu ano de nascimento: ");
		anoNascto = sc.nextInt();

		// 3 - Instancia a Data do sistema e ...
		//      Converte o formato de saida em string para inteiro
		int Ano = Integer.parseInt(sdf.format(new Date())); 
		String Nome = s1; 
		// 4 - Calcula a idade
		int Idade = Ano - anoNascto;
		System.out.println(Nome);
		System.out.println(Idade);
		}  
	}  