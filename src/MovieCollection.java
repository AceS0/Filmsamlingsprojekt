import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();
    public void addMovie(Movie movie)
    {
        collection.add(movie);
    }

    public String movieList() {
        String toPrint = "";
        int counter = 1;
        for (Movie movie : collection) {
            toPrint += ("\nMovie " + counter++ +": \nTitle: "+movie.getTitle()+"\nDirector: "+movie.getDirector()+
                    "\nRelease year: "+movie.getYearCreated()+"\nIn color: "+movie.getIsInColor()+
                    "\nLength (in minutes): "+movie.getLengthInMinutes()+"\nGenre: "+movie.getGenre()+"\n");
        }
        return toPrint;
    }

    public Movie movieFinder(String searchTerm) {
        for (Movie movie : collection) {

            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                return movie;
            }
        }
        return null;
    }


}