package Aula10;
import java.util.HashMap;

public class Ex01 {
    static HashMap<String, String> colorDef = new HashMap<String, String>();
    public static void main(String[] args){

        colorDef.put("Branco", "Que tem a cor da neve.");
        colorDef.put("Vermelho", "Que tem a cor do fogo.");
        colorDef.put("Rosa", "Que tem a cor da rosa.");
        colorDef.put("Laranja", "Que tem a cor da laranja.");
        colorDef.put("Azul", "Que tem a cor do mar.");
        System.out.println(colorDef.toString());
        colorDef.put("Azul", "Que tem a cor do céu.");
        colorDef.remove("Rosa");
        String s = "MAPA: ";
            for (String i : colorDef.keySet()){
                s += "Cor: "  + i + " Definição: " + colorDef.get(i) +"/";
            }
            s += "CORES: ";
            for (String i : colorDef.keySet()){
                s += "Cor: "  + i + "/";
            }
            s += "Definições: ";
            for (String i : colorDef.keySet()){
                s += "Definição: " + colorDef.get(i) + "/";
            }
            System.out.println(s);
        

    }
       

    
}
