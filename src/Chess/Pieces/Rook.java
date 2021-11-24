package Chess.Pieces;


import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class Rook extends Piece{

	public Rook(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);

		if(hova.getX() != this.location.getX() && hova.getY() != this.location.getY()) {
			return false;
		}

		return true;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wrook.png");
		}else{
			return new ImageIcon("./iconok/brook.png");
		}
	}
}
