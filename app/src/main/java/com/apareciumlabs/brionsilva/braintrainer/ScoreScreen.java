package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreScreen extends AppCompatActivity {

    private ArrayAdapter<String> ScoreAdapter;  //Array adapter to store the scores
    ListView scoreListView;

    Button backButton;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MainMenu.class);
                finish();
                startActivity(intent);

            }
        });

        //initialize the score list view
        Intent i = getIntent();
        arrayList = i.getStringArrayListExtra("scorelist");

        ArrayList<String> listArr = new ArrayList<>();

        for(int j=0 ; j<10 ; j++){

            listArr.add(arrayList.get(j));

        }

        ScoreAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item , listArr);

        scoreListView = (ListView) findViewById(R.id.scoreListView) ;
        scoreListView.setAdapter(ScoreAdapter);

    }
}
