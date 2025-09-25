package Aula10;
import java.util.ArrayList;
import java.util.TreeMap;

public class Ex02 {
    
    public static void main(String[] args){

        TreeMap<String, ArrayList<String>> colorDef = new TreeMap<String, ArrayList<String>>();
        colorDef.put("Branco", new ArrayList<String>());
        colorDef.get("Branco").add("Que tem a cor da neve.");
        colorDef.get("Branco").add("Que tem a cor da cal.");
        colorDef.get("Branco").add("Que tem a cor da geada.");
        colorDef.get("Branco").add("Que tem a cor da luz.");
        colorDef.put("Vermelho", new ArrayList<String>());
        colorDef.get("Vermelho").add("Que tem a cor do fogo.");
        colorDef.get("Vermelho").add("Que tem a cor do morango.");
        colorDef.get("Vermelho").add("Que tem a cor da maça.");
        colorDef.get("Vermelho").add("Que tem a cor da lava."); 
        colorDef.put("Azul", new ArrayList<String>());
        colorDef.get("Azul").add("Que tem a cor do céu.");
        colorDef.get("Azul").add("Que tem a cor da mar.");
        colorDef.get("Azul").add("Que tem a cor do gelo.");
        colorDef.get("Azul").add("Que tem a cor do mirtilo ."); 
        colorDef.put("Verde", new ArrayList<String>());
        colorDef.get("Verde").add("Que tem a cor da relva.");
        colorDef.get("Verde").add("Que tem a cor da arvore.");
        colorDef.get("Verde").add("Que tem a cor da cato.");

        System.out.println(colorDef.toString());

    }
}
