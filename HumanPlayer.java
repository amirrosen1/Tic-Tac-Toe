/**
 * HumanPlayer class. This class implements the Player interface. It represents a human player.
 *
 * @author Amir Rosengarten
 */
public class HumanPlayer implements Player {

    /**
     * Constructs a new HumanPlayer.
     */
    public HumanPlayer() {};

    /**
     * Plays a turn on the given board.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        requestPlayerInput(mark);
        int row, col, coordinate;
        coordinate = KeyboardInput.readInt();
        row = coordinate / 10;
        col = coordinate % 10;
        while (!board.putMark(mark, row, col)) {
            if (board.getMark(row, col) != Mark.BLANK) {
                System.out.println(Constants.OCCUPIED_COORDINATE);
            } else {
                System.out.println(Constants.INVALID_COORDINATE);
            }
            coordinate = KeyboardInput.readInt();
            row = coordinate / 10;
            col = coordinate % 10;
        }
    }

    /**
     * Requests input from the player based on their mark.
     *
     * @param mark The mark of the player.
     */
    private void requestPlayerInput(Mark mark) {
        if (mark == Mark.X) {
            System.out.println(Constants.playerRequestInputString("X"));
        } else if (mark == Mark.O) {
            System.out.println(Constants.playerRequestInputString("O"));
        }
    }
}