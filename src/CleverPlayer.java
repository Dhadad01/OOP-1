public class CleverPlayer implements Player{

    @Override
    //play single turn in a given board, marking specific mark
    public void playTurn(Board board, Mark mark){
        int boardSize = board.getSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.putMark(mark,j,i)){
                    return;
                }
            }
        }
    }
}
