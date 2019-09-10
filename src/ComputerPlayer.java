import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class ComputerPlayer extends Player {
    //private ArrayList<Tile> hand;

    public ComputerPlayer(ArrayList<Tile> hand){
        this.hand = hand;
    }

    /**
     * How the computer player chooses to move, he's kind of dumb right now. He just cycles through
     * seeing if any of the numbers on his tiles match the open ones on the board.
     * @param playables is the playable numbers on the board
     * @return the move he chose, if he has no moves, he returns null.
     */
    public Move move(int[] playables){
        int index = -1;
        int sidePlayed = -1;
        for(int i =0; i < hand.size(); i++){
            if(hand.get(i).getSide1() == playables[0]){
                index = i;
                sidePlayed = hand.get(i).getSide1();
            } else if (hand.get(i).getSide2() == playables[1]){
                index = i;
                sidePlayed = hand.get(i).getSide2();
            }
        }
        if(index == -1){
            setPassedTurn(true);
            return null;
        }
        setPassedTurn(false);
        return new Move(index, sidePlayed);
    }
}
