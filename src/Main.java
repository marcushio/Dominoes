import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 *
 * This is a simple version of dominoes. Where you try to match any tiles in your hand with one of the two exposed ends.
 * Check README for full rules.
 *
 */
public class Main extends Application {
    public static final int STARTING_HAND_SIZE = 7;
    private Stage primaryStage;
    private GUI view;
    private Model model;
    private Controller controller;

    /**
     * Launches the program
     * @param args
     */
    public static void main(String[] args) { launch(args); }

    /**
     * Instantiates the objects needed for the program to run.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        model = new Model();
        controller = new Controller();
        controller.addModel(model);
        view = new GUI(primaryStage, controller);
        //view.addHandler(controller);
        model.addView(view);
    }
}
