import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board extends Observable {
    private ArrayList<Tile> board;
    private int playable0, playable1;

    public Board(){
        board = new ArrayList<Tile>();
        playable0 = 0;
        playable1 = 0;
    }

    public ArrayList<Tile> getBoard() {
        return board;
    }

    /**
     *
     * @param tile
     * @param boardSidePlayed refers to which end of the board. You can think of the left side as side1 and right as side2
     */
    public void addTile(Tile tile, int boardSidePlayed){
        //if using playable1 add tile to beginning
        if(board.isEmpty()){
            playable0 = tile.getSide0();
            playable1 = tile.getSide1();
            board.add(tile);
        } else if(boardSidePlayed == 0){
            playable0 = tile.getSide0();
            board.add(0,tile);
        } else if (boardSidePlayed == 1){
            playable1 = tile.getSide1();
            board.add(tile);
        }
    }

    /**
     * @return an array of size 2 with both of the boards exposed numbers that can be played off of.
     */
    public int[] getPlayableNumbers(){
        int[] playables = new int[2];
        playables[0] = playable0;
        playables[1] = playable1;
        return playables;
    }

    public int getPlayable0() {
        return playable0;
    }

    public int getPlayable1() {
        return playable1;
    }
}
