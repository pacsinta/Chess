package Chess.Gui;

import javax.swing.*;
import java.awt.*;

public class StartPage {
    JFrame f;

    /**
     * Létrehozza a start menüt. Mérete fix.
     */
    public StartPage() {
        f = new JFrame("Start Game");

        f.setSize(300, 150);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Start new game");
        f.setBackground(Color.CYAN);
        f.setFocusable(false);
        f.setResizable(false);
        f.setVisible(true);

        f.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Chess");
        label.setFont(new Font(null, Font.BOLD, 40));
        labelPanel.add(label);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(400, 100));
        buttonsPanel.setBackground(Color.GREEN);

        JButton singlePlayer = new JButton("Single Player");
        singlePlayer.setFont(new Font(null, Font.BOLD, 20));

        singlePlayer.addActionListener(action -> {
            f.dispose();
            new ChessTable(false);
        });

        buttonsPanel.add(singlePlayer);

        JButton multiPlayer = new JButton("Multi Player");
        multiPlayer.setFont(new Font(null, Font.BOLD, 20));
        multiPlayer.addActionListener(action -> {
            f.dispose();
            new ChessTable(true);
        });

        buttonsPanel.add(multiPlayer);

        f.add(labelPanel, BorderLayout.NORTH);
        f.add(buttonsPanel, BorderLayout.CENTER);

        f.pack();
    }
}
