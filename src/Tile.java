

public class Tile {
    int side1;
    int side2;

    public Tile(int side1, int side2){
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public String toString(){
        return "[ " + side1 + " | " + side2 + " ]";
    }

    public void flip(){
        int temp = side1;
        side1 = side2;
        side2 = temp;
    }

    public int getSide1(){ return side1; }

    public int getSide2(){ return side2; }
}
