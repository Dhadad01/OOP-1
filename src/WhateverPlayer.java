import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;

public class WhateverPlayer implements Player{
    @Override
    //play single turn in a given board, marking specific mark
    public void playTurn(Board board, Mark mark){
        int boardSize = board.getSize();
        Random rand = new Random();
        int place;
        ArrayList<Integer> free_spots = free_places(board);
        place = rand.nextInt(free_spots.size());
        board.putMark(mark,free_spots.get(place)/boardSize,free_spots.get(place)%boardSize);
    }
    //check available places to sign
    private ArrayList<Integer> free_places(Board board){
        ArrayList<Integer> free_spots = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i,j)==Mark.BLANK){
                    free_spots.add(i*board.getSize()+j);
                }
            }
        }
        return free_spots;
    }
}
