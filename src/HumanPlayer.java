public class HumanPlayer implements Player{
    @Override
    public void playTurn(Board board, Mark mark){
        int boardSize = board.getSize();
        int row,col;
        if (mark == Mark.X){
            System.out.print(Constants.playerRequestInputString("X"));
        }
        else {
            System.out.print(Constants.playerRequestInputString("O"));
        }
        while (true) {
            int input = KeyboardInput.readInt();
            row = input/10;
            col = input%10;

            if (row < 0 || col < 0 || row >= boardSize || col >= boardSize) {
                System.out.println(Constants.INVALID_COORDINATE);
                continue;
            }
            if (!board.putMark(mark, row, col)) {
                System.out.println(Constants.OCCUPIED_COORDINATE);
            } else {
                break;
            }
        }
        board.putMark(mark,row,col);

    }
}
