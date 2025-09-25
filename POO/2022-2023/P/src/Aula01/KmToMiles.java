package Aula01;
import java.util.Scanner;

public class KmToMiles {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		double km, miles;
		System.out.print("Insira distância em km: ");
		km = sc.nextDouble();
		miles = km / 1.609;
		System.out.printf("A distância em milhas é %.3f",miles);
		sc.close();
	}
}
