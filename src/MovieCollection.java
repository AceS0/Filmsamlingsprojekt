import java.io.*;
import java.util.*;

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

    public String movieList(String term1, String term2) {
        String toPrint = "";
        int counter = 1;
        sortBy(term1, term2);
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
                for (Movie movie : collection) {
                    if (movie.getTitle().equals(checkFile.getTitle())) {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    collection.add(checkFile);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
        } catch (NullPointerException e) {
        } catch (NoSuchElementException e) {
        }
        return "\nLoaded successfully.";
    }

    //sorterer film ifølge term
    public void sortBy(String term, String term2) {
        Comparator<Movie> title = Movie.TITLE_COMPARATOR;
        Comparator<Movie> director = Movie.DIRECTOR_COMPARATOR;
        Comparator<Movie> release = Movie.RELEASE_COMPARATOR;
        Comparator<Movie> color = Movie.COLOR_COMPARATOR;
        Comparator<Movie> length = Movie.LENGTH_COMPARATOR;
        Comparator<Movie> genre = Movie.GENRE_COMPARATOR;
        switch (term) {
            case "director" -> {
                switch (term2) {
                    case "title" -> Movie.DIRECTOR_COMPARATOR.thenComparing(title);
                    case "release" -> Movie.DIRECTOR_COMPARATOR.thenComparing(release);
                    case "color" -> Movie.DIRECTOR_COMPARATOR.thenComparing(color);
                    case "length" -> Movie.DIRECTOR_COMPARATOR.thenComparing(length);
                    case "genre" -> Movie.DIRECTOR_COMPARATOR.thenComparing(genre);
                    default -> collection.sort(director);
                }
            }
            case "release" -> {
                switch (term2) {
                    case "title" -> Movie.RELEASE_COMPARATOR.thenComparing(title);
                    case "director" -> Movie.RELEASE_COMPARATOR.thenComparing(director);
                    case "color" -> Movie.RELEASE_COMPARATOR.thenComparing(color);
                    case "length" -> Movie.RELEASE_COMPARATOR.thenComparing(length);
                    case "genre" -> Movie.RELEASE_COMPARATOR.thenComparing(genre);
                    default -> collection.sort(release);
                }
            }
            case "color" -> {
                switch (term2) {
                    case "title" -> Movie.COLOR_COMPARATOR.thenComparing(title);
                    case "director" -> Movie.COLOR_COMPARATOR.thenComparing(director);
                    case "release" -> Movie.COLOR_COMPARATOR.thenComparing(release);
                    case "length" -> Movie.COLOR_COMPARATOR.thenComparing(length);
                    case "genre" -> Movie.COLOR_COMPARATOR.thenComparing(genre);
                    default -> collection.sort(color);
                }
            }

            case "length" -> {
                switch (term2) {
                    case "title" -> Movie.LENGTH_COMPARATOR.thenComparing(title);
                    case "director" -> Movie.LENGTH_COMPARATOR.thenComparing(director);
                    case "release" -> Movie.LENGTH_COMPARATOR.thenComparing(release);
                    case "color" -> Movie.LENGTH_COMPARATOR.thenComparing(color);
                    case "genre" -> Movie.LENGTH_COMPARATOR.thenComparing(genre);
                    default -> collection.sort(length);
                }
            }
            case "genre" -> {
                switch (term2) {
                    case "title" -> Movie.GENRE_COMPARATOR.thenComparing(title);
                    case "director" -> Movie.GENRE_COMPARATOR.thenComparing(director);
                    case "release" -> Movie.GENRE_COMPARATOR.thenComparing(release);
                    case "color" -> Movie.GENRE_COMPARATOR.thenComparing(color);
                    case "length" -> Movie.GENRE_COMPARATOR.thenComparing(length);
                    default -> collection.sort(genre);
                }
            }
            default -> {
                switch (term2) {
                    case "director" -> Movie.TITLE_COMPARATOR.thenComparing(director);
                    case "release" -> Movie.TITLE_COMPARATOR.thenComparing(release);
                    case "color" -> Movie.TITLE_COMPARATOR.thenComparing(color);
                    case "length" -> Movie.TITLE_COMPARATOR.thenComparing(length);
                    case "genre" -> Movie.TITLE_COMPARATOR.thenComparing(genre);
                    default -> collection.sort(title);
                }

            }
        }

    }

    public String deleteFile() {
        File file = new File("save.txt");
        if (file.delete()) {
            return "You have deleted a file ";
        } else {
            return "You need a file, before you can delete.";
        }
    }
}