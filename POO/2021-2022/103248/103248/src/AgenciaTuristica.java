import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AgenciaTuristica {

    public AgenciaTuristica(String nome, String local){
        set(nome, local);
        this.eventosMap = new LinkedHashMap<>();
        
    }



    public void set(String nome, String local){
        
        this.nome = nome;
        this.local = local;

    }
    public String nome(){
        return nome;

    }
    public String local(){
        return local;
    }

    public PacoteTuristico pacoteTuristico(String nome, int noites, double preço) {
        PacoteTuristico pacote = new PacoteTuristico(nome, noites, preço);
        return pacote;
    }

    public void reserva(PacoteTuristico p, int i) {
        if(i > 4){
           double pre = p.precoTotal(p, i);
           pre = (0.95 * pre);
           p.setPreco(pre);

        }
    }

   

    public String listaPacotes() {
        String str = "Events:";
        for (String c : pacotesMap.keySet()) {
            str += "\n" + c;
            for (PacoteTuristico e : pacotesMap.get(c))
                str += "\n" + e;
        }

        return str;
    }


    public String listaReservas() {
        String str = "Events:";
        for (String c : pacotesMap.keySet()) {
            str += "\n" + c;
            for (PacoteTuristico e : pacotesMap.get(c))
                str += "\n" + e;
        }

        return str;
    }

    
    private String nome, local;
    private Map<String, Set<PacoteTuristico>> pacotesMap;

    
}
