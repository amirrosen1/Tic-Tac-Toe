/**
 * Board class for TicTacToe game.
 * This class is responsible for the state of the board.
 *
 * @author Amir Rosengarten
 */
public class Board {
    private static final int SIZE = 4;
    private Mark[][] board;
    private int size;

    /**
     * Constructs a new board with the default size.
     */
    public Board() {

        this(SIZE);
    }

    /**
     * Constructs a new board with the given size.
     *
     * @param size The size of the board.
     */
    public Board(int size) {
        this.size = size;
        board = new Mark[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    /**
     * Returns the size of the board.
     *
     * @return The size of the board.
     */
    public int getSize() {

        return size;
    }

    /**
     * Puts a mark on the board.
     *
     * @param mark The mark to put on the board.
     * @param row  The row to put the mark in.
     * @param col  The column to put the mark in.
     * @return True if the mark was put successfully, false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if (board[row][col] != Mark.BLANK) {
            return false;
        }
        board[row][col] = mark;
        return true;
    }

    /**
     * Returns the mark in the given location. If the location is invalid, returns Mark.BLANK.
     *
     * @param row The row of the mark.
     * @param col The column of the mark.
     * @return The mark in the given location.
     */
    public Mark getMark(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return Mark.BLANK;
        }
        return board[row][col];
    }
}