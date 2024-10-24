import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();
    public void addMovie(Movie movie)
    {
        collection.add(movie);
    }

    public String movieList() {
        String a = "";
        for (Movie movie : collection) {
            return ("Title: "+movie.getTitle()+"\nDirector: "+movie.getDirector()+
                    "\nRelease year: "+movie.getYearCreated()+"\nIn color: "+movie.getIsInColor()+
                    "\nLength (in minutes): "+movie.getLengthInMinutes()+"\nGenre: "+movie.getGenre()+"\n");
        }
        return null;
    }

    public ArrayList<Movie> movieFinder(String searchTerm) {
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : collection) {

            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                 results.add(movie);
            }
        }
        return results;
    }


}