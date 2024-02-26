package com.example.spacebulletshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    Button ExitBtn, PlayAgainBtn;
    TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String points = String.valueOf(getIntent().getIntExtra("points", 0));

        ExitBtn = findViewById(R.id.buttonExit);
        PlayAgainBtn = findViewById(R.id.buttonPlayAgain);
        point = findViewById(R.id.points);

        point.setText(points);

        ExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });

        PlayAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }
}