package com.shaan.blackhole;

/**
 * Created by stefan on 20/03/16.
 */
public class GameField {
    private int lines;
    private int numbers[];
    private int player[];

    public GameField(int lines) {
        this.lines = lines;
        int arraySize = lines * (lines + 1) / 2;
        numbers = new int[arraySize];
        player = new int[arraySize];
    }

    public GameField() {
        this(6);
    }

    public int getLines() {
        return lines;
    }

    private static int calculateArrayIndex(int line, int row) {
        return line * (line + 1) / 2 + row;
    }

    private void checkCoords(int line, int row) {
        if (!(line < lines) || row > line)
            throw new IndexOutOfBoundsException("Coordinates out of bounds");
    }

    public int getFieldNumber(int line, int row) {
        checkCoords(line, row);
        return numbers[calculateArrayIndex(line, row)];
    }


    public int getFieldPlayer(int line, int row) {
        checkCoords(line, row);
        return player[calculateArrayIndex(line, row)];
    }

    public boolean applyMove(GameMove move) {
        checkCoords(move.line, move.row);
        int arrayIndex = calculateArrayIndex(move.line, move.row);
        if (numbers[arrayIndex] != 0)
            return false;
        numbers [arrayIndex] = move.number;
        player  [arrayIndex] = move.player;
        return true;
    }

}
