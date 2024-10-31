
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
                "2. Remove a movie.\n"+
                "3. Search movie.\n" +
                "4. List of the movies\n" +
                "5. Get a help list.\n" +
                "6. Edit a movie\n" +
                "7. Exit");

        while (running) {
            System.out.print("Type \"help\", for a list of commands." +
                    "\nChoose an option: ");
            //Dette splitter brugerens input, som vi gør brug af i bla seacrh funktionen:
            String userInput = sc.nextLine().toLowerCase();
            System.out.println();
            String[] splitPut = userInput.split(" ");
            String command = splitPut[0];

            //Switch på forskellige commands brugeren kan vælge
            switch (command) {
                case "create","c", "1" -> addMovieByUser();
                case "remove", "r", "2" -> removeMovieByUser();
                case "search", "s","3" -> {
                    if (splitPut.length > 1){
                    searchForFilm(splitPut[1]);
                     } else {
                        System.out.print("insert search term: ");
                        searchForFilm(sc.nextLine());
                    }
                }
                case "list","l","4" -> getMovieList();
                case "help", "h","5" -> helpList();
                case "edit", "6" -> {
                    if (splitPut.length > 1){
                        editMovie(splitPut[1]);
                    } else {
                        System.out.print("Insert the movie you want to edit: ");
                        editMovie(sc.nextLine());
                    }
                }
                case "exit","7" -> {
                    System.out.println("Thank you for your time, hope to see you again.");
                    running = false;}
                default -> System.out.println("Unknown request, please try again.");
            }

        }
    }
    //Metode til at tilføje en film:
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
        //Tilføjer filmen til MovieCollection:
        controller.addMovieToCollection(name,director,yearCreated,isInColor,lengthInMinutes,genre);
    }

    //Metode til at få listen på film
    public void getMovieList(){
        if (Objects.equals(controller.getMovies().movieList(), "")){
            System.out.println("\nThe list is empty, please create a movie.\n");
        } else {
            System.out.println(controller.getMovies().movieList());
        }
    }
//metode til at fjerne en film
    public void removeMovieByUser(){
        Scanner sc = new Scanner(System.in);
        //print liste af film til brugeren
        System.out.println(controller.getMovies().movieList());
        System.out.println("Which movie do you want to remove?");
        System.out.print("Type here: ");

       while (true){
           try{
               int input = sc.nextInt();
               controller.removeMovieFromCollection(input-1);
           }catch (Exception e){
               System.out.println("Invalid input, try again:(");
               removeMovieByUser();
           }
       }
   }

    //Metode til Hjælpeguide.
    public void helpList(){
        System.out.println(
                "Type [1, create] -> Create a movie.\n"+
                "Type [2, remove, r] -> Remove a movie\n"+
                "Type [3, search, s] -> Search for a movie.\n" +
                "Type [4, list, l] -> List the movies.\n" +
                "Type [5, help, h] -> Get a help list.\n" +
                "Type [6, edit] -> Edit a movie.\n"+
                "Type [7, exit] -> Exit the application.\n");
    }

    //Metode til at få beskrivelse på filmen.
    public String getMovieDesc(Movie movieName) {
        return ("\nTitle: "+movieName.getTitle()+"\nDirector: "+movieName.getDirector()+
                "\nRelease year: "+movieName.getYearCreated()+"\nIn color: "+movieName.getIsInColor()+
                "\nLength (in minutes): "+movieName.getLengthInMinutes()+"\nGenre: "+movieName.getGenre()+"\n");
    }

    //Metode til at søge efter en film
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

    /*1. metode til at redigere en film:
    Forklaring: Hvis brugeren skriver [edit, 5], så vil programmet spørge ind til det man vil edit.
    */
    public void editMovie(String film){
        try {
            ArrayList<Movie> found = controller.runSearch(film);
            Scanner sc = new Scanner(System.in);


            System.out.println("Do you want to edit '" + found.getFirst().getTitle()+"'? (yes/no)");
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
    /*2.. metode til at redigere en film:
    Forklaring: Modsat 1. metode i edit kan man her direkte skrive "edit (filmnavn)"
     */
    public void editFilm(Movie film, String edit) {
        System.out.println(getMovieDesc(film));
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("0. exit, 1. name, 2. director, 3. year, 4. color, 5. length, 6. genre");
        switch (sc.next()) {
            case "1", "name":
                System.out.print("What should the new title be: ");
                System.out.println(controller.EditMovie(film, "title", sc.nextLine()));
                break;
            case "2", "director":
                System.out.print("Who should the new director be: ");
                input = sc.next();
                if (input.matches(".*\\d.*")) {
                    System.out.print("Invalid input, please try again: ");
                    break;
                } else {
                    System.out.println(controller.EditMovie(film, "director", sc.next()));
                } break;
            case "3", "year":
                System.out.print("What is the new release year: ");
                if (sc.hasNextInt()) {
                    System.out.println("The value has now been changed to: " + controller.EditMovie(film, "year", String.valueOf(sc.nextInt())));
                    break;
                } break;
            case "4", "color":
                System.out.print("Is it in color: ");
                input = sc.next();
                if (input.matches(".*\\d.*")) {
                    System.out.print("Invalid input.");
                } else if(input.equals("yes") || input.equals("no")) {
                    System.out.println(controller.EditMovie(film, "color", input));
                    break;
                } break;
            case "5","length" :
                System.out.print("How long is the movie now: ");
                if (sc.hasNextInt()) {
                    System.out.println("The value has now been changed to: " + controller.EditMovie(film, "length", String.valueOf(sc.nextInt())));
                    break;
                } else {
                    System.out.println("Invalid input. Enter only numbers!");
                } break;
            case "6","genre":
                System.out.print("What is the new genre: ");
                input = sc.next();
                if (input.matches(".*\\d.*")) {
                    System.out.print("Invalid input.");
                } else {
                    System.out.println("The value has now been changed to: " + controller.EditMovie(film, "genre", input));
                    break;
                } break;
            case "7", "exit":
                System.out.println("-> Returning back to menu.");
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}