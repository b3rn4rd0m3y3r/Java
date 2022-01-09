// Class Declaration
public class Jv022 {
	// Instance Variables
	int Lado1;
	int Lado2;

	public Jv022(){
		// Construtor simples
		}
	
	public Jv022(int pLado1, int pLado2){
		Lado1 = pLado1;
		Lado2 = pLado2;
		}
	
	public int Area(){
		return (Lado1 * Lado2);
		}

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		// Primeiro construtor
		Jv022 Ret1 = new Jv022();
		System.out.print("Lado1 : ");
		int i1 = sc.nextInt();
		System.out.print("Lado2 : ");
		int i2 = sc.nextInt();
		Ret1.Lado1 = i1;
		Ret1.Lado2 = i2;
		System.out.println(Ret1.Area());
		// Segundo construtor
		Jv022 Ret2 = new Jv022(7, 8);
		System.out.println(Ret2.Area());
		sc.close();
    }
}