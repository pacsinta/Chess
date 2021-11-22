package Chess;

public class Field {
    int x, y;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Field(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void inverz() {
        this.x = 8-this.x;
        this.y = 8-this.y;
    }
}
