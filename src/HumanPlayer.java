import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * Represents a human player.
 */
public class HumanPlayer extends Player {
    private ArrayList<Tile> hand;

    public HumanPlayer(ArrayList<Tile> hand){
        this.hand = hand;
    }

    /*
    public HumanPlayer(){
        for(int i=0; i <= 7; i++){
            draw();
        }
    }
     */

    public void move(){

        setPassedTurn(false);
    }

}
