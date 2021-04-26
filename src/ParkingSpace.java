public class ParkingSpace {
    private int x;
    private int y;
    private int index;
    private boolean occupied;

    public ParkingSpace(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.index = index;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean getOccupied() {
        return this.occupied;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return  this.x + " " + this.y ;
    }
}

