/**
 * Interface for a player. This interface represents a particular logic or strategy for taking a turn in
 * a game given a particular board.
 *
 * @author Amir Rosengarten
 */
public interface Player {

    /**
     * Plays a turn on the given board.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     */
    void playTurn(Board board, Mark mark);
}