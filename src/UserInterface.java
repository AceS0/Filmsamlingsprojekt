import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();

    public void userInterface() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to your movie collection.\n\n"+
                "1. Create a movie.\n"+
                "2. Exit.");
        System.out.print("Choose an option: ");
        while (running) {
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1 -> addMovieByUser();
                case 2 -> {
                    System.out.println("Thank you for your time, hope to see you again.");
                    return;
                }
                default -> System.out.println("Unknown request, please try again.");
            }
        }
    }

    public void addMovieByUser()
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("What's the name of the movie: ");
        String navn = sc.nextLine();
        System.out.print("Whose the director: ");
        String direktør = sc.nextLine();
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
        String genre = sc.nextLine();
        controller.addMovieToCollection(navn,direktør,yearCreated,isInColor,lengthInMinutes,genre);

        userInterface();

    }
}