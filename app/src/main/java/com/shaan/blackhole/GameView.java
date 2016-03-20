package com.shaan.blackhole;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by stefan on 20/03/16.
 */
public class GameView extends View {
    private final Paint bluePaint;
    private final Paint blueSelectionPaint;
    private final Paint blackLinePaint;
    private final Paint redPaint;
    private final Paint blackPaint;

    private Game game;
    private float radius;

    private PointF lastTouch = new PointF();
    private int selectedL, selectedR;

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

        blueSelectionPaint = new Paint(blackLinePaint);
        blueSelectionPaint.setColor(Color.BLUE);
        blueSelectionPaint.setAlpha(50);
        blueSelectionPaint.setStrokeWidth(50f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        GameField gameField = game.getField();
        selectedL = -1;
        selectedR = -1;

        radius = canvas.getWidth() / (2*game.getFieldSize() + 2); //Calculate raduis for circles
        float cx, cy; //Center for each circle
        cx = canvas.getWidth() / 2 - radius;
        cy = 0;
        for (int l = 0; l < game.getFieldSize(); l++) {
            cx -= (2*l + 1) * radius;
            cy += (float) (Math.sqrt(3) * radius);
            for (int r = 0; r <= l; r++) {
                cx += 2 * radius;

                canvas.save();
                canvas.translate(cx, cy);

                drawGameCircle(canvas, l, r);
                float distanceToTouch = PointF.length(lastTouch.x - cx, lastTouch.y - cy);
                if (distanceToTouch < radius) {
                    canvas.drawCircle(0, 0, radius, blueSelectionPaint);
                    selectedL = l;
                    selectedR = r;
                }

                canvas.restore();
            }
        }
    }

    private void drawGameCircle(Canvas canvas, int l, int r) {
        GameField gameField = game.getField();
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

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastTouch = new PointF(event.getX(), event.getY());
        if(event.getAction() == MotionEvent.ACTION_UP && selectedL != -1 && selectedR != -1){
            Toast.makeText(getContext(), "R"+selectedR+"L"+selectedL, Toast.LENGTH_SHORT).show();
        }
        invalidate();
        return true;
    }
}
