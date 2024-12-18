package Controller;
import java.util.ArrayList;

import model.*;
import filehandling.*;

public class Controller {
    private MovieCollection movies = new MovieCollection();
    private Backup fh = new Backup();


    //Controller.Controller kobler alle metoder fra andre klasser til ui.UserInterface.
    public void addMovieToCollection(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movies.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        runSave();
    }


    public void removeMovieFromCollection(Movie movie){
        movies.removeMovie(movie);
        runSave();
    }


    public ArrayList<Movie> runSearch(String searchTerm) {
        ArrayList<Movie> results = movies.movieFinder(searchTerm);
        return results;
    }

    public String EditMovie(Movie movie, String command, String edit) {
        switch (command) {
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
                if (edit.equalsIgnoreCase("yes")) {
                    movie.setIsInColor(true);
                }else movie.setIsInColor(false);
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

    public MovieCollection getMovies() {
        return movies;
    }

    public String runSave(){
        return fh.saveMovieFile(movies);
    }

    public String runLoad(){
        return fh.loadMovieFile(movies);
    }

    public String runDeleteFile(){
        return fh.deleteFile();
    }
 }