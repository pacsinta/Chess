package test;

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
    public void whiteInit(){
        //assertEquals();
    }

    @Test
    public void selectPieceTest(){

    }
}