package com.apareciumlabs.brionsilva.braintrainer;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brionsilva on 07/03/2017.
 */

public class QuestionGenerator {

    public String difficulty;
    public int difficultyLevel;

    public int maxTerms;

    List<Integer> termsList;
    List<Character> operatorList;

    //strings to store the question and answer
    String questionString,answerString;

    public QuestionGenerator(String difficulty){

        this.difficulty = difficulty;

        //initializing the difficulty level
        switch (difficulty){

            case "Novice" : {
                difficultyLevel = 1;
                maxTerms = 2;
            }

            case "Easy" : {
                difficultyLevel = 2;
                maxTerms = 3;
            }

            case "Medium" : {
                difficultyLevel = 3;
                maxTerms = 4;
            }

            case "Guru" : {
                difficultyLevel = 4;
                maxTerms = 6;
            }

        }

        //initializing a list of numbers
        termsList = new ArrayList<>();

        //initializing a list of operators
        operatorList = new ArrayList<>();

        //Generate the numbers depending on the difficulty
        generateNumberList(termsList , maxTerms);

        //Generate list of operators
        generateOperatorList(operatorList , maxTerms);

        //generate the question and answer
        generateQuestion();
    }

    public String[] generateQuestion(){

        switch (difficulty){
            case "Novice" : {
                int no1=2,no2 = 3;
                questionString = String.valueOf(no1) + " " + operatorList.get(0) + " " + String.valueOf(no2);
                Integer answer = no1+ operatorList.get(0) + no2;
                answerString = String.valueOf(answer);
                return new String[] {questionString , answerString};
            }

            case "Easy" : {
                return  new String[] {"Easy" , "oi"};
            }

            case "Medium" : {
                return new String[] {"Medium" , "oi"};
            }

            case "Guru" : {
                return new String[] {"Duru" , "oi"};
            }

            default: break;
        }

        return null;
    }

    //generating the operator
    public char generateOperator(){

        Random random = new Random();
        int operator = random.nextInt(3);

        switch (operator){
            case 0 : return '+';

            case 1 : return '-';

            case 2 : return  '*';

            case 3 : return '/';
        }
        return '0';
    }

    //generating random number
    public int generateRandomNumber(){

        Random random = new Random();

        //999 is the maximum and 1 is the minimum
        int number = random.nextInt(99) + 1;

        return number;

    }

    //generating numbers to be used in the expression
    public void generateNumberList(List termList , int maximumTerms){

        for (int i = 0; i < maximumTerms ; i++){
            termList.add(generateRandomNumber());
        }

    }

    //generating operators to be used in the expression
    public void generateOperatorList(List operatorList , int maximumTerms){

        for (int i = 0; i < (maximumTerms-1) ; i++){
            operatorList.add(generateOperator());
        }

    }
}
