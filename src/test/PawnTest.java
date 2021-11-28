package test;

import Chess.Field;
import Chess.Pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class PawnTest {
    Pawn pawn1;
    Pawn pawn2;
    @Before
    public void init(){
        pawn1 = new Pawn(new Field(0, 0),true);
        pawn2 = new Pawn(new Field(0, 0),false);
    }

    @Test
    public void getIconTest(){
        assertNotEquals(-1, pawn1.getIcon().getIconHeight());
        assertNotEquals(-1, pawn2.getIcon().getIconHeight());
    }

    @Test
    public void checkMoveTest(){

    }

    @Test
    public void moveTest(){

    }
}