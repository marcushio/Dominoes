import java.util.ArrayList;
import java.util.Collections;

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
    private Move currentMove;
    private ArrayList<Tile> boneyard;
    private boolean running;

    public Main(){
        boneyard = new ArrayList<Tile>();
        fillBoneyard();
        board = new Board();
        ArrayList<Tile> newHand = getNewHand();
        humanPlayer = new HumanPlayer(newHand);
        newHand = getNewHand();
        pcPlayer = new ComputerPlayer(newHand);
        currentPlayer = humanPlayer;
        running = true;
    }

    private void fillBoneyard(){
        boneyard = new ArrayList<Tile>();
        for (int i = 0; i < 7; i++){
            for (int j = 6; j >= i; j--){
                boneyard.add(new Tile(i,j));
                //System.out.println("this is tile " + i +"," + j);
                //System.out.println(boneyard.size());
            }
        }
    }

    public void shuffleBones(){
        Collections.shuffle(boneyard);
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
     * @return the tile that you pulled from the boneyard.
     */
    private Tile pullTile(){
        int random = (int)(Math.random() * boneyard.size());
        Tile pulledTile = boneyard.remove(random);
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
            if(controller.currentPlayer instanceof HumanPlayer) System.out.println("Your hand... \n" + hand); //show the human player their hand
            int openTile1 = controller.board.getPlayableNumbers()[0];
            int openTile2 = controller.board.getPlayableNumbers()[1];     //you could probably use some consistency for when you crack into the playablenumbers[]
            //I think I'll just have players return null if they don't have a move.
            //if(controller.currentPlayer.hasNoMoves(openTile1, openTile2)) controller.currentPlayer.setPassedTurn(true);  //if current player has no moves then have them pass
            controller.currentMove = controller.currentPlayer.move(controller.board.getPlayableNumbers());
            //check if move is valid. Have players do that.
            if(controller.currentMove != null)
                controller.currentPlayer.removeTileFromHand(controller.currentMove.getTileIndex());

            controller.running = controller.isWin();
            //controller.running = false;
        }
    }
}
