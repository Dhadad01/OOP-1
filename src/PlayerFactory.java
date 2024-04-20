public class PlayerFactory {
    //types of players
    public static final String CLEVER = "clever";
    public static final String GENIUS = "genius";
    public static final String WHATEVER = "whatever";
    public static final String HUMAN = "human";
     PlayerFactory(){}

    /**
     *
     * @param type of player - string
     * @return Player - type of player
     */
    public Player
    buildPlayer(String type){
         String PType = type.toLowerCase();
        return switch (PType) {
            case CLEVER -> new CleverPlayer();
            case GENIUS -> new GeniusPlayer();
            case HUMAN -> new HumanPlayer();
            case WHATEVER -> new WhateverPlayer();
            default -> null;
        };
    }

}
