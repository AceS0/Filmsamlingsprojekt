import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();
    public UserInterface() {
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
            }

        }

    }
    public void addMovieByUser()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("skriv navn");
        String navn = sc.nextLine();
        System.out.println("skriv direktør");
        String direktør = sc.nextLine();
        System.out.println("skriv udgivelses år");
        int yearCreated = sc.nextInt();

        controller.addMovieToCollection(navn,direktør,yearCreated,isInColor,lengthInMinutes,genre);



    }
}
