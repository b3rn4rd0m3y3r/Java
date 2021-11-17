import java.util.Date;
import java.text.SimpleDateFormat;


class Jv006{  
	// Chamada java Jv006
	// Propriedades da classe
	// Versão
	String versao = "1.0";
	public static void main(String args[]){  

		// Define formato de extração das informações da data
		Date dt = new Date();
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
		SimpleDateFormat sdf_d = new SimpleDateFormat("dd");
		// Converte o formato de saida em string para inteiro
		String Ano = sdf_y.format(dt); 
		String Mes = sdf_m.format(dt);
		String Dia = sdf_d.format(dt);
		
		System.out.println("Dia "+Dia);
		System.out.println("Mês "+Mes);
		System.out.println("Ano "+Ano);
		}  
	}  