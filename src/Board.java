import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board extends Observable {
    private ArrayList<Tile> board;
    private int playable1 = 0, playable2 = 0;

    public ArrayList<Tile> getBoard() {
        return board;
    }

    public void addTile(Tile tile, Move move){
        //if using playable1 add tile to beginning
        if(move.getPlayedSide() == playable1){ board.add(0,tile); } //i think i have to add flippabilty
        else if (move.getPlayedSide() == playable2){ board.add(tile); }
        //if using playable2 add tile to end
    }

    /**
     * @return an array of size 2 with both of the boards exposed numbers that can be played off of.
     */
    public int[] getPlayableNumbers(){
        int[] playables = new int[2];
        playables[0] = playable1;
        playables[1] = playable2;
        return playables;
    }
}
