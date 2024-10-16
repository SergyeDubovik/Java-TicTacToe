/*
 * Write a tic-tac-toe program where a human user
 * can play against an AI bot, and where two AI
 * bots can play against each other.  Write input
 * and output code so that it's intuitive for the
 * user.
 */

import java.io.*;

public class TicTacToe {

    public static Game game;
    public static int count;
    public static String userInput;

    private static int gameMode;
    private static boolean validInput;

    public static void main(String[] args) {

        int gameSize;
        int minimumGameSize = 2;
        int maximumGameSize = 26;

        //When the program starts, user is met with a welcome message
        System.out.println("\n\tWelcome to this wonderful and lovely game of TicTacToe.");
        System.out.println("\n\tPlease select your Game mode.");
        System.out.println("\n\t    (1) Human vs. Computer");
        System.out.println("\n\t    (2) Computer vs. Computer");
        userInput = getInput("\n\tWhich mode would you like to play? (1/2): ");

        //Keep asking for an answer from the user until we get a 1 or a 2
        gameMode(userInput); //gameMode() is defines below

        System.out.println("\n\tHow large of a grid would you like to use? ");
        userInput = getInput("\n\tPlease enter an integer between " + minimumGameSize + " and " + maximumGameSize + ": ");

        //validate user input for game size
        validInput = false;
        int parsedUserInput = 0;
        while (!validInput) {
            try {
                parsedUserInput = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                userInput = getInput("\n\tYou must enter a number between " + minimumGameSize + " and "
                        + maximumGameSize + ": ");
                continue;
            }


            if (minimumGameSize <= parsedUserInput && parsedUserInput <= maximumGameSize) {

                validInput = true;

            } else {

                userInput = getInput("\n\tYou must enter a number between " + minimumGameSize + " and "
                        + maximumGameSize + ": ");

            }
        }

        //issue warning for game sizes larger than 15
        if (parsedUserInput > 15) {

            System.out.println("""

                    \t!!WARNING!!
                    \t!!WARNING!!  Games large than 15 will not display correctly if console width is restricted to 80 col (neither will this message)
                    \t!!WARNING!!""");
            getInput("");

        }

        gameSize = parsedUserInput;

        //Create a new Game instance
        game = new Game(gameSize);

        //create an array of two players
        Player[] players = new Player[2];

        //set players to AI or Human depending on game mode
        if (gameMode == 1) {

            players[0] = new Player("Human");

        } else {

            players[0] = new Player("AI");

        }
        players[1] = new Player("AI");

        //Draw the blank board initially to show the user which columns and rows to choose from
        System.out.println(game.output());

        //until the game is over, go back and forth between players in player array
        //output the game map to the screen after each move
        while (!game.finished) {

            for (Player player : players) {

                player.go();
                System.out.println("\n" + game.output());
                count += 1;

                if (game.finished) {
                    break;
                }

            }
        }

        //output an ending message to the game
        if (game.draw) {

            System.out.println("\n\tCat's game!");

        } else {

            //count variable from earlier is used to decide who went last and therefore won.
            if (count % 2 == 1) {

                System.out.println("\n\tX's win!");

            } else {

                System.out.println("\n\tO's win!");

            }
        }
    }

    //encapsulated code for input stream buffer
    public static String getInput(String prompt) {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); // stdin = standard input

        System.out.print(prompt);
        System.out.flush();

        try {

            return stdin.readLine();

        } catch (Exception e) {

            return "Error: " + e.getMessage();

        }
    }

    //validates user input and sets the game mode
    private static void gameMode(String userInput) {

        validInput = false;

        while (!validInput) {

            if ((userInput.length() == 1) && (userInput.matches("[1-2]"))) {

                validInput = true;

            } else {

                userInput = getInput("\n\tYou must enter '1' or '2' for the game mode: ");

            }
        }

        //Set user input to gameMode for use later
        gameMode = Integer.parseInt(userInput);

    }
}