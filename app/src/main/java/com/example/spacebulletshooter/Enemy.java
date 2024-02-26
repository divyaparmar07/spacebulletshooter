package com.example.spacebulletshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {

    private final Bitmap enemy;
    private float x, y;
    private final int screenWidth;
    private final int screenHeight;

    public Enemy(Context context, int screenWidth, int screenHeight) {
        enemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        reset(screenWidth, screenHeight);
    }

    public void update() {
        y += 5; // Speed of the enemy
        if (y > screenHeight) {
            reset(screenWidth, screenHeight);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(enemy, y, x, null);
    }

    public int getWidth() {
        return enemy.getWidth();
    }

    public void reset(int screenWidth, int screenHeight) {
        x = new Random().nextInt(screenWidth + screenHeight);
        y = -enemy.getHeight();
    }

    Rect getRect() {
        return new Rect((int) x, (int) y, (int) (x + getWidth()), (int) (y + getWidth()));
    }
}