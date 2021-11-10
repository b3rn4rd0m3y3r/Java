import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

class Jv008{  
	// Chamada java Jv005 Jo�o 1960
	// Propriedades da classe
	// Nome da classe
	String className = this.getClass().getSimpleName();	
	// Vers�o
	String versao = "1.0";
	public static void main(String args[]){  
		// Declara��o de vari�veis
		String s1;
		int anoNascto;
		// Define um rastreador de entrada padr�o (teclado)
		Scanner sc = new Scanner(System.in);
		// Define formato de extra��o das informa��es da data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// Entrada dos dados
		System.out.print("Entre com o seu nome: ");
		s1 = sc.nextLine();
		System.out.print("Entre com o seu ano de nascimento: ");
		anoNascto = sc.nextInt();

		// Instancia a Data do sistema e ...
		// Converte o formato de saida em string para inteiro
		int Ano = Integer.parseInt(sdf.format(new Date())); 
		String Nome = s1; 
		int Idade = Ano - anoNascto;
		System.out.println(Nome);
		System.out.println(Idade);
		}  
	}  