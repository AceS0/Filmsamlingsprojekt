import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();

    public void userInterface() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "Welcome to your movie collection.\n"
                +"1. Create a movie.\n"+
                "2. Exit.\n" +
                "3. List of the movies\n" +
                "4. Get a help list.\n" +
                "5. Search movie");
        while (running) {
            System.out.print("\nChoose an option: ");
            String userInput = sc.nextLine().toLowerCase();
            switch (userInput) {
                case "create","1" -> addMovieByUser();
                case "exit","2" -> {System.out.println("Thank you for your time, hope to see you again.");
                    return;}
                case "list","l","3" -> getMovieList();
                case "help", "h","4" -> helpList();
                case "search", "s","5" -> searchForFilm();
                default -> System.out.println("Unknown request, please try again.");
            }
            System.out.println("Type \"help\", \"h\", \"4\"");
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
        System.out.println(controller.movies.movieList());

    }

    public void helpList(){
        System.out.println(
                "Type [1, create] -> Create a movie.\n"+
                "Type [2, exit] -> Exit the application.\n" +
                "Type [3, list, l] -> List the movies.\n" +
                "Type [4, help, h] -> Get a help list.\n");
    }

    public String getMovieDesc(Movie movieName) {
        return ("Title: "+movieName.getTitle()+"\nDirector: "+movieName.getDirector()+
                "\nRelease year: "+movieName.getYearCreated()+"\nIn color: "+movieName.getIsInColor()+
                "\nLength (in minutes): "+movieName.getLengthInMinutes()+"\nGenre: "+movieName.getGenre()+"\n");
    }

    public void searchForFilm()
    {
        System.out.print("insert search term: ");
        Scanner sc = new Scanner(System.in);
        Movie found = controller.runSearch(sc.nextLine());
        if(found != null)
        {
            System.out.println(getMovieDesc(found) + "\n\nDo you wish to edit this movie?" );
            if (sc.nextLine().toLowerCase().contains("y"))
            {
                editFilm(found);
            }

        }else
        {
            System.out.println("movie could not be found");
        }

    }

    public void editFilm(Movie film)
    {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("1. name, 2. director, 3. year, 4. color, 5. length, 6. genre");
        switch (sc.nextLine())
        {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5" :
                break;
            case "6":
                break;
        }
    }
}