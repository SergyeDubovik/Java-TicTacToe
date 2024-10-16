public class Cell {

    //This is one cell of the game grid

    public String content;
    public boolean empty;

    public Cell() {

        content = " ";
        empty = true;
    }

    public String output() {

        return content;
    }

    public void placeMark() {

        if (TicTacToe.count % 2 == 0) {
            content = "X";
        } else {
            content = "O";
        }

        empty = false;
    }
}