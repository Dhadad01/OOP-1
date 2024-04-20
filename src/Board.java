import java.util.ArrayList;
public class Board {
    // ********************************* Data Members *********************************
    private static final int DEFAULT_SIZE = 4;
    private int size;
    private Mark[][] board;
    // ********************************* Public Methods *********************************
    /**
     * Default C'tor that initialize the board with default value
     */
    public Board(){
        this.size = DEFAULT_SIZE;
        this.board = new Mark[this.size][this.size];
        initialize(this.size);
    }
    /**
     * C'tor that initialize the board upon the size given
     * @param size
     */
    public Board(int size){
        this.size = size;
        this.board = new Mark[this.size][this.size];
        initialize(this.size);
    }
    /**
     * getter for the size of the board
     * @return the size of the board in the current game
     */
    public int getSize(){
        return this.size;
    }

    /* put mark in specific location*/
    public boolean putMark(Mark mark,
                           int row, int col){
        if (out_of_bound(row,col)) {
            System.out.println(Constants.INVALID_COORDINATE);
            return false;
        }
        else if (board[row][col]!=Mark.BLANK){
            System.out.println(Constants.OCCUPIED_COORDINATE);
            return false;
        }
        this.board[row][col] = mark;
        return true;
    }
    /**
     * getter for the current mark in the cell
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the mark of the cell
     */

    public Mark getMark(int row, int col){
        if (out_of_bound(row,col)) {
            System.out.println(Constants.INVALID_COORDINATE);
            return Mark.BLANK;
        }
        return this.board[row][col];
    }

    //fill the board with BLANKS
    private void initialize(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = Mark.BLANK;
            }

        }

    }

    //check if a given location is out of bounds
    private boolean out_of_bound(int row,int col){
        return row>=this.size||row<0||col>=this.size||col<0;
    }


}
