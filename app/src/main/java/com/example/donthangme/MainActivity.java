package com.example.donthangme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play Button - Redirect to GameActivity
        Button playButton = findViewById(R.id.btn_play);
        playButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });

        // Quit Button - Close the app
        Button quitButton = findViewById(R.id.btn_quit);
        quitButton.setOnClickListener(v -> {
            finishAffinity();  // Close all activities
            System.exit(0);    // Exit app completely
        });
    }
}