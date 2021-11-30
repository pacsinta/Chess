package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class King extends Piece{
	public King(Field kezdohely, Boolean color) {
		super(kezdohely, color);
	}

	@Override
	public Boolean checkMove(Field hova) {
		int elteres_X = Math.abs(this.location.getX()-hova.getX());
		int elteres_Y = Math.abs(this.location.getY()-hova.getY());

		System.out.println("x: "+elteres_X);
		System.out.println("Y: "+elteres_Y);
		return elteres_X <= 1 && elteres_Y <= 1;
	}

	@Override
	public Field checkCollision(Field honnan, Field hova, Player player) {
		for (int i = 0; i < 16; i++) {
			if(player.getPiece()[i].getLocation().isEqual(hova)){
				return hova;
			}
		}
		return null;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wking.png");
		}else{
			return new ImageIcon("./iconok/bking.png");
		}
	}
}
