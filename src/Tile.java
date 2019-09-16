

public class Tile {
    private int side0;
    private int side1;
    private boolean isSelected;
    private int selectedSide;

    public Tile(int side0, int side1){
        this.side0 = side0;
        this.side1 = side1;
    }

    @Override
    public String toString(){
        return "[ " + side0 + " | " + side1 + " ]";
    }

    public void flip(){
        int temp = side0;
        side0 = side1;
        side1 = temp;
    }

    public void setIsSelected(boolean isSelected){ this.isSelected = isSelected; }

    public void setSideSelected(int sideSelected){ this.selectedSide = sideSelected; }

    public int getValueOfSideSelected(){
        if (selectedSide == 0) return side0;
        else if (selectedSide == 1) return side1;
        return -1;
    }

    public int getSelectedSide(){return selectedSide; }

    public boolean isSelected(){ return isSelected; }

    public int getSide0(){ return side0; }

    public int getSide1(){ return side1; }

    public int getSideValue(int side){
        int sideValue = -1;
        if (side == 0 ) sideValue = side0;
        else if (side == 1) sideValue = side1;
        return sideValue;
    }
}
