import java.util.ArrayList;

public class Controller {
    MovieCollection movies = new MovieCollection();
    Movie movie;
    public Controller(){
    }

    public void addMovieToCollection(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
       movies.addMovie(new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre));
    }

    public ArrayList<Movie> runSearch(String searchTerm)
    {
        ArrayList<Movie> results = movies.movieFinder(searchTerm);
        System.out.println(searchTermfor (Movie movie : collection) {

        if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
            results.add(movie);
        }
    });
        return results;
    }
    public String EditMovie(Movie movie, String gotcha, String edit)
    {
        switch (gotcha) {
            case "title":
                movie.setTitle(edit);
                return edit;
            case "director":
                movie.setDirector(edit);
                return edit;
            case "year":
                movie.setYearCreated(Integer.parseInt(edit));
                return edit;
            case "color":
                movie.setIsInColor(edit);
                return edit;
            case "length":
                movie.setLengthInMinutes(Integer.parseInt(edit));
                return edit;
            case "genre":
                movie.setGenre(edit);
                return edit;
            default:
                return null;
        }
    }
}