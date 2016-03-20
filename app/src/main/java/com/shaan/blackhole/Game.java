package com.shaan.blackhole;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan on 20/03/16.
 */
public class Game {
    private final int fieldSize;
    private GameField field;

    private int currentPlayer = 0;
    private int currentNumber = 1;


    public interface OnGameOverListener {
        void onGameOver (int playerWon);
    }
    private OnGameOverListener onGameOverListener = new OnGameOverListener() {
        @Override
        public void onGameOver(int playerWon) {

        }
    };
    public void setOnGameOverListener(OnGameOverListener listener) {onGameOverListener = listener;}

    public Game(int fieldSize) {
        this.fieldSize = fieldSize;
        this.field = new GameField(fieldSize);
    }

    public int getFieldSize() {return fieldSize;}
    public GameField getField() {return field;}

    public void onPlayerSelect(int selectedL, int selectedR) {
        GameMove gameMove = new GameMove(selectedR, selectedL, currentPlayer, currentNumber);
        if (field.applyMove(gameMove)) {
            nextPlayer();
        }
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
        if (currentPlayer == 0)
            currentNumber += 1;
        if (currentNumber > fieldSize * (fieldSize + 1) / 4)
            onGameOver();
    }

    private void onGameOver() {
        int blackHoleL = 0, blackHoleR = 0;
        for (GameMove gameMove : field)
            if(gameMove.number == 0){
                blackHoleL = gameMove.line;
                blackHoleR = gameMove.row;
            }

        //get neighbour fields
        List<GameMove> neighbours = new ArrayList<>(6);
        addValidNeighbour(blackHoleL - 1, blackHoleR - 1, neighbours);
        addValidNeighbour(blackHoleL - 1, blackHoleR, neighbours);
        addValidNeighbour(blackHoleL, blackHoleR - 1, neighbours);
        addValidNeighbour(blackHoleL, blackHoleR + 1, neighbours);
        addValidNeighbour(blackHoleL + 1, blackHoleR, neighbours);
        addValidNeighbour(blackHoleL + 1, blackHoleR + 1, neighbours);

        //calculate Score
        int playerScore[] = new int[2];
        for (GameMove neighbour : neighbours) {
            playerScore[neighbour.player] += neighbour.number;
        }

        if(playerScore[0] < playerScore[1])
            onGameOverListener.onGameOver(0);
        else
            onGameOverListener.onGameOver(1);
    }

    private void addValidNeighbour(int l, int r, List<GameMove> neighbours) {
        if (field.isValidCoords(l, r))
            neighbours.add(new GameMove(
                    l, r,
                    field.getFieldPlayer(l, r),
                    field.getFieldNumber(l, r)
            ));
    }

}
