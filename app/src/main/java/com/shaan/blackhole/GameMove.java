package com.shaan.blackhole;

/**
 * Created by stefan on 20/03/16.
 */
public class GameMove {
    public int line;
    public int row;
    public int player;
    public int number;

    public GameMove(int line, int row, int player, int number){
        this.line = line;
        this.row = row;
        this.player = player;
        this.number = number;
    }

    public GameMove(int line, int row) {this(line, row, 0, 0);}

}
