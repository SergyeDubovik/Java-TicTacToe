
public class RandomComputerPlayer implements Player {
    private boolean turn;

    public void go() {
        turn = true;
        System.out.print("\tThe computer will now make a move..");
        delay(TicTacToe.game.gridSize); //take a second to go to make it appear as if computer is thinking

        while (turn) {
            //AI selects a random empty cell and places the corresponding mark
            int index = (int) Math.round((TicTacToe.game.gridSize * TicTacToe.game.gridSize - 1) * Math.random());
            move(index, TicTacToe.game);

        }
    }

    private void move(int index, Game game) {

        if (game.markCell(index)) {
            turn = false;
        }
    }

    //encapsulated code for AI delay behavior
    private static void delay(int gameSize) {

        try {

            Thread.sleep(1000 * 3L / ((long) gameSize * gameSize));

        } catch (InterruptedException ex) {

            Thread.currentThread().interrupt();
        }
    }


}

