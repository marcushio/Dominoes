import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */

public class ConsoleDisplay {

    public void printBoard(ArrayList<Tile> board){
        if(board.isEmpty()){ System.out.println("Empty Board "); }
        System.out.println("***BOARD****");
        System.out.println(board);
        System.out.println("*******");
    }

    public void shiftBoard(){
        //make the display compatible with our shifted format
        //shouldn't be too bad just read through list and alternate which line it prints on.

    }

}
