/**
 * @author: Marcus Trujillo
 * @version:
 * Encapsulates a players move. It gives information about which tile their playing and how they are using it by
 * specifying which side they are using to play off the board.
 */
public class Move {
    public int tileIndex;
    public int playedSide;
    public int openSide;

    public Move(int tileIndex, int playedSide){
        this.tileIndex = tileIndex;
        this.playedSide = playedSide;
        //this.openSide = openSide;
    }

    public int getTileIndex() { return tileIndex; }

    public int getPlayedSide() { return playedSide; }
}
