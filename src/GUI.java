import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {
    private VBox root;
    private HBox opponentTray;
    private HBox endGameDisplay;
    private HBox boardDisplay;
    private HBox humanTray;

    public GUI(Stage primaryStage){
        root = new VBox(10);
        opponentTray = new HBox(3);

        //drawOpponentsHand(pcHand.size());
        endGameDisplay = new HBox();
        boardDisplay = new HBox(2);
        DisplayDomino testBone = new DisplayDomino(1,1);
        humanTray = new HBox(3);
        humanTray.getChildren().add(testBone);
        //drawHand(humanHand);

        root.getChildren().addAll(opponentTray, endGameDisplay, boardDisplay, humanTray);
        primaryStage.setTitle("Simple, humble Dominoes!");
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();
    }

    public void update(Observable model, Object arg){
        Model updatedModel = (Model) model;
        drawOpponentsHand(updatedModel.getPcPlayer().getHand().size());
        drawHand(updatedModel.getHumanPlayer().getHand());
        drawBoard(updatedModel.getBoard());
    }

    /**
     * draws a new tile onto the board
     */
    private void drawTile(){

    }

    /**
     * draws the human players hand in the tray
     */
    public void drawHand(ArrayList<Tile> hand){
        for(Tile tile: hand){
            DisplayDomino bone = new DisplayDomino(tile.getSide1(), tile.getSide2());
            humanTray.getChildren().add(bone);
        }

    }

    /**
     * Draws the opponents hand, user can't see the dots, but you can see how many tiles pc has.
     */
    public void drawOpponentsHand(int handSize){
        for(int i = 0; i < handSize; i++){
            Canvas boneBack = new Canvas(80, 40 );
            GraphicsContext gc = boneBack.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0,0,80,40);
            opponentTray.getChildren().add(boneBack);
        }
    }

    /**
     * draw board
     */
    public void drawBoard(ArrayList<Tile> board){

    }

}
