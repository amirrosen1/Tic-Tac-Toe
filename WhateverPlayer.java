import java.util.Random;

/**
 * A WhateverPlayer is a player that plays randomly. It implements the Player interface.
 *
 * @author Amir Rosengarten
 */
public class WhateverPlayer implements Player {
    private Random random = new Random();

    /**
     * Constructs a new WhateverPlayer.
     */
    public WhateverPlayer() {};

    /**
     * Plays a turn on the given board.
     *
     * @param board The board to play on.
     * @param mark  The mark to play.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int row, col;
        do {
            row = random.nextInt(board.getSize());
            col = random.nextInt(board.getSize());
        } while (board.getMark(row, col) != Mark.BLANK);
        board.putMark(mark, row, col);
    }
}