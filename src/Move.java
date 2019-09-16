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

    /**
     * set values of our new move.
     * @param tileIndex
     * @param tileSide
     * @param boardSide
    */
    public Move(int tileIndex, int tileSide, int boardSide){
        this.tileIndex = tileIndex;
        this.dominoSide = tileSide;
        this.boardSide = boardSide;
    }

    /**
     * @return the index in the player's hand of the tile we're going to want to move
     */
    public int getTileIndex() { return tileIndex; }

    /**
     * @return the side of the domino we're planning on moving.
     */
    public int getDominoSide() { return dominoSide; }

    /**
     * @return the side of the board we're planning on placing our tile
     */
    public int getBoardSide() { return boardSide; }
}
