package Chess.Gui;

import Chess.*;
import Chess.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class ChessTable extends JFrame {
    Boolean playerCount;

    public ChessTable(Boolean playerCount){
        this.playerCount = playerCount;

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start new game");
        setVisible(false);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(8,8));

        Player whitePlayer = new Player(true);
        Player blackPlayer = new Player(false);

        PlayerMoveTypes currentMove = PlayerMoveTypes.WhiteSelectPiece;

        JButton[][] fields = new JButton[8][8];
        for (int x = 0; x<8; x++){
            for(int y = 0; y<8; y=y+2){
                if(x%2==0){
                    fields[x][y] = new JButton();
                    fields[x][y].setBackground(Color.DARK_GRAY);
                    fields[x][y].addActionListener(new FieldActionListener(new Field(x, y), whitePlayer, blackPlayer, currentMove, fields[x][y]));

                    fields[x][y+1] = new JButton();
                    fields[x][y+1].setBackground(Color.WHITE);
                    fields[x][y+1].addActionListener(new FieldActionListener(new Field(x, y+1), whitePlayer, blackPlayer, currentMove, fields[x][y+1]));
                }else{
                    fields[x][y] = new JButton();
                    fields[x][y].setBackground(Color.WHITE);
                    fields[x][y].addActionListener(new FieldActionListener(new Field(x, y), whitePlayer, blackPlayer, currentMove, fields[x][y]));

                    fields[x][y+1] = new JButton();
                    fields[x][y+1].setBackground(Color.DARK_GRAY);
                    fields[x][y+1].addActionListener(new FieldActionListener(new Field(x, y+1), whitePlayer, blackPlayer, currentMove, fields[x][y+1]));
                }

                fieldPanel.add(fields[x][y]);
                fieldPanel.add(fields[x][y+1]);
            }
        }

        addPieces(fields, whitePlayer.getPiece());
        addPieces(fields, blackPlayer.getPiece());

        GameController controller = new GameController(whitePlayer, blackPlayer);

        add(fieldPanel);
    }

    void addPieces(JButton[][] fields, Piece[] pieces){
        for(int i = 0; i<16;i++){
            fields[pieces[i].getLocation().getY()][pieces[i].getLocation().getX()].setIcon(pieces[i].getIcon());
        }
    }
}
