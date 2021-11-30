package Chess.Gui;

import Chess.GameData;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class EndPage {
    /**
     * Létrehozza a játék utáni ablakot.
     * @param data Az eddigi játékok adatai, amit meg fog jeleníteni.
     */
    public EndPage(LinkedList<GameData> data){
        JFrame f = new JFrame("Game End");
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);

        String[] columnNames = {"Győztes", "Vesztes", "Játék Idő", "Lépés Szám"};
        String[][] columnData = new String[data.size()][];

        int i = 0;
        for (GameData game : data) {
            if (game.getWinner()) {
                columnData[i] = new String[]{game.getPlayer1(), game.getPlayer2(), String.valueOf(game.getTime()), String.valueOf(game.getLepesszam())};
            } else {
                columnData[i] = new String[]{game.getPlayer2(), game.getPlayer1(), String.valueOf(game.getTime()), String.valueOf(game.getLepesszam())};
            }
            i++;
        }

        JTable mainTable = new JTable(columnData, columnNames);
        JScrollPane sp = new JScrollPane(mainTable);
        sp.setPreferredSize(new Dimension(sp.getWidth(), 300));

        f.add(sp, BorderLayout.NORTH);

        JPanel BottomPanel = new JPanel();
        JButton saveGame = new JButton("Save game");
        saveGame.addActionListener(listener->{
            try {
                FileOutputStream outputStream = new FileOutputStream("games.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(data);
                objectOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        BottomPanel.add(saveGame);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e->{
            f.dispose();
            new StartPage();
        });
        BottomPanel.add(newGame);

        JButton exitGame = new JButton("Exit");
        exitGame.addActionListener(e->{
            f.dispose();
        });
        BottomPanel.add(exitGame);

        f.add(BottomPanel, BorderLayout.SOUTH);

        f.pack();
    }
}
