import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public void removeMovie(Movie movie) {
        collection.remove(movie);
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

    public String movieListShort(){
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

    public void saveMovieFile(){
        try {
            FileWriter writer = new FileWriter("save.txt");

            for (Movie movie : collection ){
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
            while(data != -1) {
                System.out.print((char)data);
                data = reader.read();
            }
            System.out.println();
            sc = new Scanner("save.txt");
            sc.nextLine();
            while (sc.hasNext()){
                String line = sc.nextLine();
                String[] attributes = line.split(": ");


                datae = new Movie((attributes[0]),(attributes[1]),
                        (Integer.parseInt(attributes[2])),(attributes[3]),
                        (Integer.parseInt(attributes[4])),(attributes[5]));
                collection.add(datae);
            }


            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}