import java.util.ArrayList;
import java.util.List;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>(List.of(
            new Movie("Life of Enes", "Anas", 2024, "no", 140, "DRAMA"),
            new Movie("Life of Anas", "Enes", 2024, "no", 140, "DRAMA"),
            new Movie("LifeIs", "Enes", 2024, "no", 140, "DRAMA"),
            new Movie("LifeWas", "Enes", 2024, "no", 140, "DRAMA")));


    //Tilføjer film til arrraylist
    public void addMovie(Movie movie) {
        collection.add(movie);
    }

    //Fjerner film fra arraylist
    public void removeMovie(int index) {
        collection.remove(collection.get(index));
        //collection.remove(movie);
    }

    //Metode til at lave filmliste
    public String movieList() {
        String toPrint = "";
        int counter = 1;
        for (Movie movie : collection) {
            toPrint += ("\nMovie " + counter++ + ": \nTitle: " + movie.getTitle() + "\nDirector: " + movie.getDirector() +
                    "\nRelease year: " + movie.getYearCreated() + "\nIn color: " + movie.getIsInColor() +
                    "\nLength (in minutes): " + movie.getLengthInMinutes() + "\nGenre: " + movie.getGenre() + "\n");
        }
        return toPrint;
    }


    //Metode til at finde film
    public ArrayList<Movie> movieFinder(String searchTerm) {
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : collection) {

            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(movie);
            }
        }
        return results;
    }

    public ArrayList<Movie> getCollection() {
        return collection;
    }
}