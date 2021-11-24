package Chess.Pieces;


import Chess.Exceptions.IncorrectMoveException;
import Chess.Field;

import javax.swing.*;

public class Bishop extends Piece{

	public Bishop(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) throws IncorrectMoveException {
		this.elteroMezo(hova);

		return true;
	}

	@Override
	public Icon getIcon() {
		if(this.color){
			return new ImageIcon("./iconok/wbishop.png");
		}else{
			return new ImageIcon("./iconok/bbishop.png");
		}
	}
}
