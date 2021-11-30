package Chess;

import Chess.Pieces.*;

import javax.swing.*;
import java.util.Random;

public class Player {
    Piece[] piece;
    int selectedPiece;

    /**
     * A játékos konstruktora. Beállítja a bábukat (szineit/helyzetüket)
     * @param color Ha igaz akkor a játékos fehér színű bábukat tartalmaz. Ha hamis akkor feketéket.
     */
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

        //A fekete bábuk a tábla túloldalán vannak
        if(!color) {
            for(int i = 0; i<16; i++) {
                piece[i].getLocation().inverz();
            }
        }
    }

    /**
     * @return Visszaadja a játékos bábuinak referenciáját.
     */
    public Piece[] getPiece(){
        return piece;
    }

    /**
     * Random mozgat egy bábut
     */
    public void randomMove(){
        Random rand = new Random();
        int pieceNumber = rand.nextInt(16);
        while(!piece[pieceNumber].isAlive){
            pieceNumber = rand.nextInt(16);
        }

        //ToDo
    }

    Field previousField; //Mozgatás előtti mező értéke

    /**
     * Elmozgatja a kiválasztott bábut egy adott mezőre.
     * Csak akkor mozgat, ha van kiválasztva bábu.
     * Elmenti a bábu mozgatás előtti helyzetét.
     *
     * @param field A mozgatás utáni mező értéke
     * @param other A másik játékos referenciája.
     * @throws Exception Ha nincs kijelölve bábu, akkor hibát dob.
     */
    public void move(Field field, Player other) throws Exception {
        if(selectedPiece>-1){
            previousField = new Field(piece[selectedPiece].getLocation().getX(), piece[selectedPiece].getLocation().getY());
            if(piece[selectedPiece] instanceof Pawn){
                ((Pawn) piece[selectedPiece]).specialMove(field, this, other);
            }else{
                piece[selectedPiece].move(field, this, other);
            }
        }else {
            throw new Exception("Nincs kijelölve bábu!");
        }
    }

    /**
     * Visszaállítja a bábut egy mozgatással korábbra
     */
    public void moveBack(){
        piece[selectedPiece].setLocation(previousField);
    }

    /**
     * Megnézi,hogy a játékos tudna e ütni egy adott mezőt.
     * @param field A mező, amit tesztelni szeretnénk.
     * @param other A másik játékos refernciája.
     * @return Ha tudjuk ütni, akkor igaz, különben hamis.
     * @throws Exception
     */
    public Boolean testCheck(Field field, Player other) throws Exception {
        for (int i = 0; i < 16; i++) {
            if(piece[i].isAlive){
                if(piece[i] instanceof Pawn){
                    if(((Pawn) piece[i]).testCheck(field)){
                        return true;
                    }
                }else if(piece[i].checkMove(field) && piece[i].checkOwnCollision(field, this) && piece[i].checkOtherCollision(field, other)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Visszaadja egy adott mezőn lévő bábu Iconját
     * @param field A kiválasztott mező
     * @return
     * @throws Exception
     */
    public Icon getIconByField(Field field) throws Exception {
        for(int i = 0; i<16; i++){
            if(piece[i].getLocation().isEqual(field)){
                return piece[i].getIcon();
            }
        }
        throw new Exception("Nincs bábu az adott mezőn");
    }

    /**
     * Egy bábut választ ki, mező alapján.
     * @param field A mező amiről a bábut akarjuk választani.
     */
    public void selectPiece(Field field){
        selectedPiece = checkPieceIsOnField(field);
    }

    /**
     * Megnézi, hogy áll-e saját (aktív) bábu egy adott mezőn.
     * @param field A mező, amit nézünk.
     * @return A bábu sorszáma, ha van bábu az adott mezőn, különben -1.
     */
    public int checkPieceIsOnField(Field field){
        int i = 0;
        while(i<16){
            if(piece[i].getLocation().isEqual(field)){
                if(piece[i].isAlive){
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    /**
     * Töröljük a bábu kiválasztást
     */
    public void cleanSelect(){selectedPiece = -1;}

    /**
     * Töröljük a kiválasztott bábut.
     */
    public void kickSelected(){
        piece[selectedPiece].kick();
    }
}
