package Chess.Gui;

import Chess.*;
import Chess.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ChessTable extends JFrame {
    /**
     * Létrehozza a sakk táblát, és elindítja a játék logikát.
     * A tábla mérete alapból 500x500, de ez a felhasználó álltal átméretezhető.
     * A sakkmező gombokból áll. Felette van egy időjelző szöveg. Melette pedig egy feladás gomb.
     */
    public ChessTable(){
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start new game");
        setVisible(true);

        JPanel timerPanel = new JPanel();
        JLabel timeMonitor = new JLabel();
        timerPanel.add(timeMonitor);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(8,8));

        Player whitePlayer = new Player(true);
        Player blackPlayer = new Player(false);

        GameController controller = new GameController(whitePlayer, blackPlayer, timeMonitor, this);

        JButton[][] fields = new JButton[8][8];
        for (int x = 0; x<8; x++){
            for(int y = 0; y<8; y=y+2){
                if(x%2==0){
                    fields[x][y] = new JButton();
                    fields[x][y].setBackground(Color.DARK_GRAY);
                    fields[x][y].addActionListener(FieldActionListener(new Field(x, y), controller));

                    fields[x][y+1] = new JButton();
                    fields[x][y+1].setBackground(Color.WHITE);
                    fields[x][y+1].addActionListener(FieldActionListener(new Field(x, y+1), controller));
                }else{
                    fields[x][y] = new JButton();
                    fields[x][y].setBackground(Color.WHITE);
                    fields[x][y].addActionListener(FieldActionListener(new Field(x, y), controller));

                    fields[x][y+1] = new JButton();
                    fields[x][y+1].setBackground(Color.DARK_GRAY);
                    fields[x][y+1].addActionListener(FieldActionListener(new Field(x, y+1), controller));
                }

                fieldPanel.add(fields[x][y]);
                fieldPanel.add(fields[x][y+1]);
            }
        }

        addPieces(fields, whitePlayer.getPiece());
        addPieces(fields, blackPlayer.getPiece());


        JButton giveUp = new JButton("Feladás");
        giveUp.addActionListener(listener->{
            controller.endGame();
        });
        giveUp.setPreferredSize(new Dimension(80, 100));
        JPanel giveUpPanel = new JPanel();
        giveUpPanel.setLayout(new BoxLayout(giveUpPanel, BoxLayout.Y_AXIS));
        giveUpPanel.add(Box.createVerticalGlue());
        giveUpPanel.add(giveUp);
        giveUpPanel.add(Box.createVerticalGlue());


        add(fieldPanel, BorderLayout.CENTER);
        add(timerPanel, BorderLayout.NORTH);
        add(giveUpPanel, BorderLayout.EAST);

        pack();
    }

    /**
     * Hozzáadja a játékmezőhöz a bábuk ikonjait. 16db bábut tud hozzáadni.
     * @param fields A tömb, amiben a játékmező gombjait tároljuk.
     * @param pieces Egy tömb, amiban a hozzáadandó bábuk vannak. Mérete 16 kell, hogy legyen
     */
    void addPieces(JButton[][] fields, Piece[] pieces){
        for(int i = 0; i<16;i++){
            fields[pieces[i].getLocation().getX()][pieces[i].getLocation().getY()].setIcon(pieces[i].getIcon());
        }
    }


    //

    /**
     * Egy mező gombjának a lenyomás figyelőjét hozza létre.
     * @param buttonField az adott gomb által reprezentált mező
     * @param controller a játék controllere (minden mező ugyan azt a referenciát kell, hogy kapja
     * @return egy actionlistener, ami a gomb lenyomására jelez a játékvezérlőnek
     */
    ActionListener FieldActionListener(Field buttonField, GameController controller){
        return e -> controller.Move(buttonField, (JButton) e.getSource());
    }
}
