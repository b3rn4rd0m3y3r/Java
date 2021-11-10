import java.util.Date;
import java.text.SimpleDateFormat;

class Jv004{  
	// Chamada java Jv004 João 1960
	
	public static void main(String args[]){  
		// Define formato de extração das informações da data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// Converte o formato de saida em string para inteiro
		int Ano = Integer.parseInt(sdf.format(new Date())); 
		String Nome = args[0]; 
		int Idade = Ano - Integer.parseInt(args[1]);
		System.out.println(Nome);
		System.out.println(Idade);
		}  
	}  