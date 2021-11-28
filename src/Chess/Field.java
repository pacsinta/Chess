package Chess;

public class Field {
    private int x, y;

    /**
     * @return Visszaadja az x értékét
     */
    public int getX() {
        return x;
    }

    /**
     * @return Visszaadja az y értékét
     */
    public int getY() {
        return y;
    }

    /**
     * Átállítja az x, y értékét egy új értékre.
     * @param x Az x új értéke
     * @param y Az y új értéke
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A Field osztály konstruktora
     * Beállítja az x, y értékét.
     * @param x Az x értéke
     * @param y Az y értéke
     */
    public Field(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Megforgatja egy mező helyzetét a sakktáblára átlósan tükrözve.
     */
    public void inverz() {
        this.x = 7-this.x;
        this.y = 7-this.y;
    }

    /**
     * 2 Mező értékét hasonlítja össze
     * @param field A mező, amihez össze akarjuk hasonlítani.
     * @return Igaz, ha a 2 mező helye megegyezik, különben hamis
     */
    public Boolean isEqual(Field field){
        if(field.getX() == this.x && field.getY() == this.y){
            return true;
        }
        return false;
    }
}
