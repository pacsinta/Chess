package Chess.Pieces;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class King extends Piece{
	Boolean sakk = false;
	
	public King(Field kezdohely, Boolean color) {
		super(kezdohely, color);
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);


		int elteres_X = Math.abs(super.location.getX()-hova.getX());
		int elteres_Y = Math.abs(super.location.getY()-hova.getY());

		if(elteres_X != 1) {
			if(elteres_Y != 1) {
				return false;
			}
		}else if(elteres_Y != 1) {
			if(elteres_X != 1) {
				return false;
			}
		}

		// TODO utes ellenerzes
		return true;
	}

	@Override
	public void move(Field hova) throws IncorrectMoveException {

		
		
		this.location.setXY(hova.getX(), hova.getY());
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
