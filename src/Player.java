import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * This represents the players in the game. We are using an abstract class because behaviors of human players
 * will be different from computer players, and we'll let the concrete classes dictate behavior.
 */
public abstract class Player {
    private String name; //might remove
    private boolean passedLastTurn;
    public ArrayList<Tile> hand;


    //abstract boolean canMove(int[] playables);

    /**
     *
     * @param option1
     * @param option2
     * @return
     */
    public boolean hasMove(int option1, int option2){
        if(hand.isEmpty()) return false;
        for(Tile tile: hand){
            int side1 = tile.getSide0();
            int side2 = tile.getSide1();
            if(side1 == 0 || side2 ==0) return true;
            else if(option1 == 0 || option2 == 0 ) return true;
            else if(side1 == option1 || side1 == option2) return true;
            else if(side2 == option1 || side2 == option2) return true;
        }
        return false;
    }

    public Tile removeTileFromHand(int index){
        return hand.remove(index);
    }

    public boolean handIsEmpty(){
        return hand.isEmpty();
    }

    /**
     * takes a tile that was drawn from the boneyard
     * @param tile
     */
    public void takeTile(Tile tile){
        hand.add(tile);
    }

    public boolean passedTurn(){return passedLastTurn;}

    public void setPassedTurn(boolean turnPassed){passedLastTurn = turnPassed;}

    public void addToHand(Tile tile){
        hand.add(tile);
    }

    public ArrayList<Tile> getHand(){return hand;}
}
