package com.shaan.blackhole;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by stefan on 20/03/16.
 */
public class GameView extends View {
    private final Paint bluePaint;
    private final Paint blackLinePaint;
    private final Paint redPaint;
    private final Paint blackPaint;
    private Game game;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        blackLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blackLinePaint.setColor(Color.BLACK);
        blackLinePaint.setStrokeWidth(5f);
        blackLinePaint.setStyle(Paint.Style.STROKE);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setTextSize(100f);

        bluePaint = new Paint(redPaint);
        bluePaint.setColor(Color.BLUE);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setAlpha(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        GameField gameField = game.getField();

        int lines = game.getFieldSize();
        float width = canvas.getWidth();
        float radius = width / (2*lines + 2);

        canvas.translate(width / 2 - radius, 0);
        for (int l = 0; l < lines; l++) {
            canvas.translate(
                    -(2*l + 1) * radius,
                    (float) (Math.sqrt(3) * radius)
            );
            for (int r = 0; r <= l; r++) {
                canvas.translate(2 * radius, 0);
                canvas.drawCircle(0, 0, radius, blackLinePaint); //draw border

                if (gameField.getFieldNumber(l, r) == 0)
                    canvas.drawCircle(0, 0, radius, blackPaint);
                else {
                    Paint playerPaint = gameField.getFieldPlayer(l, r) == 0 ? redPaint : bluePaint;
                    playerPaint.setAlpha(50);
                    canvas.drawCircle(0, 0, radius, playerPaint);
                    playerPaint.setAlpha(255);

                    String number = gameField.getFieldNumber(l, r) + "";
                    canvas.drawText(
                            number,
                            -playerPaint.measureText(number) / 2,
                            radius / 2,
                            playerPaint
                    );
                }
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
