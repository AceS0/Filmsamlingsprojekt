import java.util.ArrayList;
import java.util.Collections;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();
    public void addMovie(Movie movie)
    {
        collection.add(movie);
    }

    public String movieList() {
        for (Movie movie : collection) {
            return ("Title: "+movie.getTitle()+"\nDirector: "+movie.getDirector()+
                    "\nRelease year: "+movie.getYearCreated()+"\nIn color: "+movie.getIsInColor()+
                    "\nLength (in minutes): "+movie.getLengthInMinutes()+"\nGenre: "+movie.getGenre()+"\n");
        }
        return null;
    }

    public Movie movieFinder() {
        for (Movie movie : collection) {

            if (movie.getTitle().equalsIgnoreCase(movie.getTitle())) {
                return movie;
            }


        }
        return null;
    }


}