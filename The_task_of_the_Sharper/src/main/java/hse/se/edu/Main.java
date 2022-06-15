package hse.se.edu;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    /**
     * The method of checking that the user entered a number.
     *
     * @param value - input value.
     * @return - integer if it is number, -1 if it is no.
     */
    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * The method of checking that the entered number is non-negative and
     * if the number of honest players is entered that their number is positive.
     *
     * @param inputString     - input string.
     * @param in              - Scanner for input.
     * @param isHonestPlayers - true if it is number of honest players.
     * @return string.
     */
    public static String checkString(String inputString, Scanner in, boolean isHonestPlayers) {
        while (tryParseInt(inputString) == -1 || tryParseInt(inputString) < 0||tryParseInt(inputString)>100) {
            errrorMessage();
            inputString = in.next();
        }
        while (isHonestPlayers == true && tryParseInt(inputString) <= 0||tryParseInt(inputString)>100) {
            errorMessageHonestPlayers();
            inputString = in.next();
        }
        return inputString;
    }

    /**
     * Error message if it is not a number.
     */
    public static void errrorMessage() {
        System.out.println("Invalid input format!You have to enter a positive(maximum - 100) number or 0 if it is number of schulers!\n" +
                "Try to enter again...");
    }

    /**
     * Error message if it is not a positive number if it is number of schulers.
     */
    public static void errorMessageHonestPlayers() {
        System.out.println("The minimum number of honest players is 1.Maximum - 100.Try to enter again...");
    }

    /**
     * The entry point to the program.
     *
     * @param argz - command line elements.
     */
    public static void main(String[] argz) {
        int numOfPlayers;
        int numOfSchulers;
        String inputString;
        System.out.println("Enter count of honest players(maximum - 100):");
        Scanner in = new Scanner(System.in);
        inputString = in.next();
        inputString = checkString(inputString, in, true);
        numOfPlayers = Integer.parseInt(inputString);
        System.out.println("Enter count of shulers(maximum - 100):");
        inputString = in.next();
        inputString = checkString(inputString, in, false);
        numOfSchulers = Integer.parseInt(inputString);
        Bank bank = new Bank();
        Vector<Thread> threads = new Vector<Thread>();
        System.out.println("Please wait, the players are playing...");
        for (int i = 0; i < numOfPlayers; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
            threads.add(new Thread(player));
        }
        for (int i = 0; i < numOfSchulers; i++) {
            Schuler schuler = bank.createSchuler();
            schuler.setId(i);
            threads.add(new Thread(schuler));
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.elementAt(i).start();
        }
        try {
            for (int i = 0; i < threads.size(); i++) {
                threads.elementAt(i).join();
            }
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
            return;
        }
        System.out.println("Scores:");
        bank.printScores();
    }
}
