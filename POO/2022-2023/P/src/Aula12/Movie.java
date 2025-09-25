package Aula12;

public class Movie {

    public Movie(String title, double score, String rating, String genre, int runTime) {
        this.title = title;
        this.score = score;
        this.rating = rating;
        this.genre = genre;
        this.runTime = runTime;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public int getRunTime() {
        return runTime;
    }
    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
    
    private String title, genre, rating;
    private double score;
    private int runTime;
    @Override
    public String toString() {
        return title + "\t" + genre + "\t" + rating + "\t" + score + "\t" + runTime;
    }
}
