package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Pawn extends Piece {
    public Pawn(Field kezdoHely, Boolean color) {
        super(kezdoHely, color);
    }

    @Override
    public Boolean checkMove(Field hova) {
        if (hova.getX() != this.location.getX()) {
            return false;
        }

        if (color) {
            if (hova.getY() - this.location.getY() > 2 || hova.getY() - this.location.getY() < 1) {
                return false;
            }
        } else {
            if (this.location.getY() - hova.getY() > 2 || this.location.getY() - hova.getY() < 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Field checkCollision(Field honnan, Field hova, Player player) {
        if (color) {
            for (int y = honnan.getY() + 1; y <= hova.getY(); y++) {
                if (testY(honnan, player, y)) return new Field(honnan.getX(), y);
            }
        } else {
            for (int y = honnan.getY() - 1; y >= hova.getY(); y--) {
                if (testY(honnan, player, y)) return new Field(honnan.getX(), y);
            }
        }
        return null;
    }

    @Override
    public Boolean checkPreCollision(Field hova, Player other) throws Exception {
        throw new Exception("Gyalognál nem használható ez a metódus!");
    }

    public Boolean testCheck(Field field){
        int elteres_X = Math.abs(this.location.getX() - field.getX());
        int elteres_Y = this.location.getY() - field.getY();

        if (this.color) {
            if (elteres_Y == -1 && elteres_X == 1) {
                System.out.println("x: "+elteres_X);
                System.out.println("Y: "+elteres_Y);
                return true;
            }
        } else {
            if (elteres_Y == 1 && elteres_X == 1) {
                System.out.println("x: "+elteres_X);
                System.out.println("Y: "+elteres_Y);
                return true;
            }
        }
        return false;
    }

    private boolean testY(Field honnan, Player player, int y) {
        for (int i = 0; i < 16; i++) {
            if (player.getPiece()[i].isAlive && player.getPiece()[i].getLocation().getX() == honnan.getX() && player.getPiece()[i].getLocation().getY() == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Icon getIcon() {
        if (this.color) {
            return new ImageIcon("./iconok/wpawn.png");
        } else {
            return new ImageIcon("./iconok/bpawn.png");
        }
    }

    @Override
    public void move(Field field, Player moving, Player notMoving) throws Exception {
        throw new Exception("Ez a fuggveny gyalognal nem hasznalhato!");
    }

    public void specialMove(Field hova, Player moveing, Player notMoving) throws Exception {
        if (checkMove(hova) && checkOwnCollision(hova, moveing) && checkOwnCollision(hova, notMoving)) {
            this.location.setXY(hova.getX(), hova.getY());
            return;
        }

        int elteres_X = Math.abs(this.location.getX() - hova.getX());
        int elteres_Y = this.location.getY() - hova.getY();

        if (this.color) {
            if (elteres_Y == -1 && elteres_X == 1) {
                this.location.setXY(hova.getX(), hova.getY());
                return;
            }
        } else {
            if (elteres_Y == 1 && elteres_X == 1) {
                this.location.setXY(hova.getX(), hova.getY());
                return;
            }
        }

        throw new Exception("Nem szabalyos lepes!");
    }
}
