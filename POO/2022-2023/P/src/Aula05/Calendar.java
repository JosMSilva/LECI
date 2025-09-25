package Aula05;
import java.util.HashMap;

public class Calendar {

    public Calendar(int weekDay, int year){
        assert weekDay >= 1 && weekDay <= 7 : "Dia da semana inválido";
        assert year >= 0 : "Ano inválido";
        this.weekDay = weekDay;
        this.year = year;
    }
    
    public int year(){
        return year;
    }

    public int firstWeekdayOfYear(){
        return weekDay;
    }

    public int firstWeekdayOfMonth(int month){
        assert month >= 1 && month <= 12 : "Mês inválido";
        int days = 0;
        for(int i = 1; i < month; i++){
            days += Date.monthDays(i, year);
        }
        return (weekDay + days) % 7;
    }

    public void addEvent(Date date){
        assert date.year() == year : "Ano inválido";
        events.put(date, 1);
    }

    public void removeEvent(Date date){
        assert date.year() == year : "Ano inválido";
        events.remove(date);
    }

    public boolean hasEvent(Date date){
        assert date.year() == year : "Ano inválido";
        return events.containsKey(date);
    }

    public void printMonth(int month){
        System.out.println("Dom\tSeg\tTer\tQua\tQui\tSex\tSab");
        int start = firstWeekdayOfMonth(month);
        for (int i = 1; i <= start; i++) {
            System.out.print("\t");
        }
        for (int i = 1; i <= Date.monthDays(month, year) ; i++) {
            Date d1 = new Date(i, month, year);
            System.out.print(hasEvent(d1));
            if(hasEvent(d1)) System.out.print("*");
            System.out.print(i + "\t");
            if((i + start) % 7 == 0) System.out.println("");
        }
        System.out.println("");
    }

    private int weekDay, year;

    private HashMap<Date, Integer> events = new HashMap<>();
}
