package Aula02;

import java.util.Scanner;

public class Ex03 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        double initialTemp, finalTemp, mass;
        System.out.print("Temperatura Inicial:");
        initialTemp = sc.nextDouble();
        System.out.print("Temperatura Final:");
        finalTemp = sc.nextDouble();
        assert initialTemp < finalTemp : "Temperatura inicial tem de ser inferior à final";
        System.out.print("Peso de Água:");
        mass = sc.nextDouble();
        assert mass > 0 : "Peso de água tem de ser positivo";
        double energy = mass * (finalTemp - initialTemp) * 4184;
        System.out.printf("A energia necessária para aquecer %.2f kg de água de %.2f ºC para %.2f ºC é %.2f J",mass,initialTemp,finalTemp,energy);
    }
    
}
