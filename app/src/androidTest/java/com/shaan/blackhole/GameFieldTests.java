package com.shaan.blackhole;

import junit.framework.TestCase;


/**
 * Created by stefan on 20/03/16.
 */
public class GameFieldTests extends TestCase {
    public void testCoords(){
        GameField gameField = new GameField();
        assertEquals(6, gameField.getLines()); //default line count

        GameField gameField1 = new GameField(5);
        assertEquals(5, gameField1.getLines());
        assertEquals(0, gameField1.getField(0,0));
        try{
            gameField1.getField(0,1);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
        try{
            gameField1.getField(4,5);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
        try{
            gameField1.getField(5,0);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
    }

    public void testFieldAccess() throws Exception {
        GameField gameField = new GameField();
        gameField.setField(0, 0, 4);
        gameField.setField(4, 4, 9);
        assertEquals(4, gameField.getField(0, 0));
        assertEquals(9, gameField.getField(4, 4));
        try{
            gameField.setField(4, 4, 5);
            fail("Able to set field, which is taken");
        } catch (Exception e) {
            assertEquals(9, gameField.getField(4, 4));
        }
        try{
            gameField.setField(4, 5, 8);
            fail("Impossible coords");
        } catch (IndexOutOfBoundsException e) {}
    }
}
