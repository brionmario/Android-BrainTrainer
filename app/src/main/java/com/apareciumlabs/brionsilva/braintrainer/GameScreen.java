package com.apareciumlabs.brionsilva.braintrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    TextView questionTV,answerTV,resultTV,hintsTV;
    //Declaring utility buttons
    Button deleteBtn,hashBtn,minusBtn;
    //Declaring number pad
    Button onebtn,twoBtn,threeBtn,fourBtn,fiveBtn,sixBtn,sevenBtn,eightBtn,nineBtn,zeroBtn;
    //declaring the switch for the hints option
    Switch hintsSwitch;

    //String to store the difficulty being passed
    String difficulty;
    //String array to show the question and answer pair to be displayed on the text views
    String [] questionAnswer;

    boolean isHashBtnClicked=false;

    boolean isHintsChecked = false;

    public static final int MAX_QUESTIONS = 10;

    //question number counter
    int numQuestions = 1;

    //number of tries counter
    int tries = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();

        difficulty = intent.getStringExtra("Difficulty");

        //initializing the hints switch
        hintsSwitch = (Switch)  findViewById(R.id.hintSwitch);
        hintsSwitch.setOnCheckedChangeListener(this);

        //initializing the question area
        questionTV = (TextView) findViewById(R.id.questionTextView);
        answerTV = (TextView) findViewById(R.id.answerTextView);

        //setting the answer to be ? initially
        answerTV.setText("?");

        //initializing the result text view
        resultTV = (TextView) findViewById(R.id.resultTextView);
        resultTV.setText("");

        //initializing the hints text view
        hintsTV = (TextView) findViewById(R.id.lblHint);
        hintsTV.setText("Hints are OFF");

        //initialising the utility buttons
        deleteBtn = (Button) findViewById(R.id.delBtn);
        deleteBtn.setOnClickListener(this);

        hashBtn = (Button) findViewById(R.id.hashBtn);
        hashBtn.setOnClickListener(this);

        minusBtn = (Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(this);

        //initialising number pad
        onebtn = (Button) findViewById(R.id.oneBtn);
        onebtn.setOnClickListener(this);

        twoBtn = (Button) findViewById(R.id.twoBtn);
        twoBtn.setOnClickListener(this);

        threeBtn = (Button) findViewById(R.id.threeBtn);
        threeBtn.setOnClickListener(this);

        fourBtn = (Button) findViewById(R.id.fourBtn);
        fourBtn.setOnClickListener(this);

        fiveBtn = (Button) findViewById(R.id.fiveBtn);
        fiveBtn.setOnClickListener(this);

        sixBtn = (Button) findViewById(R.id.sixBtn);
        sixBtn.setOnClickListener(this);

        sevenBtn = (Button) findViewById(R.id.sevenBtn);
        sevenBtn.setOnClickListener(this);

        eightBtn = (Button) findViewById(R.id.eightBtn);
        eightBtn.setOnClickListener(this);

        nineBtn = (Button) findViewById(R.id.nineBtn);
        nineBtn.setOnClickListener(this);

        zeroBtn = (Button) findViewById(R.id.zeroBtn);
        zeroBtn.setOnClickListener(this);

        //Generate a question
        QuestionGenerator questionGenerator = new QuestionGenerator(difficulty);

        questionAnswer = questionGenerator.generateQuestion();

        questionTV.setText(questionAnswer[0]);

    }

    @Override
    public void onClick(View v) {

        if(answerTV.getText().toString().equals("?")){
            answerTV.setText(null);
        }

        switch(v.getId()){

            //Appending the question text view when number pad is clicked
            case R.id.oneBtn:{
                answerTV.append("1");
                break;
            }
            case R.id.twoBtn:{
                answerTV.append("2");
                break;
            }
            case R.id.threeBtn:{
                answerTV.append("3");
                break;
            }
            case R.id.fourBtn:{
                answerTV.append("4");
                break;
            }
            case R.id.fiveBtn:{
                answerTV.append("5");
                break;
            }
            case R.id.sixBtn:{
                answerTV.append("6");
                break;
            }
            case R.id.sevenBtn:{
                answerTV.append("7");
                break;
            }
            case R.id.eightBtn:{
                answerTV.append("8");
                break;
            }
            case R.id.nineBtn:{
                answerTV.append("9");
                break;
            }
            case R.id.zeroBtn:{
                answerTV.append("0");
                break;
            }

            //utility buttons
            case R.id.delBtn:{
                //backspace action implementation
                String answerStr = answerTV.getText().toString();
                if (answerStr.length() >1 ) {
                    //begin index is 0 and the required end index has to be answerStr.length() - 1
                    answerStr = answerStr.substring(0, answerStr.length() - 1);
                    answerTV.setText(answerStr);
                }
                else if (answerStr.length() <=1 ) {
                    answerTV.setText("");
                }
                break;
            }
            case R.id.hashBtn:{
                //call the validate method
                validate();
                break;
            }
            case R.id.minusBtn:{
                answerTV.append("-");
                break;
            }

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            hintsTV.setText("Hints are ON");
            isHintsChecked = true;
            Toast.makeText(getBaseContext(),"Hints have been turned ON",Toast.LENGTH_SHORT).show();
        }else {
            hintsTV.setText("Hints are OFF");
            isHintsChecked = false;
            Toast.makeText(getBaseContext(),"Hints have been turned OFF",Toast.LENGTH_SHORT).show();
        }
    }

    //Validating the answer
    public void validate(){

        if(numQuestions < MAX_QUESTIONS) {

            if (isHashBtnClicked == false) {
                //check for the answer
                String submittedAnswer = answerTV.getText().toString();

                if (submittedAnswer.equals(questionAnswer[1])) {
                    resultTV.setText("CORRECT");
                    resultTV.setTextColor(Color.GREEN);
                } else {
                    //checking if the hints option is on
                    if(isHintsChecked == false ) {

                        resultTV.setText("WRONG");
                        resultTV.setTextColor(Color.RED);

                    }else {

                        if(tries < 5) {
                            if (Integer.parseInt(submittedAnswer) > Integer.parseInt(questionAnswer[1])) {

                                alertBox("Less", "Okay");

                            } else if (Integer.parseInt(submittedAnswer) < Integer.parseInt(questionAnswer[1])) {

                                alertBox("Greater", "Okay");

                            }
                            Toast.makeText(getBaseContext() , "Attempt number " + tries , Toast.LENGTH_SHORT).show();
                            if(tries==4) {
                                isHashBtnClicked=true;
                                return;
                            }
                            tries++;
                            return;
                        }
                    }
                }

                //button is pressed once
                isHashBtnClicked = true;
            } else {

                resultTV.setText("");
                answerTV.setText("");
                tries = 1;

                //Generate a question
                QuestionGenerator questionGenerator = new QuestionGenerator(difficulty);

                questionAnswer = questionGenerator.generateQuestion();

                questionTV.setText(questionAnswer[0]);

                numQuestions++;
                Toast.makeText(getBaseContext(), "Question Number " + numQuestions, Toast.LENGTH_SHORT).show();
                isHashBtnClicked = false;

            }
        }else {
            Toast.makeText(getBaseContext(), " Number Up yako" + numQuestions, Toast.LENGTH_SHORT).show();
        }
    }

    //Alert Dialog boy reusable  method
    public void alertBox(String Message , String Button){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Message);

        builder.setPositiveButton(
                Button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
