package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreScreen extends AppCompatActivity {

    private ArrayAdapter<String> ScoreAdapter;  //Array adapter to store the scores
    ListView scoreListView;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        //initialize the score list view
        Intent i = getIntent();
        arrayList = i.getStringArrayListExtra("scorelist");

        ScoreAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item , arrayList);

        scoreListView = (ListView) findViewById(R.id.scoreListView) ;
        scoreListView.setAdapter(ScoreAdapter);

    }
}
