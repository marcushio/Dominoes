import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 *
 * Controller handles all the logic and is the go between for the view (Console display for console version and GUI for
 * graphic version) and the model (Board).
 */

public class Controller {
    private ConsoleDisplay view;
    /*
    private Board board;
    private Player humanPlayer;
    private Player pcPlayer;
     */
    private Model model;
    private Player currentPlayer;
    private Move currentMove;
    private ArrayList<Tile> boneyard;
    private boolean running;

    public Controller(){
        fillBoneyard();
        /*replaced by code below
        board = new Board();
        humanPlayer = new HumanPlayer(getNewHand());
        pcPlayer = new ComputerPlayer(getNewHand());
        currentPlayer = humanPlayer;
         */

    }

    public void addModel(Model model){
        this.model = model;
    }

    private void fillBoneyard(){
        boneyard = new ArrayList<Tile>();
        for (int i = 0; i < Main.STARTING_HAND_SIZE; i++){
            for (int j = 6; j >= i; j--){
                boneyard.add(new Tile(i,j));
            }
        }
    }

    public ArrayList<Tile> getNewHand(){
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
    private boolean isWin(Player humanPlayer, Player pcPlayer){
        if(humanPlayer.passedTurn() && pcPlayer.passedTurn() && boneyard.isEmpty()) return true;
        return false;
    }

    /**
     * Passes the turn to the next player by setting current player to the next player.
     */
    private void nextPlayer(){
        model.setCurrentPlayer();
    }

    private void checkWinner(HumanPlayer humanPlayer, ComputerPlayer pcPlayer){
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
        view.printWinner(humanTotal, pcTotal);
    }

    /*
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
            if(controller.currentPlayer instanceof HumanPlayer) controller.view.printHand(hand);  //show the human player their hand
            controller.currentMove = controller.currentPlayer.move(controller.board.getPlayableNumbers());
            if(controller.currentMove != null) {
                Tile movedTile = controller.currentPlayer.removeTileFromHand(controller.currentMove.getTileIndex());
                controller.view.printTilePlayed(movedTile); //
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
        controller.checkWinner();
    }
     */
}
