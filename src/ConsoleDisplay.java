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

    public void printHand(ArrayList<Tile> hand){
        System.out.println("Your hand... \n" + hand);
    }

    public void printTilePlayed(Tile movedTile){
        System.out.println("player played tile: " + movedTile);
    }

    public void printWinner(int humanPoints, int pcPoints){
        System.out.println("GAME OVER");
        System.out.println("xX Final Score Xx \n You: " + humanPoints + " PC: "+ pcPoints);
        if(humanPoints < pcPoints) System.out.println("A WINNER IS YOU!");
        else if(humanPoints > pcPoints ) System.out.println("PC WINS, THE MACHINES ARE TAKING OVER!");
        else System.out.println("YOUR MIGHTS ARE EVENLY MATCHED, TIE GAME");
    }


}
