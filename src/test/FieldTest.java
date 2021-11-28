package test;

import Chess.Field;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    Field field;
    @Before
    public void init(){
        field = new Field(0, 0);
    }

    @Test
    public void isEqualTest(){
        assertEquals(true, field.isEqual(new Field(0, 0)));
    }

    @Test
    public void inverzTest(){
        field.inverz();
        assertEquals(true, field.isEqual(new Field(7, 7)));
    }
}