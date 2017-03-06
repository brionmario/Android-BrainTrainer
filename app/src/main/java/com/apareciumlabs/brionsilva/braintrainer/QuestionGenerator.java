package com.apareciumlabs.brionsilva.braintrainer;

/**
 * Created by brionsilva on 07/03/2017.
 */

public class QuestionGenerator {

    public String difficulty;

    public QuestionGenerator(String difficulty){
        this.difficulty = difficulty;
    }

    public String generateQuestion(){

        switch (difficulty){
            case "Novice" : {

                return "Novice";
            }

            case "Easy" : {
                return "Easy";
            }

            case "Medium" : {
                return "Medium";
            }

            case "Guru" : {
                return "Guru";
            }

            default: break;
        }

        return null;
    }
}
