import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.List;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board extends Observable {
    private ArrayList<Tile> board;
    private int playable1 = 0, playable2 = 0;

    public Board(){
        board = new ArrayList<Tile>();
    }

    public ArrayList<Tile> getBoard() {
        return board;
    }

    /**
     *
     * @param tile
     * @param endPlayed refers to which end of the board. You can think of the left side as side1 and right as side2
     */
    public void addTile(Tile tile, int endPlayed){
        //if using playable1 add tile to beginning
        if(board.isEmpty()){
            System.out.println("empty board");
            playable1 = tile.getSide1();
            playable2 = tile.getSide2();
            board.add(tile);
        } else if(endPlayed == 1){
            System.out.println("changing playable1");
            playable1 = tile.getSide1();
            System.out.println("we're doing playable1: " + playable1);
            board.add(0,tile);
        } else if (endPlayed == 2){
            System.out.println("changing playable2");
            playable2 = tile.getSide2();
            System.out.println("We're doing playable2: " + playable2);
            board.add(tile);
        }

        //playable2 = tile.getSide2();
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

    public int getPlayable1() {
        return playable1;
    }

    public int getPlayable2() {
        return playable2;
    }
}
