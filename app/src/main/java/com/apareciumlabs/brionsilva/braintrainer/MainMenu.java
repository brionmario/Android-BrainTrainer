package com.apareciumlabs.brionsilva.braintrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button newgameBtn,continueBtn,aboutBtn,exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //initializing the buttons and onclick
        newgameBtn = (Button) findViewById(R.id.newgameBtn);
        newgameBtn.setOnClickListener(this);

        continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(this);

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);

        exitBtn = (Button) findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.newgameBtn :{
                //redirect to new game activity
                showDifficultyDialog();
                break;
            }
            case R.id.continueBtn :{
                //redirect to new game activity
                break;
            }
            case R.id.aboutBtn :{
                //redirect to new game activity
                break;

            }

            case R.id.exitBtn :{
                /*Terminates the application. Added the line android:excludeFromRecents="true"
                in the manifest to terminate the app from the background as well*/
                finish();
                break;
            }
        }
    }

    //The dialog box to select the difficulty
    public void showDifficultyDialog()
    {
        List<String> difficulty = new ArrayList<String>();
        difficulty.add("Novice");
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Guru");

        //Create sequence of items
        final CharSequence[] Difficulty = difficulty.toArray(new String[difficulty.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this,R.style.BrionDialogTheme);
        dialogBuilder.setTitle("Please select the game difficulty");
        dialogBuilder.setItems(Difficulty, new DialogInterface.OnClickListener() {

            //when an item is clicked, difficulty is passed via the intent
            public void onClick(DialogInterface dialog, int item) {
                //the selected item stored in a string to be passed
                String selectedDifficulty = Difficulty[item].toString();
                Intent intent = new Intent(getBaseContext(), GameScreen.class);
                intent.putExtra("Difficulty", selectedDifficulty);

                //start the Game Screen activity
                startActivity(intent);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }


}
