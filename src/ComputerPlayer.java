import java.util.ArrayList;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class ComputerPlayer extends Player {
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
        setPassedTurn(false);

        System.out.println("Computer making move");
       // System.out.println("computer's hand: " + hand); //this is just for debugging, will not show pc hand
        for(int i =0; i < hand.size(); i++){
            if(hand.get(i).getSide1() == playables[0] || hand.get(i).getSide1() == 0){
                index = i;
                sidePlayed = hand.get(i).getSide1();
                return new Move(index, sidePlayed);
            } else if (hand.get(i).getSide1() == playables[1]){
                index = i;
                sidePlayed = hand.get(i).getSide1();
                return new Move(index, sidePlayed);
            } else if (hand.get(i).getSide2() == playables[0] || hand.get(i).getSide2() == 0){
                index = i;
                sidePlayed = hand.get(i).getSide2();
                return new Move(index, sidePlayed);
            }else if (hand.get(i).getSide2() == playables[1]) {
                index = i;
                sidePlayed = hand.get(i).getSide2();
                return new Move(index, sidePlayed);
            } else if (playables[0] == 0){
                index = i;
                sidePlayed = hand.get(i).getSide2();
                return new Move(index, sidePlayed);
            } else if (playables[1] == 0){
                index = i;
                sidePlayed = hand.get(i).getSide1();
                return new Move(index, sidePlayed);
            }
        }
        if(index == -1){
            System.out.println("computer has no move");
            setPassedTurn(true);
            return null;
        }
        setPassedTurn(false);
        return new Move(index, sidePlayed);
    }
}
