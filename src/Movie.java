public class Movie {
    //Attributter
    private String title;
    private String director;
    private int yearCreated;
    private String isInColor;
    private int lengthInMinutes;
    private String genre;

    //Konstruktør
    public Movie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.isInColor = isInColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
    }

    public Movie(){

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

    public String getIsInColor() {
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

    public void setIsInColor(String isInColor) {
        this.isInColor = isInColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }
}