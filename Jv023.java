//import Retangulo;
// Class Declaration
public class Jv023 {
	// Instance Variables


	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		// Primeiro construtor
		Retangulo Ret1 = new Retangulo();
		System.out.print("Lado1 : ");
		int i1 = sc.nextInt();
		System.out.print("Lado2 : ");
		int i2 = sc.nextInt();
		Ret1.Lado1 = i1;
		Ret1.Lado2 = i2;
		System.out.println(Ret1.Area());
		// Segundo construtor
		Retangulo Ret2 = new Retangulo(7, 8);
		System.out.println(Ret2.Area());
		sc.close();
    }
}