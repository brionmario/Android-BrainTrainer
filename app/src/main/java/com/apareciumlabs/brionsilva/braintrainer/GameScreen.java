package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {

    TextView questionTV,answerTV,resultTV;
    //Declaring utility buttons
    Button deleteBtn,hashBtn,minusBtn;
    //Declaring number pad
    Button onebtn,twoBtn,threeBtn,fourBtn,fiveBtn,sixBtn,sevenBtn,eightBtn,nineBtn,zeroBtn;

    //String to store the difficulty being passed
    String difficulty;

    String [] test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();

        difficulty = intent.getStringExtra("Difficulty");

        //initializing the question area
        questionTV = (TextView) findViewById(R.id.questionTextView);
        answerTV = (TextView) findViewById(R.id.answerTextView);

        //setting the answer to be ? initially
        answerTV.setText("?");

        //initializing the result text view
        resultTV = (TextView) findViewById(R.id.resultTextView);

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

        test = questionGenerator.generateQuestion();

        questionTV.setText(test[0]);
        answerTV.setText(test[1]);

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
                answerTV.setText(null);
                break;
            }
            case R.id.hashBtn:{
                //check for the answer
                break;
            }
            case R.id.minusBtn:{
                //backspace action implementation
                String answerStr = answerTV.getText().toString();
                if (answerStr.length() >1 ) {
                    //begin index is 0 and the required end index has to be answerStr.length() - 1
                    answerStr = answerStr.substring(0, answerStr.length() - 1);
                    answerTV.setText(answerStr);
                }
                else if (answerStr.length() <=1 ) {
                    answerTV.setText("0");
                }
                break;
            }

        }
    }
}
