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
                case 1 ->
            }

        }

    }
}
