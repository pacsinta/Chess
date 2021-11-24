package Chess;

import Chess.Exceptions.IncorrectMoveException;
import Chess.Pieces.*;

import javax.swing.*;

public class Player {
    Piece[] piece;
    int selectedPiece;

    public Player(Boolean color) {
        piece = new Piece[16];

        piece[0] = new Rook(new Field(0, 0), color);
        piece[1] = new Knight(new Field(1, 0), color);
        piece[2] = new Bishop(new Field(2, 0), color);
        piece[3] = new Queen(new Field(3, 0), color);
        piece[4] = new King(new Field(4, 0), color);
        piece[5] = new Bishop(new Field(5, 0), color);
        piece[6] = new Knight(new Field(6, 0), color);
        piece[7] = new Rook(new Field(7, 0), color);

        for(int i = 0; i<8; i++) {
            piece[i+8] = new Pawn(new Field(i, 1), color);
        }

        if(!color) {
            for(int i = 0; i<16; i++) {
                piece[i].getLocation().inverz();
            }
        }
    }

    public Piece[] getPiece(){
        return piece;
    }

    public void move(Field field) throws IncorrectMoveException {
        piece[selectedPiece].move(field);
    }

    public Boolean selectPiece(Field field){
        int i = 0;
        while(i<16){
            if(piece[i].getLocation().isEqual(field)){
                selectedPiece = i;
                return true;
            }
            i++;
        }
        return false;
    }
}
