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
    private ArrayList<Tile> boneyard;

    public Model(GUI view ){
        fillBoneyard();
        board = new Board();
        humanPlayer = new HumanPlayer(getNewHand());
        pcPlayer = new ComputerPlayer(getNewHand());
        currentPlayer = humanPlayer;
        /*
        addObserver(view);
        setChanged();
        notifyObservers();

         */
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

    public ArrayList<Tile> getBoneyard() {
        return boneyard;
    }

    /**
     *
     * @return
     */
    public Player getPcPlayer(){ return pcPlayer; }

    public void setCurrentPlayer(){
        if(currentPlayer instanceof HumanPlayer) currentPlayer = pcPlayer;
        else currentPlayer = humanPlayer;
        while(!currentPlayer.hasMove(board.getPlayable1(), board.getPlayable2()) && !boneyard.isEmpty()){
            currentPlayer.takeTile(pullTile()); //player is given a random tile from the boneyard
            //System.out.println("Tile Drawn... New Hand...  " + hand); this is for showing any players hand
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
    /**
     *
     * @return
     */
    public Board getBoard(){ return board; }

}
