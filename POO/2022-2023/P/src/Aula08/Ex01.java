package Aula08;

public class Ex01 {
    
        public static void main(String[] args) {
            Empresa e = new Empresa("Empresa");
            e.addViatura(new Motociclo("11-AA-11", "Honda", "CBR", 100, "Deportiva"));
            e.addViatura(new Motociclo("22-BB-22", "Yamaha", "R1", 200, "Deportiva"));
            e.addViatura(new Motociclo("33-CC-33", "Suzuki", "GSX-R", 300, "Deportiva"));
            e.addViatura(new PesadoMerc("44-55-CD", "Skoda", "35A", 200, 56, 6, 2, 450));

            e.addTrajeto("11-AA-11", 100);
            e.ultimoTrajeto("11-AA-11");
        }
}
