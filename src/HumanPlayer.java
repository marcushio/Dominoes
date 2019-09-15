import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * Represents a human player.
 */
public class HumanPlayer extends Player {
    private InputGetter input;
    private int tileSelected;
    private int sideSelected;

    public HumanPlayer(ArrayList<Tile> hand){
        this.hand = hand;
        input = new InputGetter();
    }

    public void setTileSelected(int newTileSelected, int sideSelected) {
        if(hand.size() > tileSelected)
            hand.get(tileSelected).setIsSelected(false); //if the hand still has the old selected
        hand.get(newTileSelected).setIsSelected(true);
        this.tileSelected = newTileSelected;
        hand.get(tileSelected).setSideSelected(sideSelected);
    }

    public boolean canMove(int boardNumber){
        //check to see if it's valid
        Tile tile = hand.get(tileSelected);
        if (sideSelected == 0) return true;
        else if(sideSelected != boardNumber && boardNumber != 0 ) return false;
        return true; //return new Move(tileSelected, sideSelected, 0);
    }

    public Tile pullSelectedTile(){
        Tile tile = hand.remove(tileSelected);
        return tile;
    }
    /*we're going to need some protections
    public Move move(int[] playables){
        if(hasMove(playables[0], playables[1])) {
            int tileIndex = Integer.parseInt(input.getStringInput("Which tile do you want to play?"));
            int playedSide = Integer.parseInt(input.getStringInput("Which number will you use on this tile?"));
            Move move = new Move(tileIndex, playedSide);
            setPassedTurn(false);
            return move;
        } else {
            setPassedTurn(true);
            return null;
        }
    }
     */

}
