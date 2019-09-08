import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board {
    private ArrayList<Tile> boneyard;


    public void shuffleBones(){
        Collections.shuffle(boneyard);
    }

}
