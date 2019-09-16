import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/13/2019
 * An observable model of the data of the game. It includes both players and their hands, as well as the board,
 * boneyard and a message about the state of the game.
 */
public class Model extends Observable {
    private Board board;
    public HumanPlayer humanPlayer;
    public ComputerPlayer pcPlayer;
    public Player currentPlayer;
    private String stateMessage;
    private ArrayList<Tile> boneyard;

    /**
     * Set our initial stateMessage to user and start on human's turn.
     */
    public Model(){
        stateMessage = "MOVE BY CLICKING THE END OF THE TILE YOU WANT TO USE ";
        stateMessage += "THEN SELECT WHICH END OF THE BOARD YOU WANT TO PLAY IT ON";
        fillBoneyard();
        board = new Board();
        humanPlayer = new HumanPlayer(getNewHand());
        pcPlayer = new ComputerPlayer(getNewHand());
        currentPlayer = humanPlayer;
    }
    /**
     * Add an observer, specifically a view that displays the information of the model
     * @param view
     */
    public void addView(GUI view){
        addObserver(view);
        setChanged();
        notifyObservers();
    }
    /**
     * get's an entirely new hand of 7 tiles.
     * @return ArrayList<Tile> with 7 new tiles.
     */
    public ArrayList<Tile> getNewHand(){
        ArrayList<Tile> newHand = new ArrayList<Tile>();
        for(int i=0; i < 7; i++){
            Tile pulledTile = pullTile();
            newHand.add(pulledTile);
        }
        return newHand;
    }
    /**
     * Pull a random tile from the boneyard.
     * @return the tile that you pulled from the boneyard.
     */
    private Tile pullTile(){
        int random = (int)(Math.random() * boneyard.size());
        Tile pulledTile = boneyard.remove(random);
        return pulledTile;
    }

    /**
     * fills the boneyard with all the bones. Tiles are pulled randomly so this is not shuffled.
     */
    private void fillBoneyard(){
        boneyard = new ArrayList<Tile>();
        for (int i = 0; i < Main.STARTING_HAND_SIZE; i++){
            for (int j = 6; j >= i; j--){
                boneyard.add(new Tile(i,j));
            }
        }
    }

    /**
     * Adds a tile to the board. Observers are notified of the change
     * @param tile
     * @param boardSidePlayed 0 if the we're playing this on the left side of the board 1 for the right side
     */
    public void addTile(Tile tile, int boardSidePlayed){
        board.addTile(tile, boardSidePlayed);
        setChanged();
        notifyObservers(this);
    }

    /**
     * Takes the tile that's selected in player's hand, flips it if necessary, and adds it to the board.
     * @param boardSide the side of the board we're going to play on, 0 for left 1 for right
     */
    public void moveHuman(int boardSide){ //board side is 0 for left 1 for right
        int boardNumber = board.getPlayableNumbers()[boardSide]; //the number we'll be playing off of
        if(humanPlayer.canMove(boardNumber)){
            Tile playedTile = humanPlayer.pullSelectedTile();
            //flip tile if necessary
            if(boardSide == 0 && playedTile.getSelectedSide() == 0){
                playedTile.flip();
            } else if(boardSide == 1 && playedTile.getSelectedSide() == 1){
                playedTile.flip();
            }
            addTile(playedTile, boardSide);
        } else setStateMessage("That's not a valid move, try again");
    }

    /**
     * Has the pc make it's move using it's trademark algorithm (jk it's really simple) if a move
     * is impossible the pc passes.
     */
    public void pcMoves(){
        currentPlayerDraws();
        Move currentMove = pcPlayer.move(getBoard().getPlayableNumbers());
        if(currentMove != null) {
            Tile playedTile = pcPlayer.removeTileFromHand(currentMove.getTileIndex());
            if(currentMove.boardSide == 0 && currentMove.dominoSide == 0){
                playedTile.flip();
            } else if(currentMove.boardSide == 1 && currentMove.dominoSide == 1){
                playedTile.flip();
            }
            addTile(playedTile, currentMove.boardSide);
            setChanged();
            notifyObservers();
        } else {
            setStateMessage("PC Had no move");
            currentPlayer.setPassedTurn(true);
        }
    }

    /**
     * The current player draws until they either have a move to make or the boneyard is empty.
     * Any observers are notified of the change to the players hand.
     */
    public void currentPlayerDraws(){
        while(!currentPlayer.hasMove(board.getPlayable0(), board.getPlayable1()) && !boneyard.isEmpty()){
            currentPlayer.takeTile(pullTile()); //player is given a random tile from the boneyard
            setChanged();
            notifyObservers();
        }
    }

    //below are all getters and setters
    /**
     * @return the human player
     */
    public Player getHumanPlayer(){ return humanPlayer; }
    /**
     * @return the boneyard
     */
    public ArrayList<Tile> getBoneyard() { return boneyard; }
    /**
     * @return the board
     */
    public Board getBoard(){ return board; }
    /**
     * @return the stateMessage communicating what's happening on the board.
     */
    public String getStateMessage(){ return stateMessage; }
    /**
     * @return the pcPlayer
     */
    public Player getPcPlayer(){ return pcPlayer; }

    /**
     * set the stateMessage communicating what's happening on the boar
     * @param message
     */
    public void setStateMessage(String message){
        stateMessage = message;
        setChanged();
        notifyObservers();
    }
    /**
     * call's player to set which bone in their hand is going to be selected.
     * @param index
     * @param sideSelected
     */
    public void setSelectedTile(int index, int sideSelected){
        humanPlayer.setTileSelected(index, sideSelected);
        setChanged();
        notifyObservers();
    }
    /**
     * Toggles from the current player to the other player, and set's a message letting the players know.
     */
    public void setCurrentPlayer(){
        if(currentPlayer instanceof HumanPlayer) {
            currentPlayer = pcPlayer;
            setStateMessage("PC's turn... now drawing");
        } else currentPlayer = humanPlayer;
        setStateMessage("Human's Turn");
    }
}
