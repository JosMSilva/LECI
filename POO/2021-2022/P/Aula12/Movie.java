package Aula12;

public class Movie {
    
    public Movie(String name, String score, String rating,String genre, String duration){
        set(name, score, rating, genre, duration);
    }

    public void set(String name, String score, String rating,String genre, String duration){
        this.name = name;
        this.score = score;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;

    }
    public String name(){
        return name;
    }
    public String score(){
        return score;
    }
    public String rating(){
        return rating;
    }
    public String genre(){
        return genre;
    }
    public String duration(){
        return duration;
    }
    
    private String name, genre, rating, duration, score;

    @Override public String toString(){
        return String.format(name + " " + score + " " + rating + " " + genre + " " + duration);
    }
}

