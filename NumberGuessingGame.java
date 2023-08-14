import java.util.Scanner;
import java.util.Random;

    public class NumberGuessingGame {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            int score = 0;
            int maxAttempts = 5; // Maximum number of attempts allowed per round

            System.out.println("Welcome to the Number Guessing Game!");

            do {
                int generatedNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100 (inclusive)
                int attempts = 0;
                int guess;

                System.out.println("New Round: Guess the number between 1 and 100!");

                while (attempts < maxAttempts) {
                    System.out.print("Enter your guess: ");
                    guess = scanner.nextInt();
                    attempts++;

                    if (guess == generatedNumber) {
                        System.out.println("Congratulations! Your guess is correct!");
                        score++;
                        break;
                    } else if (guess < generatedNumber) {
                        System.out.println("Your guess is too low.");
                    } else {
                        System.out.println("Your guess is too high.");
                    }

                    if (attempts < maxAttempts) {
                        System.out.println("Attempts left: " + (maxAttempts - attempts));
                    }
                }

                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you have used all your attempts. The number was: " + generatedNumber);
                }

                System.out.println("Your current score: " + score);

                System.out.print("Do you want to play again? (yes/no): ");
            } while (scanner.next().equalsIgnoreCase("yes"));

            System.out.println("Thank you for playing! Your final score: " + score);
            scanner.close();
        }
    }

