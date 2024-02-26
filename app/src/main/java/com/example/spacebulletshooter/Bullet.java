package com.example.spacebulletshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bullet {

    private final Bitmap bullet;
    private float x, y;
    private boolean isVisible;

    public Bullet(Context context) {
        bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        isVisible = false;
    }

    public void shoot(float spaceshipX, float spaceshipY) {
        x = (float) (spaceshipX + (getBulletWidth() / 2.0));
        y = spaceshipY;
        isVisible = true;
    }

    public void draw(Canvas canvas) {
        if (isVisible) {
            canvas.drawBitmap(bullet, x, y, null);
        }
    }

    public void update() {
        if (isVisible) {
            y -= 35; // Adjust the speed as needed
            if (y < 0) {
                reset();
            }
        }
    }

    public boolean isColliding(Enemy enemy) {
        return isVisible && Rect.intersects(getRect(), enemy.getRect());
    }

    public void reset() {
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    private Rect getRect() {
        return new Rect((int) y, (int) x, (int) y, (int) x);
    }

    private int getBulletWidth() {
        return bullet.getWidth();
    }

}
