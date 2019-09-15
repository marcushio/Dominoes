import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {
    private VBox root;
    private HBox opponentTray;
    private HBox messageDisplay;
    private HBox topBoardDisplay;
    private HBox bottomBoardDisplay;
    private HBox humanTray;
    private Controller handler; //will be handling all events from GUI

    public GUI(Stage primaryStage){
        root = new VBox(10);
        opponentTray = new HBox(3);
        messageDisplay = new HBox(new Canvas(1,100));
        topBoardDisplay = new HBox(10, new Canvas(1,39));
        bottomBoardDisplay = new HBox(10, new Canvas(1,39));
        humanTray = new HBox(5);

        root.getChildren().addAll(opponentTray, messageDisplay, topBoardDisplay, bottomBoardDisplay, humanTray);
        primaryStage.setTitle("Simple, humble, Dominoes!");
        primaryStage.setScene(new Scene(root, 1600, 700));
        primaryStage.show();
    }

    public void addHandler(Controller controller){
        this.handler = controller;
    }

    public void update(Observable model, Object arg){
        Model updatedModel = (Model) model;
        drawOpponentsHand(updatedModel.getPcPlayer().getHand().size());
        drawHand(updatedModel.getHumanPlayer().getHand());
        drawBoard(updatedModel.getBoard().getBoard());
        writeMessageDisplay(updatedModel);
    }
    /**
     * Draws the opponents hand, user can't see the dots, but you can see how many tiles pc has.
     */
    public void drawOpponentsHand(int handSize){
        opponentTray.getChildren().clear();
       // opponentTray.getChildren().add(new Canvas(40,40));
        for(int i = 0; i < handSize; i++){
            Canvas boneBack = new Canvas(80, 40 );
            GraphicsContext gc = boneBack.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0,0,80,40);
            opponentTray.getChildren().add(boneBack);
        }
    }
    /**
     * draws the human players hand in the tray
     */
    public void drawHand(ArrayList<Tile> hand){
        humanTray.getChildren().clear();
        //humanTray.getChildren().add(new Canvas(40,40));
        int indexCounter = 0;
        for(Tile tile: hand){
            DisplayDomino bone = new DisplayDomino(tile.getSide1(), tile.getSide2(), indexCounter, handler);
            humanTray.getChildren().add(bone);
            indexCounter++;
        }
        Button passButton = new Button("PASS");
        passButton.setOnAction(handler);
        humanTray.getChildren().add(passButton);
    }

    public void writeMessageDisplay(Model model){
        messageDisplay.getChildren().clear();
        messageDisplay.getChildren().add(new Label(model.getStateMessage()));
    }

    /**
     * draw board
     */
    public void drawBoard(ArrayList<Tile> board){
        topBoardDisplay.getChildren().clear();
        bottomBoardDisplay.getChildren().clear();
        bottomBoardDisplay.getChildren().add(new Canvas(40,40));
        int counter = 0;
        for(Tile tile: board){
            if(counter++%2 == 0) {
                topBoardDisplay.getChildren().add(new DisplayDomino(tile.getSide1(), tile.getSide2(), 0, handler));
            } else {
                bottomBoardDisplay.getChildren().add(new DisplayDomino(tile.getSide1(), tile.getSide2(), 0, handler));
            }
        }
    }

}
