import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 * The domino that will be displayed in the view, it's composed of two canvases.
 */
public class DisplayDomino extends HBox {
    private final int WIDTH = 40, HEIGHT = 40, CENTERX = WIDTH/2, CENTERY = HEIGHT/2;
    Canvas side1, side2;
    int index = -1;

    public DisplayDomino(int leftSide, int rightSide, int index, Controller handler){
        this.index = index;
        side1 = paintSide(leftSide, 1);
        side1.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        side2 = paintSide(rightSide, 2);
        side2.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        this.getChildren().addAll(side1, side2);
    }

    private Canvas paintSide(int dots, int side){
        Canvas dominoSide = new Canvas(WIDTH, HEIGHT );
        GraphicsContext gc = dominoSide.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        switch (dots) {
            case (0):
                break;
            case(1):
                gc.fillOval(CENTERX,CENTERY,5,5); //center
                break;
            case (2):
                gc.fillOval(WIDTH/3 ,CENTERY,5,5);
                gc.fillOval((2*WIDTH)/3,CENTERY,5,5);
                break;
            case(3):
                gc.fillOval(CENTERX,CENTERY,5,5);
                gc.fillOval(WIDTH/4,HEIGHT/4,5,5);
                gc.fillOval(30,30,5,5);
                break;
            case (4):
                gc.fillOval(10,10,5,5);
                gc.fillOval(10,30,5,5);
                gc.fillOval(30,10,5,5);
                gc.fillOval(30, 30,5,5 );
                break;
            case(5):
                gc.fillOval(20,20,5,5);
                gc.fillOval(10,10,5,5);
                gc.fillOval(10,30,5,5);
                gc.fillOval(30,10,5,5);
                gc.fillOval(30, 30,5,5 );
                break;
            case(6):
                gc.fillOval(10,10,5,5);
                gc.fillOval(10,30,5,5);
                gc.fillOval(30,10,5,5);
                gc.fillOval(30, 30,5,5 );
                gc.fillOval(10,20,5,5);
                gc.fillOval(30,20,5,5);
                break;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,WIDTH,HEIGHT);
        if(side == 1) gc.strokeLine(WIDTH,0,WIDTH,HEIGHT);
        else if(side == 2) gc.strokeLine(1,0,1,40);
        return dominoSide;
    }
}
