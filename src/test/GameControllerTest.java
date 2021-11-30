package test;

import Chess.Field;
import Chess.GameController;
import Chess.Pieces.Pawn;
import Chess.Player;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class GameControllerTest {
    @Test
    public void movePieceTest(){
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        JLabel t = new JLabel();
        GameController controller = new GameController(player1, player2, t);

        controller.Move(new Field(0, 1), new JButton("n"));
        controller.Move(new Field(0, 2), new JButton("n"));

        assertTrue(player1.getPiece()[8].getLocation().isEqual(new Field(0, 2)));
    }
}