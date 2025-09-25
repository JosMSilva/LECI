package Aula07;

public class Ex01 {

    public static void main(String[] args){
        Triangulo t1 = new Triangulo("Vermelho", 2, 2, 2);
        Triangulo t2 = new Triangulo("Vermelho", 2, 2, 2);
        Triangulo t3 = new Triangulo("Vermelho", 2, 2, 3);
        Triangulo t4 = new Triangulo("Azul", 2, 2, 2);
        System.out.println(t1.equals(t2));
        System.out.println(t1.equals(t3));
        System.out.println(t1.equals(t4));
        System.out.println(t3.equals(t4));
    }

   
}
