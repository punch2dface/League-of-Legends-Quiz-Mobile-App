package com.example.cecs;

/**
 *  Question java class
 *  A class where question, question options, and question answers are stored into a Question Object
 *  This class contains a default constructor, overloaded constructor, getter and setters for each variable {question, option1, option2, option3, answerNumber}
 */
public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answerNumber;

    /**
     * Default Constructor for Question Object
     */
    public Question() {}

    /**
     * Overloaded Question Constructor that initializes the parameters as the question, option1, option2, option3, and answerNumber
     * @param question
     * @param option1
     * @param option2
     * @param option3
     * @param answerNumber
     */
    public Question(String question, String option1, String option2, String option3, int answerNumber) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNumber = answerNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
