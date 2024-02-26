package com.example.spacebulletshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Spaceship {

    private final Bitmap spaceship;
    private float x;
    private final float y;

    public Spaceship(Context context) {
        spaceship = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);
        x = 250;
        y = 1300;
    }

    public boolean gameOverColliding(Enemy enemy) {
        return Rect.intersects(getRect(), enemy.getRect());
    }

    public int getWidth() {
        return spaceship.getWidth();
    }

    private Rect getRect() {
        return new Rect((int) y, (int) x, (int) (y + getWidth()), (int) (x + getWidth()));
    }

    public void handleTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            x = (float) (event.getX() - (spaceship.getWidth() / 2.0));
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(spaceship, x, y, null);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}