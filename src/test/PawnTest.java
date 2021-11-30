package test;

import Chess.Field;
import Chess.Pieces.Pawn;
import Chess.Player;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class PawnTest {
    Pawn pawn1;
    @Before
    public void init(){
        pawn1 = new Pawn(new Field(5, 5),true);
    }

    @Test
    public void getIconTest(){
        Pawn pawn2 = new Pawn(new Field(1, 1), false);
        assertNotEquals(-1, pawn1.getIcon().getIconHeight());
        assertNotEquals(-1, pawn2.getIcon().getIconHeight());
    }

    @Test
    public void checkMoveTest() {
        assertFalse(pawn1.checkMove(new Field(6, 5)));
        assertTrue(pawn1.checkMove(new Field(5, 6)));
        assertTrue(pawn1.checkMove(new Field(5, 7)));
    }

    @Test(expected = Exception.class)
    public void moveTestException() throws Exception {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        pawn1.move(new Field(5, 8), player1, player2);
    }

    @Test
    public void moveTest() throws Exception {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        pawn1.specialMove(new Field(6, 6), player1, player2);

        assertTrue(pawn1.getLocation().isEqual(new Field(6, 6)));
    }
}