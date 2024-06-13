/**
 * CleverPlayer is a player that tries to win the game by playing the first empty cell in a row, column or
 * diagonal. If it can't win, it plays randomly.
 * It implements the Player interface.
 *
 * @author Amir Rosengarten
 */
public class CleverPlayer implements Player {
    /**
     * Constructs a new CleverPlayer.
     */
    public CleverPlayer() {};

    /**
     * Plays a turn on the given board. If it can win, it plays the first empty cell in a row, column or
     * diagonal. If it can't win, it plays randomly.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (!playFirstEmptyInRow(board, mark)) {
            if (!playFirstEmptyInCol(board, mark)) {
                if (!playFirstEmpyInDiag(board, mark)) {
                    PlayerFactory factory = new PlayerFactory();
                    Player whatever = factory.buildPlayer("whatever");
                    whatever.playTurn(board, mark);
                }
            }
        }
    }

    /**
     * Plays the first empty cell in a row.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     * @return true if the player played, false otherwise.
     */
    private boolean playFirstEmptyInRow(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getMark(i, 0) == Mark.BLANK) {
                board.putMark(mark, i, 0);
                return true;
            }
        }
        return false;
    }

    /**
     * Plays the first empty cell in a column.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     * @return true if the player played, false otherwise.
     */
    private boolean playFirstEmptyInCol(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getMark(0, i) == Mark.BLANK) {
                board.putMark(mark, 0, i);
                return true;
            }
        }
        return false;
    }

    /**
     * Plays the first empty cell in a diagonal.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     * @return true if the player played, false otherwise.
     */
    private boolean playFirstEmpyInDiag(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getMark(i, i) == Mark.BLANK) {
                board.putMark(mark, i, i);
                return true;
            }
        }
        return false;
    }
}