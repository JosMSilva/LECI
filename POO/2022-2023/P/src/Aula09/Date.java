package Aula09;

public class Date {

    public Date(int day, int month, int year){
        assert valid(day, month, year) : "Data inválida";
        set(day, month, year);
    }

    public static boolean validMonth(int month){
        return month >= 1 && month <= 12;
    }

    public static int monthDays(int month, int year){
        assert validMonth(month);
        if (month == 2){
            if(leapYear(year)){
                return 29;
            }else{
                return 28;
            }
            
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }else{
            return 31;
        }
    }

    public static boolean leapYear(int year){
        return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
    }

    public static boolean valid(int day, int month, int year){
        return validMonth(month) && day >= 1 && day <= monthDays(month, year);
    }

    public void set(int day, int month, int year){
        assert valid(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public int day(){
        return day;

    }

    public int month(){
        return month;
    }

    public int year(){
        return year;
    }

    public void increment(){
        assert valid(day, month, year);
        if(day == monthDays(month, year)){
            if(month == 12){
                day = 1;
                month = 1;
                year += 1;
            }else{
                day = 1;
                month += 1;

            }
            
        }else{
            day += 1;
        }
        
    }

    public void decrement(){
        assert valid(day, month, year);
        if(day == 1){
            if(month == 1){
                month = 12;
                year -= 1;
                day = 31;
            }else{
                month -= 1;
                day = monthDays(month, year);

            }
            
        }else{
            day -= 1;
        }
        
    }

    private int day, month, year;

    @Override public String toString(){
        return String.format("%04d/%02d/%02d", year, month, day);
    }
    
}

