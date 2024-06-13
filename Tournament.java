/**
 * This class is responsible for running a tournament between two players.
 *
 * @author Amir Rosengarten
 */
public class Tournament {
    private final int rounds;
    private final Renderer renderer;
    private final Player player1;
    private final Player player2;

    /**
     * Constructs a new tournament with the given parameters.
     *
     * @param rounds   The number of rounds to play.
     * @param renderer The renderer to use.
     * @param player1  The first player.
     * @param player2  The second player.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Runs the tournament.
     *
     * @param size        The size of the board.
     * @param winStreak   The win streak.
     * @param playerName1 The name of the first player.
     * @param playerName2 The name of the second player.
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        int player1Wins = 0;
        int player2Wins = 0;
        int draws = 0;

        for (int i = 0; i < this.rounds; i++) {
            Game game = initializeGame(i, size, winStreak);
            Mark winner = game.run();

            // Update scores directly in the loop
            if ((winner == Mark.X && i % 2 == 0) || (winner == Mark.O && i % 2 == 1)) {
                player1Wins++;
            } else if ((winner == Mark.X && i % 2 == 1) || (winner == Mark.O && i % 2 == 0)) {
                player2Wins++;
            } else {
                draws++;
            }
        }
        printResults(playerName1, playerName2, player1Wins, player2Wins, draws);
    }

    /**
     * Initializes a game with alternating players.
     *
     * @param round     The current round.
     * @param size      The size of the board.
     * @param winStreak The win streak.
     * @return The initialized game.
     */
    private Game initializeGame(int round, int size, int winStreak) {
        // Initialize game with alternating players
        if (round % 2 == 0) {
            return new Game(player1, player2, size, winStreak, renderer);
        } else {
            return new Game(player2, player1, size, winStreak, renderer);
        }
    }

    /**
     * Prints the results of the tournament.
     *
     * @param playerName1 The name of the first player.
     * @param playerName2 The name of the second player.
     * @param player1Wins The number of wins of the first player.
     * @param player2Wins The number of wins of the second player.
     * @param draws       The number of draws.
     */
    private void printResults(String playerName1, String playerName2, int player1Wins, int player2Wins,
                              int draws) {
        System.out.println("######### Results #########");
        System.out.printf("Player 1, %s won: %d rounds%n", playerName1, player1Wins);
        System.out.printf("Player 2, %s won: %d rounds%n", playerName2, player2Wins);
        System.out.printf("Ties: %d%n", draws);
    }

    /**
     * Creates a renderer of the given type.
     *
     * @param rendererType The type of the renderer.
     * @param size         The size of the board.
     * @return The created renderer.
     */
    private static Renderer createRenderer(String rendererType, int size) {
        RendererFactory rendererFactory = new RendererFactory();
        return rendererFactory.buildRenderer(rendererType, size);
    }

    /**
     * Creates a player of the given type.
     *
     * @param playerType The type of the player.
     * @return The created player.
     */
    private static Player createPlayer(String playerType) {
        PlayerFactory playerFactory = new PlayerFactory();
        return playerFactory.buildPlayer(playerType);
    }

    /**
     * Runs a tournament between two players.
     *
     * @param rounds      The number of rounds to play.
     * @param size        The size of the board.
     * @param winStreak   The win streak.
     * @param renderer    The renderer to use.
     * @param player1     The first player.
     * @param player2     The second player.
     * @param player1Name The name of the first player.
     * @param player2Name The name of the second player.
     */
    private static void runTournament(int rounds, int size, int winStreak, Renderer renderer, Player player1,
                                      Player player2, String player1Name, String player2Name) {
        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        tournament.playTournament(size, winStreak, player1Name, player2Name);
    }

    /**
     * Runs a tournament between two players.
     *
     * @param args The arguments to the program.
     */
    public static void main(String[] args) {
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        String rendererType = args[3].toLowerCase();
        String player1Type = args[4].toLowerCase();
        String player2Type = args[5].toLowerCase();
        Renderer renderer = createRenderer(rendererType, size);
        if (renderer == null) {
            System.out.println(Constants.UNKNOWN_RENDERER_NAME);
            return;
        }
        Player player1 = createPlayer(player1Type);
        Player player2 = createPlayer(player2Type);
        if (player1 == null || player2 == null) {
            System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            return;
        }
        runTournament(rounds, size, winStreak, renderer, player1, player2, player1Type, player2Type);
    }
}