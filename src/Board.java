import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board extends Observable {
    private int playable1 = 0, playable2 = 0;

    public void updateBoard(){

    }

    /**
     * @return an array of size 2 with both of the boards exposed numbers that can be played off of.
     */
    public int[] getPlayableNumbers(){
        int[] playables = new int[2];
        playables[0] = playable1;
        playables[1] = playable2;
        return playables;
    }
}
