package Chess.Pieces;

import Chess.Field;
import Chess.Player;

import javax.swing.*;

public class Queen extends Piece{

	public Queen(Field kezdoHely, Boolean color) {
		super(kezdoHely, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean checkMove(Field hova) {
		if(this.location.getX() != hova.getX() && this.location.getY() != hova.getY()) {
			if(Math.abs(super.location.getX()-hova.getX()) != Math.abs(super.location.getY()-hova.getY())){ //�tl� ellen�rz�se
				return false;
			}
		}

		return true;
	}

	@Override
	public Field checkCollision(Field honnan, Field hova, Player player) {
		Bishop buffBishop = new Bishop(honnan, this.color);
		Rook buffRook = new Rook(honnan, this.color);

		System.out.println("Hova2: "+hova.getX()+" "+hova.getY());
		System.out.println("Honnan2: "+honnan.getX()+" "+honnan.getY());
		if(honnan.getX()==hova.getX() || honnan.getY() == hova.getY()){
			return buffRook.checkCollision(honnan, hova, player);
		}
		return buffBishop.checkCollision(honnan, hova, player);
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
