import java.util.ArrayList;

public class Controller {
    MovieCollection movies = new MovieCollection();
    public Controller(){


    }

    public void addMovieToCollection(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
       movies.addMovie(new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre));

    }
}
