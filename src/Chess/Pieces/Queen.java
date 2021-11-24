package Chess.Pieces;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class Queen extends Piece{

	public Queen(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);

		if(super.location.getX() != hova.getX() && super.location.getY() != hova.getY()) {
			if(Math.abs(super.location.getX()-hova.getX()) != Math.abs(super.location.getY()-hova.getY())){ //�tl� ellen�rz�se
				return false;
			}
		}

		return true;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wqueen.png");
		}else{
			return new ImageIcon("./iconok/bqueen.png");
		}
	}
}
