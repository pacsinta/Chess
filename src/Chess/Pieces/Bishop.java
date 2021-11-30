package Chess.Pieces;


import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Bishop extends Piece{

	public Bishop(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
	}

	@Override
	public Boolean checkMove(Field hova) {
		int xElteres = Math.abs(hova.getX()-this.location.getX());
		int yElteres = Math.abs(hova.getY()-this.location.getY());
		return xElteres == yElteres;
	}

	@Override
	public Field checkCollision(Field honnan, Field hova, Player player) {
		if(hova.getX()>honnan.getX()){
			for (int x = honnan.getX()+1; x <= hova.getX(); x++) {
				Field buffField = yFor(honnan, hova, player, x);
				if (buffField!=null) return buffField;
			}
		}else{
			for (int x = honnan.getX()-1; x >= hova.getX(); x--) {
				Field buffField = yFor(honnan, hova, player, x);
				if (buffField!=null) return buffField;
			}
		}

		return null;
	}

	private Field yFor(Field honnan, Field hova, Player player, int x) {
		if(hova.getY() > honnan.getY()){
			for (int y = honnan.getY()+1; y <= hova.getY(); y++) {
				if(player.checkPieceIsOnField(new Field(x, y))!=-1){
					return new Field(x, y);
				}
			}
		}else{
			for (int y = honnan.getY()-1; y >= hova.getY(); y--) {
				if(player.checkPieceIsOnField(new Field(x, y))!=-1){
					System.out.println("x: "+x+" y: "+y);
					return new Field(x, y);
				}
			}
		}
		return null;
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
