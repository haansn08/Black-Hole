package com.shaan.blackhole;

/**
 * Created by stefan on 20/03/16.
 */
public class Game {
    private final Player players[] = new Player[2];
    private final int fieldSize;
    private GameField field;

    public Game(Player player1, Player player2, int fieldSize) {
        players[0] = player1;
        players[1] = player2;
        this.fieldSize = fieldSize;
        this.field = new GameField(fieldSize);
    }

    public void startGame() throws Exception {
        int currentPlayer = 0;
        for (int i = 0; i < fieldSize * (fieldSize + 1) / 2 - 1; i++) {
            GameMove gameMove = players[currentPlayer].doMove();
            gameMove.number = (i - i % 2) / 2 + 1;
            gameMove.player = currentPlayer;
            field.applyMove(gameMove);

            currentPlayer = (currentPlayer + 1) % players.length;
        }
    }

    public int getFieldSize() {return fieldSize;}
    public GameField getField() {return field;}
}
