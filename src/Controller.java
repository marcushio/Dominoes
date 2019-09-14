import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 *
 * Controller handles all the logic and is the go between for the view (Console display for console version and GUI for
 * graphic version) and the model (Board).
 */

public class Controller implements EventHandler {
    //private ConsoleDisplay view;
    private Model model;
    //private Player currentPlayer;
    private Move currentMove;


    public Controller(){

    }

    public void addModel(Model model){
        this.model = model;
    }



    @Override
    public void handle(Event event){
        Canvas selected = (Canvas) event.getSource();
        DisplayDomino selectedBone = (DisplayDomino) selected.getParent();
        int side = selectedBone.getChildren().indexOf(selected) + 1;
        Parent boneParent = selectedBone.getParent();
        int index = boneParent.getChildrenUnmodifiable().indexOf(selectedBone);
        currentMove = new Move(index, side);
        Tile movedTile = model.currentPlayer.removeTileFromHand(currentMove.getTileIndex());
        if(currentMove.getPlayedSide() == model.getBoard().getPlayable1()){
            if(movedTile.getSide1() == model.getBoard().getPlayable1()) movedTile.flip();
            model.addTile(movedTile, 1);
        } else {
            if(movedTile.getSide2() == model.getBoard().getPlayable2()) movedTile.flip();
            model.addTile(movedTile, 2);
        }

        model.setCurrentPlayer();
    }



    /**
     * Checks to see if a win condition has been met
     * @return true if a player's hand is empty or the game went pass-pass else false.
     */
    private boolean isWin(Player humanPlayer, Player pcPlayer){
        if(humanPlayer.passedTurn() && pcPlayer.passedTurn() && model.getBoneyard().isEmpty()) return true;
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
        //view.printWinner(humanTotal, pcTotal);
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

            if(controller.currentPlayer instanceof HumanPlayer) controller.view.printHand(hand);  //show the human player their hand
            }
            if( controller.isWin() ) controller.running = false;
            controller.nextPlayer();
        }
        controller.checkWinner();
    }
     */
}
