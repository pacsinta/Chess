package Chess;

public class Field {
    private int x, y;

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
        this.x = 7-this.x;
        this.y = 7-this.y;
    }

    public Boolean isEqual(Field field){
        if(field.getX() == this.x && field.getY() == this.y){
            return true;
        }
        return false;
    }
}
