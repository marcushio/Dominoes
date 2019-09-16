import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/6/2019
 * The board represents the actual playing board where tiles are laid. It knows which numbers it has that can be played on
 */
public class Board extends Observable {
    private ArrayList<Tile> board;
    private int playable0, playable1;

    /**
     * Initialize the board. Start playable numbers as 0's so anything can be played (since the board is empty)
     */
    public Board(){
        board = new ArrayList<Tile>();
        playable0 = 0;
        playable1 = 0;
    }
    /**
     * @return the structure holding the domino's that have been played
     */
    public ArrayList<Tile> getBoard() {
        return board;
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

    /**
     * @return the open number on the left side of the board
     */
    public int getPlayable0() {
        return playable0;
    }

    /**
     * @return the open number on the right side of the board
     */
    public int getPlayable1() {
        return playable1;
    }

    /**
     * adds a tile to the specified side of the board.
     * @param tile
     * @param boardSidePlayed refers to which end of the board. You can think of the left side as side0 and right as side1
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
}
