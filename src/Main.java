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
    private Player currentPlayer;
    private boolean running;

    public Main(){
        board = new Board();
        ArrayList<Tile> newHand = getNewHand();
        humanPlayer = new HumanPlayer(newHand);
        newHand = getNewHand();
        pcPlayer = new ComputerPlayer(newHand);
        currentPlayer = humanPlayer;
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

    /**
     * Pull a random tile from the boneyard.
     * @return
     */
    private Tile pullTile(){
        int random = (int)(Math.random() * board.boneyard.size());
        Tile pulledTile = board.pullTile(random);
        return pulledTile; 
    }

    /**
     * Checks to see if a win condition has been met
     * @return true if a player's hand is empty or the game went pass-pass else false.
     */
    private boolean isWin(){
        if(humanPlayer.handIsEmpty() || pcPlayer.handIsEmpty()) return true;
        if(humanPlayer.passedTurn() && pcPlayer.passedTurn()) return true;
        return false;
    }

    public static void main(String[] args) {
        Main controller = new Main();
        while(controller.running){
            ArrayList<Tile> hand = controller.currentPlayer.getHand();
            System.out.println("Your hand... \n" + hand);
            //controller.running = controller.isWin();
            controller.running = false;
        }
    }
}
