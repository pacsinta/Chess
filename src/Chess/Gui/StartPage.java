package Chess.Gui;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {
    public StartPage(ChessTable table){
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start new game");
        setBackground(Color.CYAN);
        setFocusable(false);
        setResizable(false);
        setVisible(true);

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Chess");
        label.setFont(new Font(null, Font.TYPE1_FONT, 25));
        labelPanel.add(label);


        JPanel buttonsPanel = new JPanel();
        //buttonsPanel.setLayout(new GridLayout());
        buttonsPanel.setPreferredSize(new Dimension(300, 300));
        buttonsPanel.setBackground(Color.GREEN);

        JButton singlePlayer = new JButton("Single Player");

        singlePlayer.addActionListener(action -> {
            dispose();
            table.setVisible(true);
        });

        buttonsPanel.add(singlePlayer);

        JButton multiPlayer = new JButton("Multi Player");
        multiPlayer.addActionListener(action -> {
            dispose();
            table.setVisible(true);
        });

        buttonsPanel.add(multiPlayer);

        add(labelPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
    }
}
