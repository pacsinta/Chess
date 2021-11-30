package Chess.Pieces;


import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Bishop extends Piece {

    public Bishop(Field kezdoHely, Boolean color) {
        super(kezdoHely, color);
    }

    @Override
    public Boolean checkMove(Field hova) {
        int xElteres = Math.abs(hova.getX() - this.location.getX());
        int yElteres = Math.abs(hova.getY() - this.location.getY());
        return xElteres == yElteres;
    }

    @Override
    public Field checkCollision(Field honnan, Field hova, Player player) {
        int elteres = Math.abs(hova.getY() - honnan.getY());

        if (hova.getX() > honnan.getX()) {
            if (hova.getY() > honnan.getY()) {
                for (int i = 1; i <= elteres; i++) {
                    if (player.checkPieceIsOnField(new Field(honnan.getX() + i, honnan.getY() + i)) != -1) {
                        return new Field(honnan.getX() + i, honnan.getY() + i);
                    }
                }
            }else{
                for (int i = 1; i <= elteres; i++) {
                    if (player.checkPieceIsOnField(new Field(honnan.getX() + i, honnan.getY() - i)) != -1) {
                        return new Field(honnan.getX() + i, honnan.getY() - i);
                    }
                }
            }

        } else {
            if (hova.getY() > honnan.getY()) {
                for (int i = 1; i <= elteres; i++) {
                    if (player.checkPieceIsOnField(new Field(honnan.getX() - i, honnan.getY() + i)) != -1) {
                        return new Field(honnan.getX() - i, honnan.getY() + i);
                    }
                }
            }else{
                for (int i = 1; i <= elteres; i++) {
                    if (player.checkPieceIsOnField(new Field(honnan.getX() - i, honnan.getY() - i)) != -1) {
                        return new Field(honnan.getX() - i, honnan.getY() - i);
                    }
                }
            }
        }

        return null;
    }


    @Override
    public Icon getIcon() {
        if (this.color) {
            return new ImageIcon("./iconok/wbishop.png");
        } else {
            return new ImageIcon("./iconok/bbishop.png");
        }
    }
}
