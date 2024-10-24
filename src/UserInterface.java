import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();

    public void userInterface() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to your movie collection.\n\n"+
                "1. Create a movie.\n"+
                "2. Exit.\n" +
                "3. List of the movies\n" +
                "4. Get a help list.");
        System.out.print("Choose an option: ");
        while (running) {
            String userInput = sc.nextLine().toLowerCase();
            switch (userInput) {
                case "create","1" -> addMovieByUser();
                case "exit","2" -> {System.out.println("Thank you for your time, hope to see you again."); return;}
                case "list","l","3" -> getMovieList();
                case "help", "h","4" -> helpList();
                default -> System.out.println("Unknown request, please try again.");
            }
        }
    }

    public void addMovieByUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("What's the name of the movie: ");
        String name = sc.nextLine();
        System.out.print("Whose the director: ");
        String director = sc.nextLine();
        System.out.print("Which year was the movie released: ");
        int yearCreated = sc.nextInt();
        System.out.println("Is the movie in color? (Yes/No)");
        String isInColor = sc.next().toLowerCase();
        while(!isInColor.equals("yes") && !isInColor.equals("no")) {
                System.out.println("Unknown request, please try again. \"Hint (Yes / No)\"");
                isInColor = sc.next();
                if (isInColor.equals("yes") || isInColor.equals("no")){
                    break;
                }
        }
        System.out.print("How long is the movie (in minutes): ");
        int lengthInMinutes = sc.nextInt();
        System.out.print("In what genre type is the movie: ");
        String genre = sc.next();
        controller.addMovieToCollection(name,director,yearCreated,isInColor,lengthInMinutes,genre);
    }
    public void getMovieList(){
        controller.movies.movieList();
    }

    public void helpList(){
        System.out.println(
                "Type [1, create] -> Create a movie.\n"+
                "Type [2, exit] -> Exit the application.\n" +
                "Type [3, list, l] -> List the movies.\n" +
                "Type [4, help, h] -> Get a help list.");
    }

    public String getMovieDesc(Movie movieName) {
        return ("Title: "+movieName.getTitle()+"\nDirector: "+movieName.getDirector()+
                "\nRelease year: "+movieName.getYearCreated()+"\nIn color: "+movieName.getIsInColor()+
                "\nLength (in minutes): "+movieName.getLengthInMinutes()+"\nGenre: "+movieName.getGenre()+"\n");
    }
}