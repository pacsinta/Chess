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
        pawn1 = new Pawn(new Field(0, 0),true);
    }

    @Test
    public void getIconTest(){
        Pawn pawn2 = new Pawn(new Field(1, 1), false);
        assertNotEquals(-1, pawn1.getIcon().getIconHeight());
        assertNotEquals(-1, pawn2.getIcon().getIconHeight());
    }

    @Test
    public void checkMoveTest() {
        assertFalse(pawn1.checkMove(new Field(1, 0)));
        assertTrue(pawn1.checkMove(new Field(0, 1)));
        assertTrue(pawn1.checkMove(new Field(0, 2)));
    }

    @Test
    public void moveTest(){

    }
}