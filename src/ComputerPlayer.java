import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class ComputerPlayer extends Player {
    private ArrayList<Tile> hand;

    public ComputerPlayer(ArrayList<Tile> hand){
        this.hand = hand;
    }

    public void move(){
        setPassedTurn(false);
    }
}
