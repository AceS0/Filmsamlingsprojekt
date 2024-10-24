public class Controller {
    MovieCollection movies = new MovieCollection();
    Movie movie;
    public Controller(){
    }

    public void addMovieToCollection(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
       movies.addMovie(new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre));
    }

    public Movie runSearch(String searchTerm)
    {
        return movies.movieFinder(searchTerm);
    }
    public String EditMovie(Movie movie, int casenumber, String edit)
    {
        return null;
    }
}