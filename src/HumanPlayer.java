import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * Represents a human player.
 */
public class HumanPlayer extends Player {
    private InputGetter input;
    private int tileSelected; //the tile in the player's hand that is selected for play
    private int sideSelected; //of the domino

    /**
     * constructor set's the players starting hand
     * @param hand
     */
    public HumanPlayer(ArrayList<Tile> hand){
        this.hand = hand;
    }

    /**
     * Set's which bone in player's hand is selected for future use on the board.
     * @param newTileSelected the new tile that will now be selected
     * @param sideSelected the side of the selected domino that will be used.
     */
    public void setTileSelected(int newTileSelected, int sideSelected) {
        if(hand.size() > tileSelected)
            hand.get(tileSelected).setIsSelected(false); //if the hand still has the old selected
        hand.get(newTileSelected).setIsSelected(true);
        this.tileSelected = newTileSelected;
        hand.get(tileSelected).setSideSelected(sideSelected);
    }

    /**
     * given the player's current selected domino and domino side, see if it's possible
     * to move on the given side of the board.
     * @param boardNumber the board number that we're seeing if we can play off. I.e [5|x] seeing if we can play off 5.
     * @return true if the move is possible else false.
     */
    public boolean canMove(int boardNumber){
        //check to see if it's valid
        Tile tile = hand.get(tileSelected);
        if (sideSelected == 0) return true;
        else if(sideSelected != boardNumber && boardNumber != 0 ) return false;
        return true;
    }

    /**
     * Remove the tile that is currently selected in the hand.
     * @return the selected domino.
     */
    public Tile pullSelectedTile(){
        Tile tile = hand.remove(tileSelected);
        return tile;
    }
}
