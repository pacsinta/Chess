package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public abstract class Piece {
    Field location;
    public Boolean color;
    public Boolean isAlive = true;

    /**
     * A bábu konstruktora
     * @param kezdoHely A bábu kezdőhelyét állítja be
     * @param color Ha igaz a bábu fehér színű, különben fekete.
     */
    public Piece(Field kezdoHely, Boolean color) {
        this.location = kezdoHely;
        this.color = color;
    }

    /**
     * A bábut deaktiválja
     */
    public void kick(){isAlive = false;}

    /**
     * Abstrakt metódus. Ellenőrzi, hogy egy adott mezőre léphet-e a bábu egy üres táblán.
     * @param hova A mező amire lépni akarunk.
     * @return igaz, ha lehet az adott mezore lepni; hamis, ha nem
     */
    public abstract Boolean checkMove(Field hova);

    /**
     * Megnézi, hogy van-e az útban bábu.
     * A kiindulási hely a bábu jelenlegi helyzete.
     * @param hova A mező amire lépni akarunk.
     * @param player A játékos akinek a bábuiba nem akarunk ütközni.
     * @return Igaz, ha nincs bábu az útban, hamis ha van.
     */
    public Boolean checkOwnCollision(Field hova, Player player){
        if(checkCollision(this.location, hova, player) != null){
            System.out.println();
        }
        return checkCollision(this.location, hova, player) == null;
    }

    /**
     * Megnézi, hogy ha egy adott helyről, egy adott helyre beleütközünk e egy adott játékos bábujába.
     *
     * @param honnan A kiindulási hely.
     * @param hova A cél hely.
     * @param player A játékos akinek a bábuiba nem akarunk ütközni.
     * @return Ha van az útban bábu, akkor a bábu helyzetét adja vissza, különben null-t.
     */
    public abstract Field checkCollision(Field honnan, Field hova, Player player);

    /**
     * Ha ki akarunk ütni egy ellenséges bábut, akkor meg kell néznünk, hogy az oda vezető úton a bábu előtti mezőig el tudunk e jutni.
     * @param hova A mező amin az ellenséges bábu helyezkedik.
     * @param other A másik játékos referenciája
     * @return Igaz, ha ki tudjuk ütni, hamis ha nem.
     * @throws Exception
     */
    public Boolean checkPreCollision(Field hova, Player other, Player player) throws Exception {
        if(!checkOwnCollision(hova, player)) return false;

        //Ha x vagy y tengely mentén akarunk mozogni
        if(hova.getX() == this.location.getX()){
            if(hova.getY()>this.location.getY()){
                return checkOwnCollision(new Field(hova.getX(), hova.getY()-1), other);
            }else{
                return checkOwnCollision(new Field(hova.getX(), hova.getY()+1), other);
            }
        }else if(hova.getY() == this.location.getY()){
            if(hova.getX()>this.location.getX()){
                return checkOwnCollision(new Field(hova.getX()-1, hova.getY()), other);
            }else{
                return checkOwnCollision(new Field(hova.getX()+1, hova.getY()), other);
            }
        }

        //Ha átlósan akarunk mozogni
        if(hova.getX()>this.location.getX()){
            if(hova.getY()>this.location.getY()){
                return checkOwnCollision(new Field(hova.getX()-1, hova.getY()-1), other);
            }else{
                return checkOwnCollision(new Field(hova.getX()-1, hova.getY()+1), other);
            }
        }else{
            if(hova.getY()>this.location.getY()){
                return checkOwnCollision(new Field(hova.getX()+1, hova.getY()-1), other);
            }else{
                return checkOwnCollision(new Field(hova.getX()+1, hova.getY()+1), other);
            }
        }
    }

    /**
     * Beállít egy új helyet
     * @param hova Az új hely értéke
     */
    public void setLocation(Field hova){
        this.location.setXY(hova.getX(), hova.getY());
    }
    public void move(Field hova, Player moving, Player notMoving)  throws Exception{
        if(checkMove(hova)){
            if(checkOwnCollision(hova, moving) && checkPreCollision(hova, notMoving, moving)){
                this.location.setXY(hova.getX(), hova.getY());
            }else{
                throw new Exception("Van útban bábu!");
            }
        }else{
            throw new Exception("Nem szabalyos lepes!");
        }
    }

    public Field getLocation() {
        return this.location;
    }

    public abstract Icon getIcon();
}
