import java.util.Scanner;

public class Questao01 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Informe um n�mero: ");
		
		Integer f = in.nextInt();
		
		Integer c = (5 * (f - 32)/9);
		
		System.out.println(c);
	}

}