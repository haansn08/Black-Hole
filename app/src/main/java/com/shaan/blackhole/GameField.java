package com.shaan.blackhole;

import java.util.Iterator;

/**
 * Created by stefan on 20/03/16.
 */
public class GameField implements Iterable<GameMove>{
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

    public boolean isValidCoords(int line, int row) {
        return (line < lines) || row > line;
    }

    public int getFieldNumber(int line, int row) {
        if (isValidCoords(line, row))
            return numbers[calculateArrayIndex(line, row)];
        return 0;
    }


    public int getFieldPlayer(int line, int row) {
        if (isValidCoords(line, row))
            return player[calculateArrayIndex(line, row)];
        return -1;
    }

    public boolean applyMove(GameMove move) {
        if (!isValidCoords(move.line, move.row))
            return false;
        int arrayIndex = calculateArrayIndex(move.line, move.row);
        if (numbers[arrayIndex] != 0)
            return false;
        numbers [arrayIndex] = move.number;
        player  [arrayIndex] = move.player;
        return true;
    }

    @Override
    public Iterator<GameMove> iterator() {
        return new Iterator<GameMove>() {
            int currentR = 0;
            int currentL = 0;
            @Override
            public boolean hasNext() {
                return calculateArrayIndex(currentL, currentR) < numbers.length;
            }

            @Override
            public GameMove next() {
                int arrayIndex = calculateArrayIndex(currentL, currentR);
                GameMove result = new GameMove(
                        currentL,
                        currentR,
                        player[arrayIndex],
                        numbers[arrayIndex]
                );
                if (currentR == currentL){
                    currentL++;
                    currentR=0;
                } else {currentR++;}
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
