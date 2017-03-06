package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    TextView difficultyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        difficultyTV = (TextView) findViewById(R.id.difficulty);

        Intent intent = getIntent();

        String diff = intent.getStringExtra("Difficulty");

        difficultyTV.setText(diff);

    }
}
