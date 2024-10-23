import java.util.ArrayList;

public class Controller {
    MovieCollection movies = new MovieCollection();
    public Controller(){


    }

    public void addMovieToCollection(Movie movie){

        movies.addMovie(movie);
    }

    public Movie createMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre){
       return new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre);

    }
}
