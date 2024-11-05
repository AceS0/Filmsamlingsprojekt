import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<>();
        //List.of(
            /*new Movie("Life of Enes", "Anas", 2024, false, 140, "DRAMA"),
            new Movie("Life of Anas", "Enes", 2024, false, 140, "DRAMA"),
            new Movie("LifeIs", "Enes", 2024, false, 140, "DRAMA"),
            new Movie("batman", "julemand", 2022, false, 200, "yikes"),
            new Movie("LifeWas", "Enes", 2024, false, 140, "DRAMA")));*/


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
    public static boolean compareList(ArrayList ls1, ArrayList ls2){
        return ls1.toString().contentEquals(ls2.toString())?true:false;
    }

    public String saveMovieFile() {
        File file = new File("save.txt");
        BufferedReader reader = null;
        ArrayList<Movie> temp = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file) );
            StringBuilder out = new StringBuilder();
            String headerLine = reader.readLine();
            String line = reader.readLine();
            String[] attributes = line.split(";");
            Movie checkFile = null;
            while (checkFile == null) {
                for (Movie movie : collection){
                    checkFile = new Movie((attributes[0]), (attributes[1]),
                            (Integer.parseInt(attributes[2])), (attributes[3]),
                            (Integer.parseInt(attributes[4])), (attributes[5]));
                    temp.add(checkFile);
                }
                out.append(line);   // add everything to StringBuilder
                // here you can have your logic of comparison.
            }

            if (compareList(collection, temp)){

            } else {
                return "Doesn't work";
            }

            if (line == null){
                return "Please create a movie and try again.";
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (NullPointerException e) {}


        try {
                FileWriter writer = new FileWriter("save.txt");
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Movie checkFile = null;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");


            checkFile = new Movie((attributes[0]), (attributes[1]),
                    (Integer.parseInt(attributes[2])), (attributes[3]),
                    (Integer.parseInt(attributes[4])), (attributes[5]));
            if (!collection.contains(checkFile)) {
                collection.add(checkFile);
            }
        }
        sc.close();
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


}