package com.shaan.blackhole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                getLayoutInflater().inflate(R.layout.a_main, null)
        );

        GameView v_game = (GameView) findViewById(R.id.v_game);
        Player mockPlayer = new Player() {
            int line = 0, row = 0;
            @Override
            public GameMove doMove() {
                GameMove result = new GameMove(line, row);
                if (row == line){
                    line++;
                    row=0;
                } else {row++;}
                return result;
            }
        };
        Game game = new Game(mockPlayer, mockPlayer, 6);
        try {
            game.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        v_game.setGame(game);
    }
}
