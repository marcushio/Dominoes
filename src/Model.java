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
    private Player currentPlayer;

    public Model(GUI view, HumanPlayer human, ComputerPlayer pc){
        board = new Board();
        humanPlayer = human;
        pcPlayer = pc;
        currentPlayer = humanPlayer;
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

    /**
     *
     * @return
     */
    public Player getPcPlayer(){ return pcPlayer; }

    public void setCurrentPlayer(){
        if(currentPlayer instanceof HumanPlayer) currentPlayer = pcPlayer;
        else currentPlayer = humanPlayer;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tile> getBoard(){ return board.getBoard(); }

    /**
     * takes the players move from the controller and updates the board accordingly.
     * @param move
     */
    public void update(Move move){

        setChanged();
        notifyObservers();
    }

}
