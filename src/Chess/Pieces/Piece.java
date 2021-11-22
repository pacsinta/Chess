package Chess.Pieces;

import Chess.Field;

public abstract class Piece {
    Field location;
    Boolean isLive = true;

    public Piece(Field kezdoHely) {
        this.location = kezdoHely;
    }
    public abstract void move(Field hova)  throws IncorrectMoveException;
    public void kick() {
        isLive = false;
    }

    public Field getHely() {
        return this.location;
    }

    public void elteroMezo(Field hova) throws IncorrectMoveException {
        if(location.getX() == hova.getX() && location.getY() == hova.getY()) {
            throw new IncorrectMoveException("Nem lehet ugyan oda lepni!");
        }
    }
}
