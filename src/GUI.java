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
import java.util.Observable;
import java.util.Observer;

/**
 * @author Marcus Trujillo
 * @version 9/14/2019
 *
 * This is the GUI for our game, it observes changes made on the model (board, players etc) and displays those
 * changes as the model notifies it.
 */
public class GUI implements Observer {
    private VBox root;
    private HBox opponentTray;
    private HBox boardDisplay;
    private HBox messageDisplay;
    private HBox topBoardDisplay;
    private HBox bottomBoardDisplay;
    private HBox humanTray;
    private Controller controller; //will be handling all events from GUI

    /**
     * set all the nodes in the stage
     * @param primaryStage
     * @param controller
     */
    public GUI(Stage primaryStage, Controller controller){
        this.controller = controller;
        root = new VBox(10);
        opponentTray = new HBox(3);
        messageDisplay = new HBox(new Canvas(1,100));
        boardDisplay = new HBox(20);
        addBoardDisplayChildren();
        humanTray = new HBox(5);
        root.getChildren().addAll(opponentTray, messageDisplay, boardDisplay, humanTray);
        primaryStage.setTitle("Simple, humble, Dominoes!");
        primaryStage.setScene(new Scene(root, 1500, 700));
        primaryStage.show();
    }

    /**
     * helper method to add all children to the board display Hbox
     */
    private void addBoardDisplayChildren(){
        Button leftButton = new Button("LEFT");
        leftButton.setOnAction(controller.new LeftButtonHandler());
        VBox boardDisplays = new VBox();
        topBoardDisplay = new HBox(5);
        bottomBoardDisplay = new HBox(5);
        boardDisplays.getChildren().addAll(topBoardDisplay, bottomBoardDisplay);
        Button rightButton = new Button("RIGHT");
        rightButton.setOnAction((controller.new RightButtonHandler()));
        boardDisplay.getChildren().addAll(leftButton, boardDisplays, rightButton);

    }

    /**
     * Adds the central object that event handling will be routed to. In my program
     * I had nested classes that did the handling within the controller.
     * @param controller
     */
    public void addHandler(Controller controller){
        this.controller = controller;
    }

    /**
     * allows the GUI to change it's appearance to reflect any new information in the model.
     * @param model a copy of the model so the gui can display it's info
     * @param arg not used here, it's an artifact of the built in java library
     */
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
            DisplayDomino bone = new DisplayDomino(tile.getSide0(), tile.getSide1(), indexCounter, controller);
            if(tile.isSelected()){
                if(tile.getSelectedSide() == 0){
                    GraphicsContext gc = bone.side1.getGraphicsContext2D();
                    gc.setFill(Color.BLUE);
                    gc.strokeRect(2,2,36,36);
                } else if(tile.getSelectedSide() == 1){
                    GraphicsContext gc = bone.side2.getGraphicsContext2D();
                    gc.setFill(Color.BLUE);
                    gc.strokeRect(2,2,36,36);
                }
            }
            humanTray.getChildren().add(bone);
            indexCounter++;
        }
        Button passButton = new Button("PASS");
        passButton.setOnAction(controller.new PassButtonHandler());
        humanTray.getChildren().add(passButton);
    }

    /**
     * writes the message display
     * @param model
     */
    public void writeMessageDisplay(Model model){
        messageDisplay.getChildren().clear();
        messageDisplay.getChildren().add(new Label(model.getStateMessage()));
    }

    /**
     * draw board, the playing area.
     */
    public void drawBoard(ArrayList<Tile> board){
        topBoardDisplay.getChildren().clear();
        bottomBoardDisplay.getChildren().clear();
        bottomBoardDisplay.getChildren().add(new Canvas(40,40));
        int counter = 0;
        for(Tile tile: board){
            if(counter++%2 == 0) {
                topBoardDisplay.getChildren().add(new DisplayDomino(tile.getSide0(), tile.getSide1(), 0, controller));
            } else {
                bottomBoardDisplay.getChildren().add(new DisplayDomino(tile.getSide0(), tile.getSide1(), 0, controller));
            }
        }
    }
}
