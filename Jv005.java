import java.util.Date;
import java.text.SimpleDateFormat;

class Jv005{  
	// Chamada java Jv005 João 1960
	// Propriedades da classe
	// Nome da classe
	String className = this.getClass().getSimpleName();	
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  

		// Define formato de extração das informações da data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// Converte o formato de saida em string para inteiro
		int Ano = Integer.parseInt(sdf.format(new Date())); 
		String Nome = args[0]; 
		int Idade = Ano - Integer.parseInt(args[1]);
		Jv005 jv = new Jv005();
		System.out.println(jv.className);
		System.out.println(jv.versao);
		System.out.println(Nome);
		System.out.println(Idade);
		}  
	}  