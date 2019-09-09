/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 *
 * This is a simple version of dominoes. Check README for full rules.
 * Main handles most of the "game level" logic such as manipulating tiles and placing them on the board, as well as
 * checking for a win condition.
 */
public class Main {
    private Board board;
    private Player player1;
    private boolean running;

    public Main(){
        player1 = new HumanPlayer();
        running = true;
    }

    private Tile pullTile(){
        int random = (int)(Math.random() * Constants.numTiles);
        Tile pulledTile = board.pullTile(random);
        return pulledTile; 
    }

    public static void main(String[] args) {
        Main controller = new Main();
        while(controller.running){

        }

    }
}
