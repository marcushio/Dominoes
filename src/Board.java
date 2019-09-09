import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.Observable;

/**
 * @author: Marcus Trujillo
 * @version: 9/5/2019
 */
public class Board extends Observable {
    public ArrayList<Tile> boneyard;
    private int playable1 = 0, playable2 = 0;

    public Board(){
        boneyard = new ArrayList<Tile>();
        for (int i = 0; i < 7; i++){
            for (int j = 6; j >= i; j--){
                boneyard.add(new Tile(i,j));
                System.out.println("this is tile " + i +"," + j);
                System.out.println(boneyard.size());
            }
        }
        shuffleBones();
    }

    //add protection from pulling non-existent tile
    public Tile pullTile(int index){
        Tile pulledTile = boneyard.remove(index);
        return pulledTile;
    }

    public void shuffleBones(){
        Collections.shuffle(boneyard);
    }

}
