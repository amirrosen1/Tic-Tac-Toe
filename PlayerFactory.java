/**
 * PlayerFactory class is used to create a player object based on the type of player requested.
 *
 * @author Amir Rosengarten
 */
public class PlayerFactory {
    /**
     * Constructs a new PlayerFactory.
     */
    public PlayerFactory() {};

    /**
     * Builds a player based on the given type.
     *
     * @param type The type of player to build.
     * @return The player.
     */
    public Player buildPlayer(String type) {
        Player player = null;
        switch (type) {
            case "human":
                player = new HumanPlayer();
                break;
            case "whatever":
                player = new WhateverPlayer();
                break;
            case "clever":
                player = new CleverPlayer();
                break;
            case "genius":
                player = new GeniusPlayer();
                break;
        }
        return player;
    }
}