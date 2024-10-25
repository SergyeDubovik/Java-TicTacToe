public class HumanPlayer implements Player {

    //player makes moves and can be human or AI

    private int index;
    private int column;
    private int row;
    private boolean turn; //whether it's the player's turn


    //player "goes" while it's their turn
    @Override
    public void go() {

        turn = true;
        System.out.println("\tPlease place an X on the grid.  You can");
        TicTacToe.userInput = TicTacToe.getInput("\tdo this by typing 1A, 1B, 1C, 2A, etc.: ");

        //while it's the player's turn...
        while (turn) {

            //validate user input
            if (validInput(TicTacToe.userInput)) {

                if (TicTacToe.userInput.length() == 2) {

                    column = Integer.parseInt(TicTacToe.userInput.substring(0, 1));
                    row = letterToNumber(TicTacToe.userInput.substring(1, 2));

                } else {

                    column = Integer.parseInt(TicTacToe.userInput.substring(0, 2));
                    row = letterToNumber(TicTacToe.userInput.substring(2, 3));

                }

                index = TicTacToe.game.gridSize * (row - 1) + (column - 1);

                if (index > (TicTacToe.game.gridSize * TicTacToe.game.gridSize) - 1 || index < 0) {

                    TicTacToe.userInput = TicTacToe.getInput("That's not a valid spot!  Please choose another spot: ");
                } else {

                    //if valid input, and cell isn't taken already,
                    //place mark in selected cell and end turn
                    move(index, TicTacToe.game);

                    if (turn) {

                        TicTacToe.userInput = TicTacToe.getInput("That space is already in play!  Please choose another spot: ");
                    }

                }

            } else {

                TicTacToe.userInput = TicTacToe.getInput("That's not valid input.  Please choose another spot: ");
            }
        }
    }

    //encapsulated code for user input validation
    //it checks to make sure the input was two or three characters long,
    //and that it contained one or two digits, followed by one lower
    //case or upper case letter
    private static boolean validInput(String userInput) {

        boolean output = false;

        if (userInput.length() == 2) {

            output = (userInput.substring(0, 1).matches("[0-9]") && userInput.substring(1, 2).matches("[a-zA-Z]"));

            if (Integer.parseInt(userInput.substring(0, 1)) > TicTacToe.game.gridSize) {
                output = false;
            }
        } else if (userInput.length() == 3) {

            output = (userInput.substring(0, 2).matches("[1-2][0-9]") && userInput.substring(2, 3).matches("[a-zA-Z]"));

            if (Integer.parseInt(userInput.substring(0, 2)) > TicTacToe.game.gridSize) {
                output = false;
            }
        }

        return output;
    }

    //player places mark
    private void move(int index, Game game) {

        if (game.markCell(index)) {
            turn = false;
        }
    }

    //converts the letter input for row/column selection into a usable number
    private static int letterToNumber(String str) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str) % 26 + 1;
    }
}