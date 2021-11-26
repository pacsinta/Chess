package Chess.Pieces;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class Pawn extends Piece{
	public Pawn(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);

		if(hova.getX() != this.location.getX()) {
			return false;
		}

		if(color) {
			if(hova.getY()-this.location.getY() > 2 || hova.getY()-this.location.getY() < 1) {
				return false;
			}
		}else {
			System.out.println("ok");
			if(this.location.getY()-hova.getY() > 2 || this.location.getY()-hova.getY() < 1) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wpawn.png");
		}else{
			return new ImageIcon("./iconok/bpawn.png");
		}
	}

}
