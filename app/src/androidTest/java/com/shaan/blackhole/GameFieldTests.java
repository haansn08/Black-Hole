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
        assertEquals(0, gameField1.getFieldNumber(0, 0));
        try{
            gameField1.getFieldNumber(0, 1);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
        try{
            gameField1.getFieldNumber(4, 5);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
        try{
            gameField1.getFieldNumber(5, 0);
            fail("Impossible Coords");
        } catch (IndexOutOfBoundsException e){}
    }

    public void testFieldAccess() throws Exception {
        GameField gameField = new GameField();
        GameMove move1 = new GameMove(0, 0, 0, 1);
        GameMove move2 = new GameMove(2, 2, 1, 1);
        GameMove move3 = new GameMove(3, 2, 0, 2);
        GameMove illegalMove1 = new GameMove(3, 2, 1, 4);
        assertTrue(gameField.applyMove(move1));
        assertTrue(gameField.applyMove(move2));
        assertTrue(gameField.applyMove(move3));
        assertFalse(gameField.applyMove(illegalMove1));
        assertEquals(1, gameField.getFieldNumber(0, 0));
        assertEquals(1, gameField.getFieldNumber(2, 2));
        assertEquals(2, gameField.getFieldNumber(3, 2));
        assertEquals(0, gameField.getFieldPlayer(0, 0));
        assertEquals(1, gameField.getFieldPlayer(2, 2));
    }
}
