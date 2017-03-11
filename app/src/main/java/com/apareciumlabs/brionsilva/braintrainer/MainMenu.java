package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button newgameBtn,continueBtn,aboutBtn,exitBtn;
    Button okayBtn; //button for the popup
    private PopupWindow popupWindow;

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
                //initiate the about popup window
                aboutPopupWindow(v);
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

                Toast.makeText(MainMenu.this, selectedDifficulty + " Difficulty selected",Toast.LENGTH_LONG).show();

                //start the Game Screen activity
                startActivity(intent);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }

    //The method for the popup window
    private void aboutPopupWindow(View v) {
        try {
            //get an instance of layoutinflater
            LayoutInflater inflater = (LayoutInflater) MainMenu.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //initiate the view
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popupView));

            //initialize a size for the popup
            popupWindow = new PopupWindow(layout, 1200, 1800, true);
            // display the popup in the center
            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            okayBtn = (Button) layout.findViewById(R.id.okayBtn);
            okayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
