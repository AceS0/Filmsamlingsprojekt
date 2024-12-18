package ui;

import Controller.Controller;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInterface {
    private final Controller controller = new Controller();


    public void userInterface() {
        controller.runLoad();
        controller.getMovies().movieList();
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                """
                        Welcome to your movie collection.
                        Below is your options:\s
                        1. Create a movie.
                        2. Remove a movie.
                        3. Search movie.
                        4. List the movies.
                        5. Get a help list.
                        6. Edit a movie.
                        7. Save to a file.
                        8. Load file.
                        9. Delete a file.
                        10. Exit""");

        while (running) {
            try {
                System.out.print("""
                        
                        Type "help", for a list of commands.\
                        
                        Choose an option:\s""");
                //Dette splitter brugerens input, som vi gør brug af i bla search funktionen:
                String userInput = br.readLine().toLowerCase();
                String[] splitPut = userInput.split(" ");
                String command = splitPut[0];

                //Switch på forskellige commands brugeren kan vælge
                switch (command) {
                    case "create", "c", "1" -> addMovieByUser();
                    case "remove", "r", "2" -> {
                        if (splitPut.length > 1) {
                            removeMovieByUser(splitPut[1]);
                        } else {
                            System.out.println("Here is a list of movies: ");
                            getMovieListShort();
                            System.out.println("Insert the movie you want to remove.");
                            System.out.print("Type here: ");
                            removeMovieByUser(sc.next());
                        }
                    }
                    case "search", "s", "3" -> {
                        if (splitPut.length > 1) {
                            searchForFilm(splitPut[1]);
                        } else {
                            System.out.print("insert search term: ");
                            searchForFilm(sc.next());
                        }
                    }
                    case "list", "l", "4" -> list();
                    case "help", "h", "5" -> helpList();
                    case "edit", "6" -> {
                        if (splitPut.length > 1) {
                            editMovie(splitPut[1]);
                        } else {
                            System.out.print("Insert the movie you want to edit: ");
                            editMovie(sc.next());
                        }
                    }
                    case "save", "7" -> saveMovie();
                    case "load", "8" -> loadMovie();
                    case "delete", "9" -> deleteFile();
                    case "exit", "10" -> {
                        System.out.println("Thank you for your time, hope to see you again.");
                        running = false;
                    }
                    default -> System.out.println("Unknown request, please try again.");
                }
            } catch (ArrayIndexOutOfBoundsException | IOException aioobe) {
                System.out.println("Unknown request, please try again.");
            }
        }
    }

    //Metode til at tilføje en film:
    public void addMovieByUser() {

        Scanner sc = new Scanner(System.in);
        System.out.println("You are creating a movie.");
        System.out.print("What's the name of the movie: ");
        String input = sc.nextLine();
        ArrayList<Movie> found = controller.runSearch(input);
        for (Movie ignored : found) {
            if (!found.isEmpty()) {
                System.out.println("The movie you're trying to create already exists, try adding to the name.");
                System.out.print("Type here: ");
                input = sc.nextLine();
            }
        }

        System.out.print("Who is the director: ");
        String director = sc.nextLine();
        System.out.print("Which year was the movie released: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            sc.next();
        }
        int yearCreated = sc.nextInt();
        System.out.print("Is the movie in color? (Yes/No): ");
        String isInColor = sc.next().toLowerCase();
        boolean isInColorBool = true;
        while (!isInColor.equals("yes") && !isInColor.equals("no")) {
            System.out.println("Unknown request, please try again. \"Hint (Yes / No)\"");
            System.out.print("Type your request here: ");
            isInColor = sc.next();
            if (isInColor.equals("yes") || isInColor.equals("no")) {
                if (isInColor.equals("no")) isInColorBool = false;
                break;
            }
        }
        System.out.print("How long is the movie (in minutes): ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input, please enter a valid number: ");
            sc.next();
        }
        int lengthInMinutes = sc.nextInt();
        System.out.print("In what genre type is the movie: ");
        while (sc.hasNextInt()) {
            System.out.print("Invalid input, please enter a genre-type: ");
            sc.next();
        }                                
        String genre = sc.next();
        //Tilføjer filmen til model.MovieCollection:
        controller.addMovieToCollection(input, director, yearCreated, isInColorBool, lengthInMinutes, genre);
    }

    public void removeMovieByUser(String inputs) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> found = controller.runSearch(inputs);
        if (found.isEmpty()) {
            System.out.println("\nThe movie doesn't exist, please create the movie if needed.\n");
            userInterface();
        } else {
            for (Movie movie : found) {
                if (found.size() == 1) {
                    System.out.println("You have successfully removed " + movie.getTitle() + "\n");
                    controller.removeMovieFromCollection(movie);
                    userInterface();
                }
            }
            while (true) {
                if (found.size() >= 2) {
                    System.out.println("\nHere is a list of movies: ");
                    StringBuilder toPrint = new StringBuilder();
                    int counter = 1;
                    for (Movie movie : found) {
                        toPrint.append("\nmodel.Movie ").append(counter++).append(": \nTitle: ").append(movie.getTitle());
                    }
                    System.out.println(toPrint);
                    System.out.println("Which movie do you want to remove?");
                    System.out.print("Type here: ");
                    inputs = sc.nextLine();
                    found = controller.runSearch(inputs);
                    for (Movie movie : found) {
                        if (found.size() == 1) {
                            System.out.println("You have successfully removed " + movie.getTitle());
                            controller.removeMovieFromCollection(movie);
                            return;
                        }
                    }
                    if (found.isEmpty()) {
                        System.out.println("The movie doesn't exist, please try again or to leave type \"exit\" or \"quit\".");
                        System.out.print("Type here: ");
                        String input = sc.nextLine();
                        if (input.equals("quit") || input.equals("exit")) {
                            return;
                        } else {
                            removeMovieByUser(input);
                        }
                    }
                }
            }
        }
    }

    //Metode til at få listen på film


    public void getMovieListShort() {
        if (Objects.equals(controller.getMovies().movieListShort(), "")) {
            System.out.println("\nThe list is empty, please create a movie.\n");
        } else {
            System.out.println(controller.getMovies().movieListShort());
        }
    }

    public void list(){
        if (Objects.equals(controller.getMovies().movieList(), "")) {
            System.out.println("\nThe list is empty, please create a movie.\n");
            userInterface();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("How do you want the list to be sorted?");
        System.out.println(
                """
                Sort by:
                1. "title"
                2. "director"
                3. "release"
                4. "color"
                5. "length"
                6. "genre"
                """);
        System.out.print("Type here: ");
        String input = sc.next();
        switch (input){
            case "2","director","d" ->{
                input = "director";
                controller.getMovies().movieList(input,"");
            }
            case "3","release","r" ->{
                input = "release";
                controller.getMovies().movieList(input,"");
            }
            case "4","color","c" ->{
                input = "color";
                controller.getMovies().movieList(input,"");
            }
            case "5","length","l" ->{
                input = "length";
                controller.getMovies().movieList(input,"");
            }
            case "6","genre","g" ->{
                input = "genre";
                controller.getMovies().movieList(input,"");
            }
            default -> {
                input = "title";
                controller.getMovies().movieList(input,"");
            }
        }
        System.out.println("Do you want to add a secondary sort? (yes or no)");
        System.out.print("Type here: ");
        while(true) {
            String input2 = sc.next();
            if (input2.equalsIgnoreCase("yes")) {
                System.out.println("How do you want the secondary attribute sorted?");
                System.out.println(
                        """
                        Sort by:
                        1. "title"
                        2. "director"
                        3. "release"
                        4. "color"
                        5. "length"
                        6. "genre"
                        """);
                System.out.print("Type here: ");
                String input3 = sc.next();
                switch (input3){
                    case "2","director","d" ->controller.getMovies().movieList(input,"director");
                    case "3","release","r" ->controller.getMovies().movieList(input,"release");
                    case "4","color","c" ->controller.getMovies().movieList(input,"color");
                    case "5","length","l" ->controller.getMovies().movieList(input,"length");
                    case "6","genre","g" ->controller.getMovies().movieList(input,"genre");
                    default -> controller.getMovies().movieList(input,"title");
                }
                System.out.println(controller.getMovies().movieList());
                break;
            } else if (input2.equalsIgnoreCase("no")) {
                System.out.println(controller.getMovies().movieList());
                break;
            } else {
                System.out.println("Invalid input, please try again.");
                System.out.print("Type here: ");
            }
        }
    }

    //Metode til Hjælpeguide.
    public void helpList() {
        System.out.println(
                """
                        Type [1, create] -> Create a movie.
                        Type [2, remove, r] -> Remove a movie
                        Type [3, search, s] -> Search for a movie.
                        Type [4, list, l] -> List the movies.
                        Type [5, help, h] -> Get a help list.
                        Type [6, edit] -> Edit a movie.
                        Type [7, savefile] -> Save movie in a file.txt.
                        Type [8, loadfile] -> Load the file.txt.
                        Type [9, delete] -> Delete the file.txt.
                        Type [10, exit] -> Exit the application.
                        """);
    }

    //Metode til at få beskrivelse på filmen.
    public String getMovieDesc(Movie movieName) {
        return ("\nTitle: " + movieName.getTitle() + "\nDirector: " + movieName.getDirector() +
                "\nRelease year: " + movieName.getYearCreated() + "\nIn color: " + movieName.getIsInColor() +
                "\nLength (in minutes): " + movieName.getLengthInMinutes() + "\nGenre: " + movieName.getGenre() + "\n");
    }

    //Metode til at søge efter en film
    public void searchForFilm(String film) {
        ArrayList<Movie> found = controller.runSearch(film);
        Scanner sc = new Scanner(System.in);
        if (found.isEmpty()) {
            System.out.println("The movie you searched for does not exist, please try again.");
        } else {

            if (found.size() == 1) {
                for (Movie movie : found) {
                    System.out.println(getMovieDesc(movie));
                }
                System.out.println("Do you want to edit " + found.getFirst().getTitle() + "? HINT \"Yes\" or \"No\"");
                System.out.print("Type here: ");
                String input = sc.next().toLowerCase();

                while (true) {
                    if (input.equals("yes") || input.equals("y")) {
                        editFilm(found.getFirst(), "placeholder");
                        return;
                    } else if (input.equals("no") || input.equals("n")) {
                        System.out.println("-> Returning back to menu.");
                        userInterface();
                    } else {
                        System.out.print("Couldn't interpret the input, please enter \"Yes\" or \"No\": ");
                        input = sc.next().toLowerCase();
                    }
                }

            } else {
                StringBuilder toPrint = new StringBuilder();
                int counter = 1;
                for (Movie movie : found) {
                    toPrint.append("\nmodel.Movie ").append(counter++).append(": \nTitle: ").append(movie.getTitle());
                }

                System.out.println(toPrint);
                System.out.println("Which movie do you want to get more details about?");
                System.out.print("Type here: ");
                String input = sc.nextLine();
                found = controller.runSearch(input);

                for (Movie ignored : found) {
                    if (!found.isEmpty()) {
                        searchForFilm(input);
                    }
                }

                if (found.isEmpty()) {
                    System.out.println("Couldn't reach the movie, please try again or to leave type \"exit\" or \"quit\". ");
                    System.out.print("Type here: ");
                    input = sc.nextLine();
                    if (input.equals("quit") || input.equals("exit")) {
                        userInterface();
                    } else {
                        searchForFilm(input);
                    }
                }
            }

        }
    }

    /*1. metode til at redigere en film:
    Forklaring: Hvis brugeren skriver [edit, 5], så vil programmet spørge ind til det man vil edit. */
    public void editMovie(String film) {
        try {
            ArrayList<Movie> found = controller.runSearch(film);
            Scanner sc = new Scanner(System.in);


            System.out.println("Do you want to edit '" + found.getFirst().getTitle() + "'? (yes/no)");
            String input = sc.next().toLowerCase();
            while (true) {
                if (input.equals("yes") || input.equals("y")) {
                    editFilm(found.getFirst(), "placeholder");
                    return;
                } else if (input.equals("no") || input.equals("n")) {
                    System.out.println("-> Returning back to menu.");
                    return;
                } else {
                    System.out.print("Couldn't interpret the input, please enter \"Yes\" or \"No\": ");
                    input = sc.next().toLowerCase();
                }
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("The movie was either not found or the movie collection is empty, please try again.");
        }
    }

    /*2. metode til at redigere en film:
    Forklaring: Modsat 1. metode i edit kan man her direkte skrive "edit (filmnavn)" */
    public void editFilm(Movie film, String edit) {
        boolean running = true;
        while (running) {
            System.out.println(getMovieDesc(film));
            Scanner sc = new Scanner(System.in);
            System.out.println("0. exit, 1. name, 2. director, 3. year, 4. color, 5. length, 6. genre");
            switch (sc.next()) {
                case "0", "exit":
                    System.out.println("-> Returning back to menu.");
                    running = false;
                    break;
                case "1", "name":
                    System.out.print("what should the new title be: ");
                    System.out.println(controller.EditMovie(film, "title", sc.next()));
                    break;
                case "2", "director":
                    System.out.print("who should the new director be: ");
                    System.out.println(controller.EditMovie(film, "director", sc.next()));
                    break;
                case "3", "year":
                    while (true) {
                        System.out.print("what is the new release year: ");
                        if (sc.hasNextInt()) {
                            System.out.println("The value has now been changed to: " + controller.EditMovie(film, "year", String.valueOf(sc.nextInt())));
                            break;
                        }
                    }
                    break;
                case "4", "color":
                    while (true) {
                        System.out.print("is it in color: ");
                        String input = sc.next();
                        if (input.equals("yes") || input.equals("no")) {
                            System.out.println(controller.EditMovie(film, "color", input));
                            break;
                        }
                    }
                    break;
                case "5", "length":
                    while (true) {
                        System.out.print("how long is the movie now: ");
                        if (sc.hasNextInt()) {
                            System.out.println("The value has now been changed to: " + controller.EditMovie(film, "length", String.valueOf(sc.nextInt())));
                            break;
                        }
                    }
                    break;
                case "6", "genre":
                    System.out.print("what is the new genre: ");
                    while (true) {
                        String input = sc.next();
                        if (input.matches(".*\\d.*")) {
                            System.out.print("Invalid input, please try again: ");
                        } else {
                            System.out.println("The value has now been changed to: " + controller.EditMovie(film, "genre", input));
                            break;
                        }
                    }
                    break;
                default:
                    System.out.print("Invalid input, please try again.");
                    break;
            }
        }
    }

    public void loadMovie(){
        System.out.println(controller.runLoad());
    }
    public void saveMovie(){
        System.out.println(controller.runSave());
    }
    public void deleteFile(){
        System.out.println(controller.runDeleteFile());
    }
}