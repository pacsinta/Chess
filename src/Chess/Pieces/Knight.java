package Chess.Pieces;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class Knight extends Piece{

	public Knight(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);
		//ToDO
		return true;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wknight.png");
		}else{
			return new ImageIcon("./iconok/bknight.png");
		}
	}
}
