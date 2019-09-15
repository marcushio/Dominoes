import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 *
 * Controller handles all the logic and is the go between for the view (Console display for console version and GUI for
 * graphic version) and the model (Board).
 */

public class Controller {
    private Model model;
    private Move currentMove;

    public void addModel(Model model){ this.model = model; }

    public class TileHandler implements EventHandler{
        @Override
        public void handle(Event event){
            Canvas selectedCanvas = (Canvas) event.getSource();
            DisplayDomino selectedBone = (DisplayDomino) selectedCanvas.getParent();
            int index = selectedBone.index;
            int sideSelected = selectedBone.getChildren().indexOf(selectedCanvas);
            model.setSelectedTile(index, sideSelected);
        }
    }
    public class LeftButtonHandler implements EventHandler{
        @Override
        public void handle(Event event){
            model.moveHuman(0); //just got human input from gui so we're moving human
            model.setCurrentPlayer(); //change to pc's turn
            model.pcMoves(); //pc makes move
            model.setCurrentPlayer(); //set to human turn
            model.currentPlayerDraws(); //if no moves and boneyard isn't empty human draws then we wait for input
            if(isWin(model.getHumanPlayer(), model.getPcPlayer())) checkWinner(model.getHumanPlayer(), model.getPcPlayer());
        }
    }
    public class RightButtonHandler implements EventHandler{
        @Override
        public void handle(Event event){
            model.moveHuman(1);
            model.setCurrentPlayer();
            model.pcMoves(); //pc handles own drawing
            model.setCurrentPlayer(); //set to human turn
            model.currentPlayerDraws(); //if no moves and boneyard isn't empty human draws then we wait for input
            if(isWin(model.getHumanPlayer(), model.getPcPlayer())) checkWinner(model.getHumanPlayer(), model.getPcPlayer());
        }
    }
    public class PassButtonHandler implements EventHandler{
        @Override
        public void handle(Event event){
            if(model.humanPlayer.hasMove(model.getBoard().getPlayable0(),model.getBoard().getPlayable1())){
                model.setStateMessage("You have a valid move, you can't pass.");
            }
            model.currentPlayer.setPassedTurn(true);
            model.setCurrentPlayer();
            model.pcMoves(); //pc draws and moves
            model.setCurrentPlayer();
            model.currentPlayerDraws();
            if(isWin(model.getHumanPlayer(), model.getPcPlayer())) checkWinner(model.getHumanPlayer(), model.getPcPlayer());
        }
    }

    /**
     * Checks to see if a win condition has been met
     * @return true if a player's hand is empty or the game went pass-pass else false.
     */
    private boolean isWin(Player humanPlayer, Player pcPlayer){
        if(humanPlayer.passedTurn() && pcPlayer.passedTurn() && model.getBoneyard().isEmpty()) return true;
        return false;
    }

    private void checkWinner(Player humanPlayer, Player pcPlayer){
        ArrayList<Tile> humanFinalHand = humanPlayer.getHand();
        ArrayList<Tile> pcFinalHand = pcPlayer.getHand();
        int humanTotal = 0;
        int pcTotal = 0;
        for(Tile tile : humanFinalHand){
            humanTotal += tile.getSide0() + tile.getSide1();
        }
        for(Tile tile : pcFinalHand){
            pcTotal += tile.getSide0() + tile.getSide1();
        }
        if (humanTotal < pcTotal) model.setStateMessage("A WINNER IS YOU! CONGRATULATIONS");
        else if (humanTotal > pcTotal) model.setStateMessage("EGAD, THE MACHINES ARE TAKING OVER, PC WINS");
        else model.setStateMessage("YOUR MIGHT HAS BEEN MATCHED, TIE GAME");
        //view.printWinner(humanTotal, pcTotal);
    }
}
