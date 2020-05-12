package com.example.cecs;

import android.provider.BaseColumns;

/**
 * QuizContract Class
 * Object that gives the layout of the question table with the given variables
 */
public final class QuizContract {

    /**
     * default constructor
     */
    private QuizContract() {}

    /**
     * QuestionsTable class
     * Constructor for the questions table.
     */
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
