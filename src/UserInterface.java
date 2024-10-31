
import java.util.*;

public class UserInterface { 
    private Controller controller = new Controller();


    public void userInterface() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "Welcome to your movie collection.\n" +
                "Below is your options: \n" +
                "1. Create a movie.\n"+
                "2. Search movie.\n" +
                "3. List of the movies\n" +
                "4. Get a help list.\n" +
                "5. Edit a movie\n" +
                "6. Exit");
        while (running) {
            System.out.print("Type \"help\", for a list of commands." +
                    "\nChoose an option: ");
            String userInput = sc.nextLine().toLowerCase();
            String[] splitPut = userInput.split(" ");
            String command = splitPut[0];

            switch (command) {
                case "create","1" -> addMovieByUser();
                case "search", "s","2" -> {
                    if (splitPut.length > 1){
                    searchForFilm(splitPut[1]);
                     } else {
                        System.out.print("insert search term: ");
                        searchForFilm(sc.nextLine());
                    }
                }
                case "list","l","3" -> getMovieList();
                case "help", "h","4" -> helpList();
                case "edit", "5" -> {
                    if (splitPut.length > 1){
                        editMovie(splitPut[1]);
                    } else {
                        System.out.print("Insert the movie you want to edit: ");
                        editMovie(sc.nextLine());
                    }
                }
                case "exit","6" -> {
                    System.out.println("Thank you for your time, hope to see you again.");
                    running = false;}
                default -> System.out.println("Unknown request, please try again.");
            }

        }
    }

    public void addMovieByUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are creating a movie.");
        System.out.print("What's the name of the movie: ");
        String name = sc.nextLine();
        System.out.print("Who is the director: ");
        String director = sc.nextLine();
        System.out.print("Which year was the movie released: ");
        while (!sc.hasNextInt()){
            System.out.print("Invalid input. Please enter a valid number: ");
            sc.next();
        }
        int yearCreated = sc.nextInt();
        System.out.print("Is the movie in color? (Yes/No): ");
        String isInColor = sc.next().toLowerCase();
        while(!isInColor.equals("yes") && !isInColor.equals("no")) {
                System.out.println("Unknown request, please try again. \"Hint (Yes / No)\"");
                System.out.print("Type your request here: ");
                isInColor = sc.next();
                if (isInColor.equals("yes") || isInColor.equals("no")){
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
        controller.addMovieToCollection(name,director,yearCreated,isInColor,lengthInMinutes,genre);
    }

    public void getMovieList(){
        if (Objects.equals(controller.movies.movieList(), "")){
            System.out.println("\nThe list is empty, please create a movie.\n");
        } else {
            System.out.println(controller.getMovies().movieList());
        }
    }

    public void helpList(){
        System.out.println(
                    "Type [1, create] -> Create a movie.\n"+
                    "Type [2, search, s] -> Search for a movie.\n" +
                    "Type [3, list, l] -> List the movies.\n" +
                    "Type [4, help, h] -> Get a help list.\n" +
                    "Type [5, edit] -> Edit a movie.\n" +
                    "Type [6, exit] -> Exit the application.\n");
    }

    public String getMovieDesc(Movie movieName) {
        return ("\nTitle: "+movieName.getTitle()+"\nDirector: "+movieName.getDirector()+
                "\nRelease year: "+movieName.getYearCreated()+"\nIn color: "+movieName.getIsInColor()+
                "\nLength (in minutes): "+movieName.getLengthInMinutes()+"\nGenre: "+movieName.getGenre()+"\n");
    }
    public void searchForFilm(String film) {
        ArrayList<Movie> found = controller.runSearch(film);
        if(found != null)
        {
            if(found.size() == 1)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("do you want to edit " + found.getFirst().getTitle());
                String check = sc.nextLine();
                if(check == "yes") {
                    editFilm(found.getFirst(), "placeholder");
                }
            }
            for (Movie movie : found) {
                getMovieDesc(movie);
            }
        }else
        {
            System.out.println("movie could not be found");
        }

    }

    public void editMovie(String film){
        try {
            ArrayList<Movie> found = controller.runSearch(film);
            Scanner sc = new Scanner(System.in);


            System.out.println("Do you want to edit " + found.getFirst().getTitle());
            String input = sc.next().toLowerCase();
            while(true) {
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
        } catch (NoSuchElementException nsee ){
            System.out.println("The movie was either not found or the movie collection is empty, please try again.");
        }
    }

    public void editFilm(Movie film, String edit) {
        System.out.println(getMovieDesc(film));
        Scanner sc = new Scanner(System.in);
        System.out.println("0. exit, 1. name, 2. director, 3. year, 4. color, 5. length, 6. genre");
        switch (sc.next())
        {
            case "0","exit":
                System.out.println("-> Returning back to menu.");
                break;
            case "1","name":
                System.out.print("what should the new title be: ");
                System.out.println(controller.EditMovie(film, "title", sc.nextLine()));
            case "2","director":
                System.out.print("who should the new director be: ");
                System.out.println(controller.EditMovie(film, "director", sc.nextLine()));
            case "3","year":
                while(true)
                {
                    System.out.print("what is the new release year: ");
                    if (sc.hasNextInt())
                    {
                        System.out.println("The value has now been changed to: " + controller.EditMovie(film, "year", String.valueOf(sc.nextInt())));
                        break;
                    }
                }
            case "4","color":
                while(true)
                {
                    System.out.print("is it in color: ");
                    String input = sc.nextLine();
                    if (input.equals("yes") || input.equals("no"))
                    {
                        System.out.println(controller.EditMovie(film, "color", input));
                        break;
                    }
                }
            case "5","length" :
                while(true)
                {
                    System.out.print("how long is the movie now: ");
                    if (sc.hasNextInt())
                    {
                        System.out.println("The value has now been changed to: " + controller.EditMovie(film, "length", String.valueOf(sc.nextInt())));
                        break;
                    }
                }
            case "6","genre":
                System.out.print("what is the new genre: ");
                while(true)
                {
                        String input = sc.next();
                        if (input.matches(".*\\d.*")) {
                            System.out.print("Invalid input, please try again: ");
                        } else {
                            System.out.println("The value has now been changed to: " + controller.EditMovie(film, "genre", input));
                           break;
                        }
                }
            default:
                System.out.println("Invalid input, please try again.");
        }
    }
}