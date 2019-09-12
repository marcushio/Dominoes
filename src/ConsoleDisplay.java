import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */

public class ConsoleDisplay {

    public void printBoard(ArrayList<Tile> board){
        if(board.isEmpty()){ System.out.println("Empty Board "); }
        System.out.println(board);
    }

    public void shiftBoard(){
        //make the display compatible with our shifted format

    }

}
