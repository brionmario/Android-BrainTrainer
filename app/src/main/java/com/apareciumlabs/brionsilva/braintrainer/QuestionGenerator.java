package com.apareciumlabs.brionsilva.braintrainer;

import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class creates question and answered based on the user defined difficulty level.
 *
 * @author  Brion Mario
 * @version 1.0
 * @since   2017-03-08
 */

public class QuestionGenerator {

    public String difficulty;
    public int difficultyLevel;

    public int maxTerms;

    List<Integer> termsList;
    List<Character> operatorList;

    //strings to store the question and answer
    String questionString,answerString;

    /**
     *
     * Constructor that initializes the difficulty and maximum terms and difficulty level when an
     * object is made.
     * P.S : Difficulty level variable isn't used anywhere
     * @param difficulty the difficulty is passed using this parameter
     *
     */
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

    /**
     * This function generates questions and answers based on the difficulty level and returns them
     * as an array.
     *
     * @return Returns question followed by the answer
     */
    public String[] generateQuestion(){

        switch (difficulty){
            case "Novice" : {

                String[] questionNanswer = generateNovice() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Easy" : {
                String[] questionNanswer = generateEasy() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Medium" : {
                String[] questionNanswer = generateMedium() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            case "Guru" : {
                String[] questionNanswer = generateGuru() ;
                return new String[] {questionNanswer[0] , questionNanswer[1]} ;
            }

            default: break;
        }

        return null;
    }

    /**
     * This function randomly generates and returns an operator
     * @return The randomly generated char operator is returned
     */
    public char generateOperator(){

        Random random = new Random();
        int operator = random.nextInt(4);

        switch (operator){
            case 0 : return '+';

            case 1 : return '-';

            case 2 : return  '*';

            case 3 : return '/';
        }
        return '0';
    }

    /**
     * this function randomly generates a number between 1 - 99 and returns it
     * @return Returns the generated random integer
     */
    public int generateRandomNumber(){

        Random random = new Random();

        //99 is the maximum and 1 is the minimum
        int number = random.nextInt(99) + 1;

        return number;

    }

    /**
     * This function checks for the maximum allowed terms per each difficulty leve; and generates a
     * list of integers
     *
     * @param termList List of integers for the expression
     * @param maximumTerms Maximum number of allowed terms per each difficulty level
     */
    public void generateNumberList(List termList , int maximumTerms){

        for (int i = 0; i < maximumTerms ; i++){
            termList.add(generateRandomNumber());
        }

    }

    /**
     * This function checks for the maximum allowed operators per each difficulty level
     * and generates a list of operators. the number of operators in an expression is always
     * maximumTerms-1 .
     *
     * @param operatorList List of operators for the expression
     * @param maximumTerms Maximum number of allowed terms per each difficulty level
     */
    public void generateOperatorList(List operatorList , int maximumTerms){

        for (int i = 0; i < (maximumTerms-1) ; i++){
            operatorList.add(generateOperator());
        }

    }

    /**
     * Generates and returns the question and answer for the "NOVICE" difficulty level.
     * @return Returns the question and answer pair in a String array
     */
    public String[] generateNovice(){

        questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

       /* EvaluateEngine evaluateEngine = new EvaluateEngine();
        Double answer = evaluateEngine.evaluate(questionString);
        answerString = String.valueOf((int) Math.round(answer));*/

        AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
        answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,2));

        return new String[] {questionString,answerString};
    }

    /**
     * Generates and returns the question and answer for the "EASY" difficulty level.
     * @return Returns the question and answer pair in a String array
     */
    public String[] generateEasy(){

        Random random = new Random();
        int number = random.nextInt(2);

        switch (number){
            //if zero, generate a question with two integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,2));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with three integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,3));

                return new String[] {questionString,answerString};

            }
        }

        return null;
    }

    /**
     * Generates and returns the question and answer for the "MEDIUM" difficulty level.
     * @return Returns the question and answer pair in a String array
     */
    public String[] generateMedium(){

        Random random = new Random();
        int number = random.nextInt(3);

        switch (number){
            //if zero, generate a question with two integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,2));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with three integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,3));

                return new String[] {questionString,answerString};

            }
            //if two, generate a question with four integers
            case 2:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)+ " " + operatorList.get(2) + " " + termsList.get(3);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,4));

                return new String[] {questionString,answerString};

            }
        }

        return null;
    }

    /**
     * Generates and returns the question and answer for the "GURU" difficulty level.
     * @return Returns the question and answer pair in a String array
     */
    public String[] generateGuru(){

        Random random = new Random();
        int number = random.nextInt(3);

        switch (number){
            //if zero, generate a question with four integers
            case 0:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)+ " " + operatorList.get(2) + " " + termsList.get(3);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,4));

                return new String[] {questionString,answerString};
            }
            //if one, generate a question with five integers
            case 1:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)
                            + " " + operatorList.get(2) + " " + termsList.get(3)
                                + " " + operatorList.get(3) + " " + termsList.get(4);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,5));

                return new String[] {questionString,answerString};

            }
            //if two, generate a question with six integers
            case 2:{
                questionString = termsList.get(0) + " " + operatorList.get(0) + " " + termsList.get(1)
                        + " " + operatorList.get(1) + " " + termsList.get(2)
                            + " " + operatorList.get(2) + " " + termsList.get(3)
                                + " " + operatorList.get(3) + " " + termsList.get(4)
                                    + " " + operatorList.get(4) + " " + termsList.get(5);

                /*EvaluateEngine evaluateEngine = new EvaluateEngine();
                Double answer = evaluateEngine.evaluate(questionString);
                answerString = String.valueOf((int) Math.round(answer));*/

                AnswerGenerator answerGenerator = new AnswerGenerator(difficulty);
                answerString = String.valueOf(answerGenerator.generateAnswer(termsList,operatorList,6));

                return new String[] {questionString,answerString};

            }

        }

        return null;
    }

}
