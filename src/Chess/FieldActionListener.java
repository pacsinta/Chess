package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {
    Field field;
    JButton button;

    Player white;
    Player black;
    PlayerMoveTypes currentMoveType;

    public FieldActionListener(Field field, Player whitePlayer, Player blackPlayer, PlayerMoveTypes currentColor, JButton button){
        this.field = field;
        this.white = whitePlayer;
        this.black = blackPlayer;
        this.currentMoveType = currentColor;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(currentMoveType == PlayerMoveTypes.WhiteSelectPiece){
            if(white.selectPiece(field)){
                System.out.println("White Selected");
                button.setBackground(Color.GREEN);
            }
        }else if(currentMoveType == PlayerMoveTypes.WhiteMovePiece){

        }
    }
}
