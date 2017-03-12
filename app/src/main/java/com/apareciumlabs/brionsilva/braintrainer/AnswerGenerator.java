package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * This class can generate answers for arithmetic expressions that has a maximum of 6 integers.
 * and it can be very easily adjusted to be used for more complex arithmetic expressions.
 *
 * @author  Brion Mario
 * @version 1.0
 * @since   2017-03-08
 */

public class AnswerGenerator {

    private String difficulty;
    private int[] answer;
    ArrayList<Integer> arrayList;

    /**
     * Constructor assigns the difficulty when an object is made
     * @param difficulty Preferred difficulty of the user
     */
    public AnswerGenerator(String difficulty){

        this.difficulty = difficulty;

    }

    /**
     * This function generates the answer for an arithmetic expression which has a maximum of 6 integers
     * and which contains the basic operators + , - , *  and /
     *
     * @param termsList The list of integers in the arithmetic expression.
     * @param operatorList The list of operators in the arithmetic expression.
     * @param numTerms Number of integers in the arithmetic expression.
     * @return Returns the final answer as an integer whoever calls the method.
     */
    public int generateAnswer(List<Integer> termsList , List<Character> operatorList , int numTerms){

        arrayList = new ArrayList<>();
        int finalAnswer = 0;

        switch (difficulty) {
            case "Novice": {
                answer = new int[numTerms - 1];
                answer[0] = Operation(termsList.get(0) , operatorList.get(0) , termsList.get(1) );
                finalAnswer = answer[0];
                break;
            }
            case "Easy": {

                if (numTerms == 2) {

                    answer = new int[numTerms - 1];
                    answer[0]  = Operation(termsList.get(0) , operatorList.get(0), termsList.get(1) );

                    finalAnswer = answer[0];

                }else if(numTerms == 3){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));

                    finalAnswer = answer [1];

                }

                break;
            }
            case "Medium": {

                if (numTerms == 2) {

                    answer = new int[numTerms - 1];
                    answer[0]  = Operation(termsList.get(0) , operatorList.get(0), termsList.get(1) );

                    finalAnswer = answer[0];

                }else if(numTerms == 3){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));

                    finalAnswer = answer [1];

                }else if (numTerms == 4){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));

                    finalAnswer = answer [2];

                }

                break;
            }
            case "Guru" :{

                if (numTerms == 4){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));

                    finalAnswer = answer [2];

                } else if(numTerms == 5){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));
                    answer[3] = Operation(answer[2] , operatorList.get(3) , termsList.get(4));

                    finalAnswer = answer [3];

                } else if(numTerms == 6){

                    answer = new int[numTerms - 1];
                    answer[0] = Operation(termsList.get(0), operatorList.get(0) , termsList.get(1) );
                    answer[1] = Operation(answer[0] , operatorList.get(1) , termsList.get(2));
                    answer[2] = Operation(answer[1] , operatorList.get(2) , termsList.get(3));
                    answer[3] = Operation(answer[2] , operatorList.get(3) , termsList.get(4));
                    answer[4] = Operation(answer[3] , operatorList.get(4) , termsList.get(5));

                    finalAnswer = answer [4];

                }

                break;
            }
        }
        return finalAnswer;
    }

    /**
     * This function will evaluate two numbers with different operators.
     *
     * @param num1 The first operand
     * @param op The operator that does the calculation
     * @param num2 The second operand
     * @return returns the result of the operation 
     */
    public int Operation (int num1 ,char op, int num2){

        int cal = 0;

        switch (op){
            case '+' : {
                cal = num1 + num2;
                arrayList.add(cal);
                break;
            }
            case '-' : {
                cal = num1 - num2;
                arrayList.add(cal);
                break;
            }
            case '*' : {
                cal = num1 * num2;
                arrayList.add(cal);
                break;
            }
            case '/' : {
                cal = num1 / num2;
                arrayList.add(cal);
                break;
            }
        }

        return cal;
    }

}
