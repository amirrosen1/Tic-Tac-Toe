/**
 * This class represents a game of Tic Tac Toe. It is responsible for running the game and checking for a
 * winner. It also holds the board and the players.
 *
 * @author Amir Rosengarten
 */
public class Game {
    private static final int WIN_STREAK = 3;
    private Player playerX;
    private Player playerO;
    private Renderer renderer;
    private Board board;
    private int winStreak;

    /**
     * Constructs a new game with the given players and renderer.
     *
     * @param playerX  The player with mark X.
     * @param playerO  The player with mark O.
     * @param renderer The renderer to use.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.winStreak = WIN_STREAK;
        this.board = new Board();
    }

    /**
     * Constructs a new game with the given players, renderer, board size and win streak.
     *
     * @param playerX   The player with mark X.
     * @param playerO   The player with mark O.
     * @param size      The size of the board.
     * @param winStreak The win streak.
     * @param renderer  The renderer to use.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.board = new Board(size);
        if (winStreak > size || winStreak < 2) {
            this.winStreak = size;
        } else {
            this.winStreak = winStreak;
        }
    }

    /**
     * Returns the win streak.
     *
     * @return The win streak.
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Returns the board size.
     *
     * @return The board size.
     */
    public int getBoardSize() {
        return board.getSize();
    }

    /**
     * Checks if the board is full.
     *
     * @return True if the board is full, false otherwise.
     */
    private boolean boardIsFull() {
        int size = getBoardSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if there is a winner diagonally.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkDiagonal(Board board, int winStreak, Mark mark) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) == mark) {
                    if (checkDiagonalFromPoint(board, winStreak, mark, row, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winner diagonally from a given point.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @param row       The row of the point.
     * @param col       The column of the point.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkDiagonalFromPoint(Board board, int winStreak, Mark mark, int row, int col) {
        return checkDirection(board, winStreak, mark, row, col, 1, 1) || // Diagonal down-right
                checkDirection(board, winStreak, mark, row, col, 1, -1);  // Diagonal down-left
    }

    /**
     * Checks if there is a winner in a given direction.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @param startRow  The row of the starting point.
     * @param startCol  The column of the starting point.
     * @param rowInc    The row increment.
     * @param colInc    The column increment.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkDirection(Board board, int winStreak, Mark mark, int startRow, int startCol,
                                   int rowInc, int colInc) {
        int count = 0;
        for (int i = 0; i < winStreak; i++) {
            int newRow = startRow + i * rowInc;
            int newCol = startCol + i * colInc;
            if (newRow >= 0 && newRow < board.getSize() && newCol >= 0 && newCol < board.getSize() &&
                    board.getMark(newRow, newCol) == mark) {
                count++;
                if (count == winStreak) return true;
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * Checks if there is a winner vertically.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkVertical(Board board, int winStreak, Mark mark) {
        int size = getBoardSize();
        for (int col = 0; col < size; col++) {
            int count = 0;
            for (int row = 0; row < size; row++) {
                if (board.getMark(row, col) == mark) {
                    count++;
                    if (count == winStreak) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winner horizontally.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkHorizontal(Board board, int winStreak, Mark mark) {
        int size = getBoardSize();
        for (int row = 0; row < size; row++) {
            int count = 0;
            for (int col = 0; col < size; col++) {
                if (board.getMark(row, col) == mark) {
                    count++;
                    if (count == winStreak) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winner.
     *
     * @param board     The board to check.
     * @param winStreak The win streak.
     * @param mark      The mark to check.
     * @return The mark of the winner, or Mark.BLANK if there is no winner.
     */
    private Mark checkWinner(Board board, int winStreak, Mark mark) {
        if (checkHorizontal(board, winStreak, mark) || checkVertical(board, winStreak, mark) ||
                checkDiagonal(board, winStreak, mark)) {
            return mark;
        }
        return Mark.BLANK;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    private boolean isGameOver() {
        return boardIsFull() || checkWinner(board, winStreak, Mark.X) != Mark.BLANK ||
                checkWinner(board, winStreak, Mark.O) != Mark.BLANK;
    }

    /**
     * Runs the game.
     *
     * @return The mark of the winner.
     */
    public Mark run() {
        Mark winner = Mark.BLANK;
        Mark currentPlayer = Mark.X;
        while (winner == Mark.BLANK && !isGameOver()) {
            if (currentPlayer == Mark.X) {
                playerX.playTurn(board, currentPlayer);
            } else {
                playerO.playTurn(board, currentPlayer);
            }
            renderer.renderBoard(board);
            winner = checkWinner(board, winStreak, currentPlayer);
            if (winner == Mark.BLANK) {
                currentPlayer = (currentPlayer == Mark.X) ? Mark.O : Mark.X;
            }
        }
        renderer.renderBoard(board);
        return winner;
    }
}