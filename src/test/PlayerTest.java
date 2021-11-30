package test;

import Chess.Field;
import Chess.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player1;
    Player player2;
    @Before
    public void init(){
        player1 = new Player(true);
        player2 = new Player(false);
    }

    @Test
    public void InitTest(){
        assertTrue(player2.getPiece()[4].getLocation().isEqual(new Field(3, 7)));
        assertTrue(player1.getPiece()[4].getLocation().isEqual(new Field(4, 0)));
        for (int i = 0; i < 16; i++) {
            assertEquals(false, player2.getPiece()[i].color);
            assertEquals(true, player1.getPiece()[i].color);
        }
    }

    @Test
    public void checkPieceIsOnFieldTest(){
        assertEquals(-1, player1.checkPieceIsOnField(new Field(7, 7)));
        for (int i = 0; i < 8; i++) {
            assertNotEquals(-1, player2.checkPieceIsOnField(new Field(i, 7)));
            assertNotEquals(-1, player2.checkPieceIsOnField(new Field(i, 6)));
        }
    }

    @Test
    public void checkMoveBackTest() throws Exception {
        player1.selectPiece(new Field(0, 1));
        player1.move(new Field(0, 3), player2);
        assertNotEquals(-1, player1.checkPieceIsOnField(new Field(0, 3)));

        player1.moveBack();
        assertEquals(-1, player1.checkPieceIsOnField(new Field(0, 3)));
        assertNotEquals(-1, player1.checkPieceIsOnField(new Field(0, 1)));
    }
}