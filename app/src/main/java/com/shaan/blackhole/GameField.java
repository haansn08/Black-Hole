package com.shaan.blackhole;

/**
 * Created by stefan on 20/03/16.
 */
public class GameField {
    private int lines;
    private int values[];

    public GameField(int lines) {
        this.lines = lines;
        int arraySize = lines * (lines + 1) / 2;
        values = new int[arraySize];
    }

    public GameField() {
        this(6);
    }

    public int getLines() {
        return lines;
    }

    private int calculateArrayIndex(int line, int row) {
        return line * (line + 1) / 2 + 1 + row;
    }

    private void checkCoords(int line, int row) {
        if (!(line < lines) || row > line)
            throw new IndexOutOfBoundsException("Coordinates out of bounds");
    }

    public int getField(int line, int row) {
        checkCoords(line, row);
        return values[calculateArrayIndex(line, row)];
    }

    public void setField(int line, int row, int value) throws Exception {
        if(getField(line, row) != 0)
            throw new Exception("Unable to set field, which is already taken");
        values[calculateArrayIndex(line, row)] = value;
    }

}
