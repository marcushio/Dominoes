import java.util.ArrayList;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class Model extends Observable {
    private Board board;
    private Player humanPlayer;
    private Player pcPlayer;
    public Player currentPlayer;
    private String stateMessage;
    private ArrayList<Tile> boneyard;

    public Model(GUI view ){
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

    /**
     *
     * @return
     */
    public Player getPcPlayer(){ return pcPlayer; }

    public void setStateMessage(String message){
        stateMessage = message;
        setChanged();
        notifyObservers();
    }

    public String getStateMessage(){ return stateMessage; }

    public void nextPlayersTurn(){
        if(currentPlayer instanceof HumanPlayer) {
            currentPlayer = pcPlayer;
            setStateMessage("PC's turn... now drawing");
        } else currentPlayer = humanPlayer;

        while(!currentPlayer.hasMove(board.getPlayable1(), board.getPlayable2()) && !boneyard.isEmpty()){
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

    public void addTile(Tile tile, int sidePlayed){
        board.addTile(tile, sidePlayed );
        setChanged();
        notifyObservers();
    }

}
