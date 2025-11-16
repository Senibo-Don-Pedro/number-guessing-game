
import java.util.Random;
import java.util.Scanner;

public class App {
    // You can put your Scanner and Random here to share them
    // Make them 'static' so your 'static' methods can use them
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) throws Exception {

        boolean wantsToPlay = true;

        while (wantsToPlay) {
            runGame(); // <-- Plays one full game
            wantsToPlay = askToPlayAgain(); // <-- Asks the user if they want to go again
        }

        System.out.println("Thanks for playing!");
        scanner.close();

    }

    // A NEW helper method!
    public static boolean askToPlayAgain() {
        System.out.print("Do you want to play another round? (y/n): ");
        String choice = scanner.next(); // Use .next() for a single word
        return choice.equalsIgnoreCase("y"); // Returns 'true' if they type 'y' or 'Y'
    }

    // This method contains ALL the logic for one round of the game
    public static void runGame() {
        // We moved the number generation here, so you get a NEW number
        // every time you play a new game.
        int numberToGuess = random.nextInt(100) + 1;

        String story = """
                Welcome to the number guessing game!
                I'm thinking of a number between 1 and 100.

                Please select the difficulty level:
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)
                """;

        System.out.println(story);
        System.out.print("Enter your choice: ");

        int difficultyLevel = scanner.nextInt(); // <-- Uh oh, this can crash! (We'll fix this)
        int totalChances = numberOfChances(difficultyLevel);

        System.out.println("Let's start the game");

        int currentChance = 1;
        boolean hasWon = false;

        while (currentChance <= totalChances) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt(); // <-- This can also crash!

            if (guess == numberToGuess) {
                hasWon = true;
                break;
            } else if (guess < numberToGuess) {
                System.out.println(String.format("Incorrect! The number is greater than %d.", guess));
            } else {
                System.out.println(String.format("Incorrect! The number is less than %d.", guess));
            }
            currentChance += 1;
        }

        if (!hasWon) {
            System.out.println("Sorry, you ran out of chances! The correct number was " + numberToGuess + "!!!");
        } else {
            System.out.println("Congratulations! You guessed the correct number in " + currentChance + " attempts.");
        }
    }

    // This method now belongs to the class and can be called by main
    public static int numberOfChances(int difficultyLevel) {

        // A switch statement is very readable for checking a variable against
        // multiple specific values (like 1, 2, or 3).
        switch (difficultyLevel) {
            case 1:
                System.out.println("Great! You have selected the Easy difficulty level.");
                return 10;
            case 2:
                System.out.println("Great! You have selected the Medium difficulty level.");

                return 5;
            case 3:
                System.out.println("Great! You have selected the Hard difficulty level.");

                return 3;
            default:
                // It's good practice to handle bad input
                System.out.println("Invalid choice. Defaulting to Medium (5 chances).");
                return 5;
        }
    }
}
