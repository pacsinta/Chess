package Chess.Pieces;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public abstract class Piece {
    Field location;
    Boolean isLive = true;
    Boolean color;

    public Piece(Field kezdoHely, Boolean color) {
        this.location = kezdoHely;
        this.color = color;
    }

    public abstract Boolean checkMove(Field hova) throws IncorrectMoveException;
    public void move(Field hova)  throws IncorrectMoveException{
        if(checkMove(hova)){
            this.location.setXY(hova.getX(), hova.getY());
        }else{
            throw new IncorrectMoveException("Nem szabalyos lepes!");
        }
    }


    public void kick() {
        isLive = false;
    }

    public Field getLocation() {
        return this.location;
    }

    public void elteroMezo(Field hova) throws IncorrectMoveException {
        if(location.getX() == hova.getX() && location.getY() == hova.getY()) {
            throw new IncorrectMoveException("Nem lehet ugyan oda lepni!");
        }
    }
    public abstract Icon getIcon();
}
