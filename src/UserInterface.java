
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();


    public void userInterface() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "Welcome to your movie collection.\n"
                +"1. Create a movie.\n"+
                "2. Search movie.\n" +
                "3. List of the movies\n" +
                "4. Get a help list.\n" +
                "5. Exit");
        while (running) {
            System.out.print("\nChoose an option: ");
            String userInput = sc.nextLine().toLowerCase();
            String[] splitPut = userInput.split(" ");


            switch (userInput) {
                case "create","1" -> addMovieByUser();
                case "search", "s","2" -> searchForFilm();
                case "list","l","3" -> getMovieList();
                case "help", "h","4" -> helpList();
                case "exit","5" -> {System.out.println("Thank you for your time, hope to see you again."); return;}
                default -> System.out.println("Unknown request, please try again.");
            }
            System.out.println("Type \"help\", for a list of commands.");
        }
    }

    public void addMovieByUser() {
        Scanner sc = new Scanner(System.in);

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
                isInColor = sc.next();
                if (isInColor.equals("yes") || isInColor.equals("no")){
                    break;
                }
        }
        System.out.print("How long is the movie (in minutes): ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            sc.next();
        }
        int lengthInMinutes = sc.nextInt();


        System.out.print("In what genre type is the movie: ");
        String genre = sc.next();
        controller.addMovieToCollection(name,director,yearCreated,isInColor,lengthInMinutes,genre);
    }

    public void getMovieList(){
        if (controller.movies.movieList() == null){
            System.out.println("\nThe list is empty, please create a movie.\n");
        } else {
            System.out.println(controller.movies.movieList());
        }
    }

    public void helpList(){
        System.out.println(
                "Type [1, create] -> Create a movie.\n"+
                "Type [2, search, s] -> Search for a movie.\n" +
                "Type [3, list, l] -> List the movies.\n" +
                "Type [4, help, h] -> Get a help list.\n" +
                "Type [5, exit] -> Exit the application.\n");
    }

    public String getMovieDesc(Movie movieName) {
        return ("Title: "+movieName.getTitle()+"\nDirector: "+movieName.getDirector()+
                "\nRelease year: "+movieName.getYearCreated()+"\nIn color: "+movieName.getIsInColor()+
                "\nLength (in minutes): "+movieName.getLengthInMinutes()+"\nGenre: "+movieName.getGenre()+"\n");
    }

    public void searchForFilm() {
        System.out.print("insert search term: ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> found = controller.runSearch(sc.nextLine());
        if(found != null)
        {
            if(found.size() == 1)
            {
                editFilm(found.getFirst(), "placeholder" );
            }
            for (Movie movie : found) {
                getMovieDesc(movie);
            }
        }else
        {
            System.out.println("movie could not be found");
        }

    }

    public void editFilm(Movie film, String edit) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. name, 2. director, 3. year, 4. color, 5. length, 6. genre");
        String hi = sc.nextLine();
        switch (hi)
        {
            case "1":
                System.out.print("what should the new title be: ");
                System.out.println(controller.EditMovie(film, "title", sc.nextLine()));
                break;
            case "2":
                System.out.print("who should the new director be: ");
                System.out.println(controller.EditMovie(film, "director", sc.nextLine()));
                break;
            case "3":
                System.out.print("what is the new release year: ");
                System.out.println(controller.EditMovie(film, "year", sc.nextLine()));
                break;
            case "4":
                while(true)
                {
                    System.out.print("is it in color: ");
                    hi = sc.nextLine();
                    if (hi.equals("yes") || hi.equals("no"))
                    {
                        System.out.println(controller.EditMovie(film, "color", hi));
                        break;
                    }
                }
                break;
            case "5" :
                System.out.print("how long is the movie now: ");
                System.out.println(controller.EditMovie(film, "length", sc.nextLine()));
                break;
            case "6":
                System.out.print("what is the new genre: ");
                System.out.println(controller.EditMovie(film, "genre", sc.nextLine()));
                break;
        }
    }
}