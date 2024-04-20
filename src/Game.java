public class Game {
    //possible directions for a strike
    private static final int[][] POSSIBLE_MOVES = {{0, 1},
            {-1, 1},
            {1, 1},
            {1, 0} };
    // ********************************* Data Members *********************************
    private Player playerX;
    private Player playerO;
    private Board board;
    private int winStreak;
    Renderer renderer;
    // ********************************* Public Methods *********************************
    /**
     * C'tor for a new Game  with default board size
     * @param playerX the first player who playing as X
     * @param playerO the second player who playing as O
     * @param renderer the renderer used for displaying the game
     */
    public Game(Player playerX,Player playerO,Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.board = new Board();
        this.winStreak = 3;
    }
    /**
     * C'tor for a new Game instance with a given board size and win streak
     * @param playerX - first player (playing as X)
     * @param playerO - second player (playing as O)
     * @param size - size of the game board
     * @param winStreak - number of consecutive marks needed to win the game
     * @param renderer - used for displaying the game
     */
    public Game(Player playerX,Player
            playerO, int size, int
                        winStreak,Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.board = new Board(size);
        this.winStreak = winStreak;
    }

    /**
     *
     * @param row of starting point
     * @param col of starting point
     * @param mark of current player
     * @param direction of winstreak needed
     * @return mark of winner if we have one - else blank
     */
    private Mark singleDirection(int row,int col,Mark mark,int[] direction){
        int cur_row = row;
        int cur_col = col;
        for (int i = 0; i < this.winStreak; i++) {
            //add out of bounds
            if(outOfBounds(cur_row,cur_col)||this.board.getMark(cur_row,cur_col) != mark){
                return Mark.BLANK;
            }
            cur_row+=direction[0];
            cur_col+=direction[1];
        }
        return mark;
    }

    /**
     *
     * @param row of the point
     * @param col of the point
     * @return true if out of bounds, false else
     */
    private boolean outOfBounds(int row,int col){
        return row < 0 || row >= this.board.getSize() || col < 0 || col >= this.board.getSize();
    }

    /**
     *
     * @param row of point to check winning streak
     * @param col of point to check winning streak
     * @param mark of current player
     * @return mark of winner if we have one - else blank
     */
    private Mark allDirections(int row,int col,Mark mark){
        for (int[] move:POSSIBLE_MOVES){
            if (singleDirection(row,col,mark,move)!= Mark.BLANK){return singleDirection(row,col,mark,move);}
        }
        return Mark.BLANK;
    }

    /**
     *
     * @return mark of winner if we have one - else blank
     */
    private Mark checkWinner(){
        Mark[] marks = {Mark.X,Mark.O};
        for (Mark mark:marks){
            for (int i = 0; i < this.board.getSize(); i++) {
                for (int j = 0; j < this.board.getSize(); j++) {
                    if(allDirections(i,j,mark)!=Mark.BLANK){
                        return allDirections(i,j,mark);
                    }
                }
            }
        }
        return Mark.BLANK;
    }

    /**
     *
     * @return true if we have draw
     */
    private boolean checkDraw(){
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (this.board.getMark(i,j)==Mark.BLANK){return false;}
            }
        }
        return true;
    }

    /**
     *
     * @return winStreak
     */
    public int getWinStreak(){ return this.winStreak;}

    public int getBoardSize(){return this.board.getSize();}

    /**
     *
     * @return mark of winner if we have one - else blank
     */
    public Mark run(){
        this.renderer.renderBoard(this.board);
        Player cur_player = this.playerX;
        Mark cur_mark = Mark.X;
        while (checkWinner()==Mark.BLANK&&!checkDraw()){
            cur_player.playTurn(this.board,cur_mark);
            this.renderer.renderBoard(this.board);

            if(cur_player==this.playerX){
                cur_player=this.playerO;
                cur_mark = Mark.O;
            }
            else {
                cur_player = this.playerX;
                cur_mark = Mark.X;
            }
        }
        if (checkDraw()){
            return Mark.BLANK;
        }
        return checkWinner();
    }
}
