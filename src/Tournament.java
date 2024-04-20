public class Tournament {
    private static final int ROUNDS_INDEX = 0;
    private static final int BOARD_SIZE_IND = 1;
    private static final int WIN_STREAK_IND = 2;
    private static final int RENDERER_NAME_IND = 3;
    private static final int PLAYER_NAME_1_IND = 4;
    private static final int PLAYER_NAME_2_IND = 5;
    int rounds;
    Renderer renderer;
    Player player1;
    Player player2;

    /**
     *C'tor
     */
    Tournament(int rounds, Renderer renderer,
               Player player1, Player player2){
        this.rounds=rounds;
        this.renderer=renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     *
     * @param size of board
     * @param winStreak - number of MARKS together in order to win
     * @param playerName1
     * @param playerName2
     * runs a single tournament
     */
    public void playTournament(int size, int winStreak,
                        String playerName1, String playerName2){
        int P1_wins = 0;
        int P2_wins = 0;
        int ties = 0;
        for (int i = 0; i < this.rounds; i++) {
            if (i%2==0){
                Game game = new Game(this.player1,this.player2,size,winStreak,this.renderer);
                Mark won = game.run();
                if (won==Mark.X){P1_wins+=1;}
                else if (won==Mark.O){P2_wins+=1;}
                else {ties+=1;}
            }
            else {
                Game game = new Game(this.player2,this.player1,size,winStreak,this.renderer);
                Mark won = game.run();
                if (won==Mark.O) {P1_wins+=1;}
                else if (won==Mark.X) {P2_wins+=1;}
                else {ties+=1;}
            }
        }
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerName1 + " won: " + P1_wins + " rounds");
        System.out.println("Player 2, " + playerName2 + " won: " + P2_wins + " rounds");
        System.out.println("Ties: " + ties);
    }

    /**
     * main - here we take args and check them, and if evereything is OK we run tournament
     * @param args
     */

    public static void main(String[] args){
        int rounds = Integer.parseInt(args[ROUNDS_INDEX]);
        int boardSize = Integer.parseInt(args[BOARD_SIZE_IND]);
        int winStreak = Integer.parseInt(args[WIN_STREAK_IND]);
        String rendererName = args[RENDERER_NAME_IND];
        String playerName1 = args[PLAYER_NAME_1_IND];
        String playerName2 = args[PLAYER_NAME_2_IND];
        Renderer renderer = new RendererFactory().buildRenderer(rendererName,boardSize);
        if(renderer==null){
            System.out.println(Constants.UNKNOWN_RENDERER_NAME);
            return;
        }
        Player p1 = new PlayerFactory().buildPlayer(playerName1);
        Player p2 = new PlayerFactory().buildPlayer(playerName2);
        if(p1==null||p2==null){
            System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            return;
        }
        if (winStreak>boardSize){winStreak=boardSize;}
        Tournament tournament = new Tournament(rounds,renderer,p1,p2);
        tournament.playTournament(boardSize,winStreak,playerName1,playerName2);
    }
}
