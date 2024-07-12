
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_TRIES = 10;
    private static final int TOTAL_ROUNDS = 3;

    public static void main(String[] args) {
        Random rng = new Random();
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("WELCOME TO THE NUMBER GUESSING GAME!");
        System.out.printf("Total Rounds: %d\n", TOTAL_ROUNDS);
        System.out.printf("Max Attempts Per Round: %d\n\n", MAX_TRIES);

        for (int round = 1; round <= TOTAL_ROUNDS; round++) {
            int targetNumber = rng.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            int attempts = 0;

            System.out.printf("Round %d: Guess a number between %d and %d.\n", round, LOWER_BOUND, UPPER_BOUND);

            while (attempts < MAX_TRIES) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    int score = MAX_TRIES - attempts;
                    totalScore += score;
                    System.out.printf("Correct! You guessed the number in %d attempts. Round Score: %d\n", attempts, score);
                    break;
                } else if (guess < targetNumber) {
                    System.out.printf("The number is higher than %d. Attempts left: %d.\n", guess, MAX_TRIES - attempts);
                } else {
                    System.out.printf("The number is lower than %d. Attempts left: %d.\n", guess, MAX_TRIES - attempts);
                }
            }

            if (attempts == MAX_TRIES) {
                System.out.printf("\nRound %d over. The correct number was %d.\n\n", round, targetNumber);
            }
        }

        System.out.printf("Game Over! Your Total Score: %d\n", totalScore);
        scanner.close();
    }
}
