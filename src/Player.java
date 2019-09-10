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


    abstract Move move(int[] playables);

    /**
     *
     * @param option1
     * @param option2
     * @return
     */
    public boolean hasMove(int option1, int option2){
        for(Tile tile: hand){
            if(tile.getSide1() == option1 || tile.getSide2() == option2) return true;
            else if(tile.getSide2() == option1 || tile.getSide2() == option2) return true;
        }
        return false;
    }


    public void removeTileFromHand(int index){
        hand.remove(index);
    }

    public boolean handIsEmpty(){
        if(hand.size() == 0) return true;
        return false;
    }

    public boolean passedTurn(){return passedLastTurn;}

    public void setPassedTurn(boolean turnPassed){passedLastTurn = turnPassed;}

    public void addToHand(Tile tile){
        hand.add(tile);
    }

    public ArrayList<Tile> getHand(){return hand;}
}
