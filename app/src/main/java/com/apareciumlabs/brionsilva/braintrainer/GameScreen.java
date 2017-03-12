package com.apareciumlabs.brionsilva.braintrainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class GameScreen extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    TextView questionTV,answerTV,resultTV,hintsTV,timeTV;
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

    public static final int MAX_QUESTIONS = 10; //constant holds the maximum question count

    public static final int COUNTER_START = 10000; //constant to hold the counter start time i.e = 10 seconds

    //question number counter
    static int numQuestions = 0;

    //number of tries counter
    int tries = 1;

    //variable to store the remaining time of the timer
    long secondsLeft;

    //counter boolean
    boolean isCounterRunning = false;

    //List to store the scores
    ArrayList<String> scores;

    //Save data
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //boolean to store if the continue button clicked
    Boolean isContinueClicked;

    String answerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();

        isContinueClicked = intent.getBooleanExtra("isContinueButtonClicked" , false);

        //initializing the textview to display the countdown timer
        timeTV = (TextView) findViewById(R.id.timeTextView);

        //initializing the hints switch
        hintsSwitch = (Switch)  findViewById(R.id.hintSwitch);
        hintsSwitch.setOnCheckedChangeListener(this);

        //initializing the question area
        questionTV = (TextView) findViewById(R.id.questionTextView);
        answerTV = (TextView) findViewById(R.id.answerTextView);



        //initializing the result text view
        resultTV = (TextView) findViewById(R.id.resultTextView);


        //initializing the hints text view
        hintsTV = (TextView) findViewById(R.id.lblHint);


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

        //initialize the score list
        scores = new ArrayList<>();


        if(isContinueClicked){
            //Toast.makeText(getBaseContext(),"Continue clicked",Toast.LENGTH_SHORT).show();

            //Data to be passed
            /*isHashBtnClicked = intent.getBooleanExtra("Hints",false);
            secondsLeft = intent.getLongExtra("TimeLeft",0);
            questionAnswer[0] = intent.getStringExtra("Question");
            questionAnswer[1] = "88";
            resultTV.setText(intent.getStringExtra("Result"));
            numQuestions = intent.getIntExtra("QuestionNumber",1); */

            isHintsChecked = intent.getBooleanExtra("Hints",true);
            hintsSwitch.setChecked(isHintsChecked);
            difficulty = intent.getStringExtra("Difficulty");
            Toast.makeText(getBaseContext(), difficulty + " Difficulty selected",Toast.LENGTH_SHORT).show();
            questionTV.setText(intent.getStringExtra("Question"));
            answerTV.setText(intent.getStringExtra("Answer"));
            resultTV.setText(intent.getStringExtra("Result"));
            numQuestions = intent.getIntExtra("QuestionNumber" , 0);
            //COUNTER_START = (int) intent.getLongExtra("TimeLeft" , 0);
            //Toast.makeText(getBaseContext() , String.valueOf(COUNTER_START), Toast.LENGTH_SHORT).show();


            if(isHintsChecked){
                hintsTV.setText("Hints are ON");

            }else {
                hintsTV.setText("Hints are OFF");
            }

            EvaluateEngine evaluateEngine = new EvaluateEngine();
            Double answer = evaluateEngine.evaluate(questionTV.getText().toString());
            answerString = String.valueOf((int) Math.round(answer));


            //Toast.makeText(getBaseContext(), "Q"  + numQuestions + " Ans" + answerString,Toast.LENGTH_SHORT).show();


        }else {

            //initialize all the variables to default
            numQuestions = 0;
            tries = 1;
            isHashBtnClicked = false;
            isHintsChecked = false;
            isCounterRunning = false;

            //Toast.makeText(getBaseContext(),"New game clicked",Toast.LENGTH_SHORT).show();

            difficulty = intent.getStringExtra("Difficulty");
            timeTV.setText("");
            //setting the answer to be ? initially
            answerTV.setText("?");
            resultTV.setText("");
            hintsTV.setText("Hints are OFF");

            //initiate the first question
            makeQuestion();

        }

        //start the countdown timer
        mCountDownTimer.start();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

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

                if(answerTV.getText().toString().equals("")){
                    alertBox("Please input a valid answer","OKAY");
                } else{

                    //call the validate method
                    validate();
                }
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

        if(numQuestions < MAX_QUESTIONS ) {

            if (isHashBtnClicked == false) {

                //call the check answer method
                checkAnswer();

            } else {

                //move on to the next question
                makeQuestion();

            }
        }else if(numQuestions == MAX_QUESTIONS ){

            //check the answer
            checkAnswer();

        }else {

            /*when the maximum questions have been answered
            finish the current activity and move to the score */
            Intent intent = new Intent(getBaseContext(),
                    ScoreScreen.class);
            intent.putStringArrayListExtra("scorelist", scores);
            finish();
            startActivity(intent);

        }
    }


    public void checkAnswer(){

        String correctAnswer;


        if(isContinueClicked){
            correctAnswer = answerString;
        }else{
            correctAnswer = questionAnswer[1];
        }
        //check for the answer
        String submittedAnswer = answerTV.getText().toString();

        if (submittedAnswer.equals(correctAnswer)) {
            resultTV.setText("CORRECT");
            resultTV.setTextColor(Color.GREEN);

            //save the score
            scores.add("Question " + numQuestions + "\t - \t" + String.valueOf(calculateScore(secondsLeft)));

        } else {
            //checking if the hints option is on
            if(isHintsChecked == false ) {

                resultTV.setText("WRONG");
                resultTV.setTextColor(Color.RED);

                //save the score
                scores.add("Question " + numQuestions + "\t - \t" + "0");

            }else {

                if(tries < 5) {

                    if (Integer.parseInt(submittedAnswer) > Integer.parseInt(correctAnswer)) {

                        resultTV.setText("LESS");
                        resultTV.setTextColor(Color.BLUE);

                    } else if (Integer.parseInt(submittedAnswer) < Integer.parseInt(correctAnswer)) {

                        resultTV.setText("GREATER");
                        resultTV.setTextColor(Color.BLUE);

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

    /**
     * Creates a new question resetting the timer,answer textview, result text view.
     */
    public void makeQuestion(){

        mCountDownTimer.start();

        resultTV.setText("");
        answerTV.setText("?");
        tries = 1;

        //Generate a question
        QuestionGenerator questionGenerator = new QuestionGenerator(difficulty);

        questionAnswer = questionGenerator.generateQuestion();

        questionTV.setText(questionAnswer[0]);

        numQuestions++;
        Toast.makeText(getBaseContext(), "Question Number " + numQuestions, Toast.LENGTH_SHORT).show();
        isHashBtnClicked = false;
    }


    /**
     * Contains the actions performed when the timer is running and when it's finished.
     * @param First parameter is for the start time
     * @param Second parameter is for the interval
     */
    CountDownTimer mCountDownTimer = new CountDownTimer(COUNTER_START, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            //asigning the remaining time to a variable
            secondsLeft = millisUntilFinished / 1000;
            timeTV.setText(millisUntilFinished / 1000 + " seconds");
        }

        @Override
        public void onFinish() {


            //save the score
            scores.add("Question " + numQuestions + "\t - \t" + "0");

            //check if the maximum number of questions have been reached
            if(numQuestions == MAX_QUESTIONS){

                //finish the current activity and move to the score
                Intent intent = new Intent(getBaseContext(),
                        ScoreScreen.class);
                intent.putStringArrayListExtra("scorelist", scores);
                mCountDownTimer.cancel();
                finish();
                startActivity(intent);

            }else {

                //move on to the next question
                makeQuestion();
                isCounterRunning = false;
            }

        }
    };

    /**
     * Overrides the default android back button action
     * Stops the timer , Clears the current stack and goes back to the main menu.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCountDownTimer.cancel();

        //save the current game by calling savegamedata method
        saveGameData();

        Intent intent = new Intent(this, MainMenu.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish(); // finish the current activity
        startActivity(intent);

    }

    public int calculateScore(long time_remaining){

        int score = (int) (100 / (10 - time_remaining));
        return score;
    }

    public void saveGameData(){

        //Data to be passed
        editor.putString("Difficulty" , difficulty);
        editor.putBoolean("Hints" , isHintsChecked);
        editor.putLong("TimeLeft" , secondsLeft);
        editor.putString("Question" , questionTV.getText().toString());
        editor.putString("Answer" , answerTV.getText().toString());
        editor.putString("Result" , resultTV.getText().toString());
        editor.putInt("QuestionNumber" , numQuestions);
        //editor.putStringSet("Scores" , (Set<String>) scores);
        editor.commit();
    }

}
