package com.example.cecs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.cecs.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * QuizDBHelper Class
 * an object that creates the database table that contains the questions for the quiz
 * this class contains functions that help manipulate the table (such as adding values into the table, update the table, etc.)
 * it also contains a function that returns the list of the questions in the table.
 */
public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LeagueOfLegendsQuiz.db";
    private static final int DATABASE_VERSION = 4;

    private SQLiteDatabase db;

    /**
     * QuizDBHelper Constructor
     * takes in the context
     * uses the DATABASE_NAME and the DATABASE_VERSION
     * @param context
     */
    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate method
     * Takes in the SQLiteDatabase db
     * Executes String that contains a query to create the table
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        // String that is a query that creates a table in the database
        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" + ")";

        // executes the query
        db.execSQL(SQL_CREATE_QUESTION_TABLE);

        // fills the database with questions, options, and answer
        fillQuestionsTable();
    }

    /**
     * onUpgrade method
     * when there is a change in the database table (such as inserting data, removing data, etc.)
     * this function Drops the table and calls the onCreate method again
     * For this to work properly, we have to increment the version manually.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * fillQuestionsTable method
     * Create new Question object for each question we have and calls them the addQuestion function to add the question object to the table.
     */
    private void fillQuestionsTable() {
        Question q1 = new Question("What's the continent where the League of Legends resides called?", "Valoran", "Pandaria", "Northrend", 1);
        addQuestion(q1);
        Question q2 = new Question("Who bears the title of \"The Thunder's Roar\"?", "Kennen", "Volibear", "Rengar", 2);
        addQuestion(q2);
        Question q3 = new Question("Where did Ziggs first used his all famous Hexplosives?", "Lab invasion in Piltover", "Noxus Raid", "Prison Break in Zaun", 3);
        addQuestion(q3);
        Question q4 = new Question("Who is Kayle's Sister?", "Morgana", "Katarina", "Casseopeia", 1);
        addQuestion(q4);
        Question q5 = new Question("Who is the champion Tryndamere married to in \"League of Legends?\"", "Ashe", "Lux", "Kayle", 1);
        addQuestion(q5);
        Question q6 = new Question("What keeps Gragas' drink always at the perfect serving temperature?", "A Freljordian chilling spell", "An iceball from Nunu", "A never melting shard of ice", 3);
        addQuestion(q6);
        Question q7 = new Question("What is the college academy in Zaun called?", "Zaun's School of Robotics and Machinery", "Zaun's College of Techmaturgy", "Zaun's School of Machinery and Robotics", 2);
        addQuestion(q7);
        Question q8 = new Question("What does the name Zac stand for?", "Zaun Anthropomorphic Combatant", "Zaun Amphibolic Contender", "Zaun Amorphous Combatant", 3);
        addQuestion(q8);
        Question q9 = new Question("Which faction is Kindred from?", "Shadow Isles", "Demacia", "None of the above", 3);
        addQuestion(q9);
        Question q10 = new Question("Who is related to Katarina?", "Casseopeia", "Caitlyn", "Kayle", 1);
        addQuestion(q10);
        Question q11 = new Question("According to the Journal of Justice, police speculated that Kassadin's daughter was kidnapped by which champion?", "Miss Fortune", "Malzahar", "Cho Gath", 2);
        addQuestion(q11);
        Question q12 = new Question("Who was the war commander during Noxus' invasion at Ionia?", "Talon", "Darius", "Riven", 3);
        addQuestion(q12);
        Question q13 = new Question("What did Fizz do to make the people of Bilgewater accept him?", "Saved the city from the beast.", "Saved the city from Noxus.", "Killed a wanted pirate.", 1);
        addQuestion(q13);
        Question q14 = new Question("What was the magic that Ryze learned called??", "Dark Magic", "Thorn Magic", "Rune Magic", 2);
        addQuestion(q14);
        Question q15 = new Question("Who has a crush on Vayne?", "Jayce", "Garen", "Dr.Mundo", 3);
        addQuestion(q15);
        Question q16 = new Question("Which tribe do the \"League of Legends\" champions Anivia, Braum, Gragas, Nunu and Tryndamere belong to?", "Avarosan", "Frostgaurd", "Winter’s Claw", 1);
        addQuestion(q16);
        Question q17 = new Question("Who joins Serylda and Avarosa as the third sister in the \"League of Legends\" Three Sisters triad?", "Ashe", "Lissandra", "Sejuani", 2);
        addQuestion(q17);
        Question q18 = new Question("Which \"League of Legends\" character leads the Winter's Claw tribe?", "Ashe", "Lissandra", "Sejuani", 3);
        addQuestion(q18);
        Question q19 = new Question("In \"League of Legends\" Cassiopeia is half woman and half what creature?", "Snake", "Lion", "Horse", 1);
        addQuestion(q19);
        Question q20 = new Question("What was the \"League of Legends\" character Zyra before transferring herself into human form?", "Ancient Snake", "Ancient Plant", "Ancient Owl", 2);
        addQuestion(q20);
        Question q21 = new Question("Which \"League of Legends\" character is known as the Unforgiven?", "Riven", "Akali", "Yassuo", 3);
        addQuestion(q21);
        Question q22 = new Question("Which \"League of Legends\" champion was born in the Voodoo Lands?", "Annie", "Malzahar", "Ivern", 1);
        addQuestion(q22);
        Question q23 = new Question("What is Garen's last name?", "Lanceguard", "Crownguard", "Vanguard", 2);
        addQuestion(q23);
        Question q24 = new Question("Who says \"We'll bring them pain.\"", "Brand", "Evelynn", "Morgana", 3);
        addQuestion(q24);
        Question q25 = new Question("Who says \"The cycle of life and death continues. We will live, they will die.\"", "Nasus", "Karthus", "Irelia", 1);
        addQuestion(q25);
        Question q26 = new Question("Who says \"Don't you trust me?\"", "Zyra", "Ahri", "Annie", 2);
        addQuestion(q26);
        Question q27 = new Question("Who says \"The balance of power must be preserved.\"", "Darius", "Riven", "Kassadin", 3);
        addQuestion(q27);
        Question q28 = new Question("What is Gragas' special drink called?", "Graggy Ice", "Graggy Booze", "Magic Grog", 1);
        addQuestion(q28);
        Question q29 = new Question("Which champion is the owner of a Sunscreen brand? ", "Singed", "Taric", "Ezreal", 2);
        addQuestion(q29);
        Question q30 = new Question("What is Demacia's special force that is in charge of protecting the monarchs called?", "The Defender of Justice", "The Valorants", "The Daunting Vanguard", 3);
        addQuestion(q30);
    }

    /**
     * addQuestion method
     * takes in a question object
     * Create a new ContentValues object cv
     * put the question, options, answer number into respective columns
     * insert the ContentValues into the table
     * @param question
     */
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNumber());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    /**
     * getAllQuestion method
     * read the database
     * store the executed query to get all data from the table into the Cursor
     * iterate through the cursor and add each set of questions and its options and answers into the list
     * Return the list.
     * @return
     */
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
