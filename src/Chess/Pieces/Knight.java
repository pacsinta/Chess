package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Knight extends Piece {

    public Knight(Field kezdoHely, Boolean color) {
        super(kezdoHely, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Boolean checkMove(Field hova) {
        int x_elteres = Math.abs(hova.getX()-this.location.getX());
        int y_elteres = Math.abs(hova.getY()-this.location.getY());

        return (x_elteres == 1 && y_elteres == 2) || (x_elteres == 2 && y_elteres == 1);
    }

    @Override
    public Field checkCollision(Field honnan, Field hova, Player player) {
        return null; //lóval át lehet ugrani a saját bábukat
    }

    @Override
    public Icon getIcon() {
        if (this.color) {
            return new ImageIcon("./iconok/wknight.png");
        } else {
            return new ImageIcon("./iconok/bknight.png");
        }
    }
}
