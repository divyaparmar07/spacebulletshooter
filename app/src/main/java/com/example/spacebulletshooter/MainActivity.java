package com.example.spacebulletshooter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button GameStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameStartBtn = findViewById(R.id.GameStart);

        if (GameStartBtn != null) {
            GameStartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Log.d("dvy", "It's in else block");
        }
    }
}
