package Aula06;

public class Ex01 {
    public static void main(String[] args) {
        Date d = new Date(29, 2, 2020);
        Pessoa p = new Pessoa("João", 12345678, d);
        System.out.println(p.toString());
        Aluno a = new Aluno("Maria", 87654321, d, null);
        System.out.println(a.toString());
        System.out.println(a.nome());
        Contacto c = new Contacto(p,"@ua.pt", 0);
        System.out.println(c.toString());
        
    }
    
}
