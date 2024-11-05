import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        return ("\nTitle: " + movieName.getTitle() + "\nDirector: " + movieName.getDirector() +
                "\nRelease year: " + movieName.getYearCreated() + "\nIn color: " + movieName.getIsInColor() +
                "\nLength (in minutes): " + movieName.getLengthInMinutes() + "\nGenre: " + movieName.getGenre() + "\n");
    }

    public void saveMovieFile() {
        try {
            FileWriter writer = new FileWriter("save.txt");

            for (Movie movie : collection) {
                writer.write(getMovieDesc(movie));
                writer.append("\n");
            }
            writer.close();
            System.out.println("You have succesfully saved your movie(s) to a save.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMovieFile() {
        Movie datae = null;
        try {
            FileReader reader = new FileReader("save.txt");
            Scanner sc = null;
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            System.out.println();
            sc = new Scanner("save.txt");
            sc.nextLine();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] attributes = line.split(": ");


                datae = new Movie((attributes[0]), (attributes[1]),
                        (Integer.parseInt(attributes[2])), (Boolean.parseBoolean(attributes[3])),
                        (Integer.parseInt(attributes[4])), (attributes[5]));
                collection.add(datae);
            }


            reader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
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
       return "You have deleted a file " + file.delete();

    }


}