/**
 * @author: Marcus Trujillo
 * @version:9/6/2019
 * Encapsulates a players move. It gives information about which tile their playing and how they are using it by
 * specifying which side they are using to play off the board.
 */
public class Move {
    public int tileIndex; //index in players hand
    public int dominoSide;//0 is the left 1 is the right
    public int boardSide; //0 is the left 1 is the right

    public Move(int tileIndex, int tileSide, int boardSide){
        this.tileIndex = tileIndex;
        this.dominoSide = tileSide;
        this.boardSide = boardSide;
    }

    public int getTileIndex() { return tileIndex; }

    public int getDominoSide() { return dominoSide; }

    public int getBoardSide() { return boardSide; }
}
