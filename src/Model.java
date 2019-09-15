import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class Model extends Observable {
    private Board board;
    public HumanPlayer humanPlayer;
    public ComputerPlayer pcPlayer;
    public Player currentPlayer;
    private String stateMessage;
    private ArrayList<Tile> boneyard;

    public Model(){
        stateMessage = "SELECT WHICH MOVE YOU WANT TO MAKE BY CLICKING THE END OF THE TILE YOU ";
        stateMessage += "WANT TO USE. E.g. I want to use the 1 in [1 | 2], I'll click the 1 end";
        fillBoneyard();
        board = new Board();
        humanPlayer = new HumanPlayer(getNewHand());
        pcPlayer = new ComputerPlayer(getNewHand());
        currentPlayer = humanPlayer;
    }

    public void addView(GUI view){
        addObserver(view);
        setChanged();
        notifyObservers();
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer(){ return currentPlayer; }

    /**
     * @return the human player
     */
    public Player getHumanPlayer(){ return humanPlayer; }

    public ArrayList<Tile> getBoneyard() { return boneyard; }

    public Board getBoard(){ return board; }
    public String getStateMessage(){ return stateMessage; }

    /**
     * @return
     */
    public Player getPcPlayer(){ return pcPlayer; }

    public void setStateMessage(String message){
        stateMessage = message;
        setChanged();
        notifyObservers();
    }

    public void setSelectedTile(int index, int sideSelected){
        humanPlayer.setTileSelected(index, sideSelected);
        setChanged();
        notifyObservers();
    }

    public void setCurrentPlayer(){
        if(currentPlayer instanceof HumanPlayer) {
            currentPlayer = pcPlayer;
            setStateMessage("PC's turn... now drawing");
        } else currentPlayer = humanPlayer;
        setStateMessage("Human's Turn");
    }

    public void currentPlayerDraws(){
        while(!currentPlayer.hasMove(board.getPlayable0(), board.getPlayable1()) && !boneyard.isEmpty()){
            currentPlayer.takeTile(pullTile()); //player is given a random tile from the boneyard
            setChanged();
            notifyObservers();
        }
    }

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

    private void fillBoneyard(){
        boneyard = new ArrayList<Tile>();
        for (int i = 0; i < Main.STARTING_HAND_SIZE; i++){
            for (int j = 6; j >= i; j--){
                boneyard.add(new Tile(i,j));
            }
        }
    }

    public void addTile(Tile tile, int boardSidePlayed){
        board.addTile(tile, boardSidePlayed);
        setChanged();
        notifyObservers(this);
    }

    public void moveHuman(int boardSide){ //board side is 0 for left 1 for right
        int boardNumber = board.getPlayableNumbers()[boardSide];
        if(humanPlayer.canMove(boardNumber)){
            Tile playedTile = humanPlayer.pullSelectedTile();
            //flip tile if necessary
            if(boardSide == 0 && playedTile.getSelectedSide() == 0){
                playedTile.flip();
            } else if(boardSide == 1 && playedTile.getSelectedSide() == 1){
                playedTile.flip();
            }
            addTile(playedTile, boardSide);
            System.out.println("board " + board.getBoard());
        } else setStateMessage("That's not a valid move, try again");

    }

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
            System.out.println("board" + board.getBoard());
            setChanged();
            notifyObservers();
        } else {
            setStateMessage("PC Had no move");
            currentPlayer.setPassedTurn(true);
        }
    }

}
