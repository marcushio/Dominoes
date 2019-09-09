import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * This represents the players in the game. We are using an abstract class because behaviors of human players
 * will be different from computer players, and we'll let the concrete classes dictate behavior.
 */
public abstract class Player {
    private String name; //might remove
    private ArrayList<Tile> hand;


    abstract void move();

    public void addToHand(Tile tile){
        hand.add(tile);
    }

    public ArrayList<Tile> getHand(){return hand;}
}
