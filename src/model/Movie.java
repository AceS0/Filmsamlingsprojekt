package model;

import java.util.Comparator;

public class Movie implements Comparable {
    //Attributter
    private String title;
    private String director;
    private int yearCreated;
    private boolean isInColor;
    private int lengthInMinutes;
    private String genre;
    public static Comparator<Movie> TITLE_COMPARATOR = Comparator.comparing(Movie::getTitle);
    public static Comparator<Movie> DIRECTOR_COMPARATOR = Comparator.comparing(Movie::getDirector);
    public static Comparator<Movie> RELEASE_COMPARATOR = Comparator.comparing(Movie::getYearCreated);
    public static Comparator<Movie> COLOR_COMPARATOR = Comparator.comparing(Movie::getIsInColor);
    public static Comparator<Movie> LENGTH_COMPARATOR = Comparator.comparing(Movie:: getLengthInMinutes);
    public static Comparator<Movie> GENRE_COMPARATOR = Comparator.comparing(Movie::getGenre);

    //Konstruktør
    public Movie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.isInColor = isInColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
    }



//-----------------------------------------------------Getter/Setter--------------------------------------------------//

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean getIsInColor() {
        return isInColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsInColor(boolean isInColor) {
        this.isInColor = isInColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}