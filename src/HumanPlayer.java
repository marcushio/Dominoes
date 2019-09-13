import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: 9/8/2019
 * Represents a human player.
 */
public class HumanPlayer extends Player {
    private InputGetter input;

    public HumanPlayer(ArrayList<Tile> hand){
        this.hand = hand;
        input = new InputGetter();
    }

    private boolean validMove(Move move, int[] playables){
        /*
        int[] playables = board.getPlayableNumbers();
        if(playables[1] = )*/
        return false;
    }

    //we're going to need some protections
    public Move move(int[] playables){
        if(hasMove(playables[0], playables[1])) {
            int tileIndex = Integer.parseInt(input.getStringInput("Which tile do you want to play?"));
            int playedSide = Integer.parseInt(input.getStringInput("Which number will you use on this tile?"));
            Move move = new Move(tileIndex, playedSide);
            setPassedTurn(false);
            return move;
        } else {
            setPassedTurn(true);
            return null;
        }

    }

}
