import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 *
 * This is a simple version of dominoes. Check README for full rules.
 * Main handles most of the "game level" logic such as manipulating tiles and placing them on the board, as well as
 * checking for a win condition.
 */
public class Main {
    private Board board;
    private Player humanPlayer;
    private Player pcPlayer;
    private boolean running;

    public Main(){
        board = new Board();
        ArrayList<Tile> newHand = getNewHand();
        humanPlayer = new HumanPlayer(newHand);
        newHand = getNewHand();
        pcPlayer = new ComputerPlayer(newHand);
        running = true;
    }

    private ArrayList<Tile> getNewHand(){
        ArrayList<Tile> newHand = new ArrayList<Tile>();
        for(int i=0; i <= 7; i++){
            Tile pulledTile = pullTile();
            newHand.add(pulledTile);
        }
        return newHand;
    }

    private Tile pullTile(){
        int random = (int)(Math.random() * board.boneyard.size());
        Tile pulledTile = board.pullTile(random);
        return pulledTile; 
    }

    public static void main(String[] args) {
        Main controller = new Main();
        while(controller.running){

        }

    }
}
