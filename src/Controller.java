import java.util.ArrayList;

public class Controller {
    MovieCollection movies = new MovieCollection();
    public Controller(){
    }

    public void addMovieToCollection(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
       movies.addMovie(new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre));
    }

    public ArrayList<Movie> runSearch(String searchTerm)
    {
        ArrayList<Movie> results = movies.movieFinder(searchTerm);
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
                String editnum = edit.replaceAll("[^0-9]", "");
                movie.setYearCreated(Integer.parseInt(editnum));
                return edit;
            case "color":
                movie.setIsInColor(edit);
                return edit;
            case "length":
                String editnum2 = edit.replaceAll("[^0-9]", "");
                movie.setLengthInMinutes(Integer.parseInt(editnum2));
                return edit;
            case "genre":
                movie.setGenre(edit);
                return edit;
            default:
                return null;
        }
    }

    public MovieCollection getMovies(){
        return movies;
    }



}