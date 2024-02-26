package com.example.spacebulletshooter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {

    private final Spaceship spaceship;
    private final Bullet bullet;
    private final List<Enemy> enemies = new ArrayList<>();
    private int points = 0;

    public GameView(Context activity) {
        super(activity);
        spaceship = new Spaceship(activity);
        bullet = new Bullet(activity);
    }

    private void initializeEnemies() {
        int numberOfEnemies = 10;
        int enemyIncreaseThreshold = 10;

        for (int i = 0; i < numberOfEnemies; i++) {
            enemies.add(new Enemy(getContext(), getWidth(), getHeight()));
        }

        /*points = 10 additionalEnemies= 10/10
        points = 20 additionalEnemies=20/10*/
        if (points >= enemyIncreaseThreshold) {
            int additionalEnemies = points / enemyIncreaseThreshold;

            for (int i = 0; i < additionalEnemies; i++) {
                enemies.add(new Enemy(getContext(), getWidth(), getHeight()));
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        // Initialize the Enemy object here, after the view has been laid out
        initializeEnemies();
    }

    public void shootBullet() {
        // Check if the bullet is not already on the screen
        if (!bullet.isVisible()) {
            bullet.shoot(spaceship.getX() + 100, spaceship.getY());
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Draw background
        canvas.drawColor(Color.BLACK);

        // Draw spaceship and bullet if initialized
        spaceship.draw(canvas);
        bullet.draw(canvas);

        for (Enemy enemy : enemies) {
            enemy.draw(canvas);

            if (points > 5) {
                //this if condition not working
                if (spaceship.gameOverColliding(enemy)) {
                    Log.d("GameOver", "yes");
                    canvas.drawColor(Color.BLACK);
                    @SuppressLint("DrawAllocation") Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(100);
                    canvas.drawText("Game Over", 100, 700, paint);
                    invalidate();
                    enemies.clear();
                    gameOver();
                    break;
                }
            }

            // Check for collision with each enemy
            if (bullet.isColliding(enemy)) {
                points++;
                bullet.reset();
                enemy.reset(getWidth(), getHeight());
//                Log.d("isColliding","true");
            }

            // Update each enemy
            enemy.update();
        }

        // Draw points
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Points: " + points, 50, 50, paint);

        bullet.update();
        invalidate();
    }

    private void gameOver() {
        Context context = getContext();
        Activity activity = (Activity) context;
//        Activity activity = getContext();
        activity.finish();

        Intent intent = new Intent(getContext(), GameOver.class);
        intent.putExtra("points", points);
        context.startActivity(intent);
    }

    // Handle touch events in the GameView
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        spaceship.handleTouch(event);

        // Check for touch event to shoot bullet
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            shootBullet();
        }

        return true;
    }
}