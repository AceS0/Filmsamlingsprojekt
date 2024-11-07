import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();



    //Tilføjer film til arrraylist
    public void addMovie(Movie movie) {
        collection.add(movie);
        saveMovieFile();

    }

    //Fjerner film fra arraylist
    public void removeMovie(Movie movie) {
        collection.remove(movie);
        saveMovieFile();
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

    public String movieList(String term1) {
        String toPrint = "";
        int counter = 1;
        sortBy(term1);
        for (Movie movie : collection) {
            toPrint += ("\nMovie " + counter++ + ": \nTitle: " + movie.getTitle() + "\nDirector: " + movie.getDirector() +
                    "\nRelease year: " + movie.getYearCreated() + "\nIn color: " + movie.getIsInColor() +
                    "\nLength (in minutes): " + movie.getLengthInMinutes() + "\nGenre: " + movie.getGenre() + "\n");
        }
        return toPrint;
    }

    public String movieListShort() {
        String toPrint = "";
        int counter = 1;
        for (Movie movie : collection) {
            toPrint += ("\nMovie " + counter++ + ": \nTitle: " + movie.getTitle());
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

    //Metode til at få beskrivelse på filmen.
    public String getMovieDesc(Movie movieName) {
        return (movieName.getTitle() + ";" + movieName.getDirector() +
                ";" + movieName.getYearCreated() + ";" + movieName.getIsInColor() +
                ";" + movieName.getLengthInMinutes() + ";" + movieName.getGenre());
    }

    public String saveMovieFile() {
        File file = new File("save.txt");
        try {
                FileWriter writer = new FileWriter(file);
                writer.write("Title,Director,Year created,Is it in color?,Length in minutes,Genre.\n");
                for (Movie movie : collection) {
                    writer.write(getMovieDesc(movie));
                    writer.append("\n");
                }
                writer.close();
                return "\"You have succesfully saved your movie(s) to a save.txt\"";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public String loadMovieFile() {
        File file = new File("save.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            sc.nextLine();

        Movie checkFile = null;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");


            checkFile = new Movie((attributes[0]), (attributes[1]),
                    (Integer.parseInt(attributes[2])), (Boolean.parseBoolean(attributes[3])),
                    (Integer.parseInt(attributes[4])), (attributes[5]));

            Boolean duplicate = false;
            for(Movie movie : collection){
                 if (movie.getTitle().equals(checkFile.getTitle())){
                     duplicate = true;
                     break;
                 }
            }
             if (!duplicate){
                 collection.add(checkFile);
             }
        }
            sc.close();
        } catch (FileNotFoundException e) {
        } catch (NullPointerException e){
        } catch (NoSuchElementException e){
        }
        return "\nLoaded successfully.";
    }

    //sorterer film ifølge term
    public void sortBy(String term) {
        switch (term) {
            case "director" -> collection.sort(Movie.DIRECTOR_COMPARATOR);
            case "release" -> collection.sort(Movie.RELEASE_COMPARATOR);
            case "color" -> collection.sort(Movie.COLOR_COMPARATOR);
            case "length" -> collection.sort(Movie.LENGTH_COMPARATOR);
            case "genre" -> collection.sort(Movie.GENRE_COMPARATOR);
            default -> collection.sort(Movie.TITLE_COMPARATOR);
        }
    }

    public String deleteFile(){
       File file = new File("save.txt");
       if (file.delete()){
            return "You have deleted a file ";
        } else {
           return "You need a file, before you can delete.";
       }
    }


}