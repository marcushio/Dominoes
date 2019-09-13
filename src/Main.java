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
        //humanPlayer = new HumanPlayer(new ArrayList<Tile>()); debug see if it's checking for empty hand
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
        if(humanPlayer.passedTurn() && pcPlayer.passedTurn() && boneyard.isEmpty()) return true;
        return false;
    }

    /**
     * Passes the turn to the next player by setting current player to the next player.
     */
    private void nextPlayer(){
        if(currentPlayer instanceof HumanPlayer) currentPlayer = pcPlayer;
        else currentPlayer = humanPlayer;
    }

    private void printWinner(){
        ArrayList<Tile> humanFinalHand = humanPlayer.getHand();
        ArrayList<Tile> pcFinalHand = pcPlayer.getHand();
        int humanTotal = 0;
        int pcTotal = 0;

        for(Tile tile : humanFinalHand){
            humanTotal += tile.getSide1() + tile.getSide2();
        }
        for(Tile tile : pcFinalHand){
            pcTotal += tile.getSide1() + tile.getSide2();
        }
        System.out.println("xX Final Score Xx \n You: " + humanTotal + " PC: "+ pcTotal);
        if(humanTotal < pcTotal) System.out.println("A WINNER IS YOU!");
        else if(humanTotal > pcTotal ) System.out.println("PC WINS, THE MACHINES ARE TAKING OVER!");
        else System.out.println("YOUR MIGHTS ARE EVENLY MATCHED, TIE GAME");
    }

    public static void main(String[] args) {
        Main controller = new Main();
        while(controller.running){
            controller.view.printBoard(controller.board.getBoard());
            ArrayList<Tile> hand = controller.currentPlayer.getHand();

            int openTile1 = controller.board.getPlayableNumbers()[0];
            int openTile2 = controller.board.getPlayableNumbers()[1];
            //System.out.println("playable1: " + openTile1 + " playable2: " + openTile2);
            while(!controller.currentPlayer.hasMove(openTile1, openTile2) && !controller.boneyard.isEmpty()){
                controller.currentPlayer.takeTile(controller.pullTile()); //player is given a random tile from the boneyard
                //System.out.println("Tile Drawn... New Hand...  " + hand); this is for showing any players hand
            }
            if(controller.currentPlayer instanceof HumanPlayer) System.out.println("Your hand... \n" + hand); //show the human player their hand
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
        controller.printWinner();
    }
}
