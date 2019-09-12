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
    private ConsoleDisplay view;
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
        view = new ConsoleDisplay();
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

    private ArrayList<Tile> getNewHand(){
        ArrayList<Tile> newHand = new ArrayList<Tile>();
        for(int i=0; i < 7; i++){
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

    private void nextPlayer(){
        if(currentPlayer instanceof HumanPlayer) currentPlayer = pcPlayer;
        else currentPlayer = humanPlayer;
    }

    public static void main(String[] args) {
        Main controller = new Main();
        while(controller.running){
            controller.view.printBoard(controller.board.getBoard());
            ArrayList<Tile> hand = controller.currentPlayer.getHand();
            if(controller.currentPlayer instanceof HumanPlayer) System.out.println("Your hand... \n" + hand); //show the human player their hand
            int openTile1 = controller.board.getPlayableNumbers()[0];
            int openTile2 = controller.board.getPlayableNumbers()[1];     //you could probably use some consistency for when you crack into the playablenumbers[]
            System.out.println("playable1: " + openTile1 + " playable2: " + openTile2);
            while(!controller.currentPlayer.hasMove(openTile1, openTile2) && !controller.boneyard.isEmpty()){
                controller.currentPlayer.takeTile(controller.pullTile());
            }
            controller.currentMove = controller.currentPlayer.move(controller.board.getPlayableNumbers());
            if(controller.currentMove != null) {
                Tile movedTile = controller.currentPlayer.removeTileFromHand(controller.currentMove.getTileIndex());
                System.out.println("player played tile: " + movedTile);
                if(controller.currentMove.getPlayedSide() == controller.board.getPlayable1()){
                    if(movedTile.getSide1() == controller.board.getPlayable1()) movedTile.flip();
                    controller.board.addTile(movedTile, 1);
                } else {
                    if(movedTile.getSide2() == controller.board.getPlayable2()) movedTile.flip();
                    controller.board.addTile(movedTile, 2);
                }
            }
            if( controller.isWin() ) controller.running = false;
            controller.nextPlayer();
        }
        System.out.println("GAME OVER");
    }
}
