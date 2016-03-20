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
        Game game = new Game(6);
        v_game.setGame(game);
    }
}
