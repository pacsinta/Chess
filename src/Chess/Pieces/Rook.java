package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Rook extends Piece{

	public Rook(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) {
		if(hova.getX() != this.location.getX() && hova.getY() != this.location.getY()) {
			return false;
		}

		return true;
	}

	@Override
	public Field checkCollision(Field honnan, Field hova, Player player) {
		for (int i = 0; i < 16; i++) {
			if(hova.getX() == honnan.getX()){
				for (int y = honnan.getY()+1; y <= hova.getY(); y++) {
					if(player.getPiece()[i].location.isEqual(new Field(hova.getX(), y))){
						return new Field(hova.getX(), y);
					}
				}
				for (int y = honnan.getY()-1; y >= hova.getY(); y--) {
					if(player.getPiece()[i].location.isEqual(new Field(hova.getX(), y))){
						return new Field(hova.getX(), y);
					}
				}
			}else{
				for (int x = honnan.getX()+1; x <= hova.getX(); x++) {
					if(player.getPiece()[i].location.isEqual(new Field(x, hova.getY()))){
						return new Field(x, hova.getY());
					}
				}
				for (int x = honnan.getX()-1; x >= hova.getX(); x--) {
					if(player.getPiece()[i].location.isEqual(new Field(x, hova.getY()))){
						return new Field(x, hova.getY());
					}
				}
			}
		}
		return null;
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
