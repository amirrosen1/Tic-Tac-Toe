/**
 * This class represents a player that plays in a genius way. The player plays in the second empty cell in
 * a row or a column. If it can't win, it plays like a clever player.
 * It implements the Player interface.
 *
 * @author Amir Rosengarten
 */
public class GeniusPlayer implements Player {
    /**
     * Constructs a new GeniusPlayer.
     */
    public GeniusPlayer() {};

    /**
     * Plays a turn on the given board. If it can win, it plays the second empty cell in a row or column.
     * If it can't win, it plays like a clever player.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (!playSecondEmpyInCol(board, mark)) {
            if (!playSecondEmpyInRow(board, mark)) {
                PlayerFactory factory = new PlayerFactory();
                Player clever = factory.buildPlayer("clever");
                clever.playTurn(board, mark);
            }
        }
    }

    /**
     * Plays the second empty cell in a row.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     * @return true if the player played, false otherwise.
     */
    private boolean playSecondEmpyInRow(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getMark(i, 1) == Mark.BLANK) {
                board.putMark(mark, i, 1);
                return true;
            }
        }
        return false;
    }

    /**
     * Plays the second empty cell in a column.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     * @return true if the player played, false otherwise.
     */
    private boolean playSecondEmpyInCol(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getMark(1, i) == Mark.BLANK) {
                board.putMark(mark, 1, i);
                return true;
            }
        }
        return false;
    }
}