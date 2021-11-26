package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {
    Field buttonField;
    GameController controller;
    JButton button;

    public FieldActionListener(Field field, GameController controller, JButton button){
        this.buttonField = field;
        this.controller = controller;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.Move(buttonField, button);
    }
}
