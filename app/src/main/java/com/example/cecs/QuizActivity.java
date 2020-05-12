package com.example.cecs;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * QuizActivity
 * An activity where the quiz component takes place.
 * This activity contains the timer, question count, question, options, and a button to confirm option selection
 */
public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView tvQuestion;
    private TextView tvQuestionCount;
    private TextView tvCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button btnConfirmNext;

    private ColorStateList textColorDefaultRB;
    private ColorStateList textColorDefaultCD;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    /**
     * onCreate method
     * get UI from the activity,
     * get the text color of the radial buttons
     * get the text color of the textViewCountDown
     * create a QuizDBHelper object,
     * get the questions in the QuizDBHelper object and store it in a list
     * get the size of the questionList
     * shuffle the list
     * call showNextQuestion method to get the first question from the list
     * this activity has a button that when clicked, checks your selection and shows the result of the question.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // get UI by their ID
        tvQuestion = findViewById(R.id.textViewQuestion);
        tvQuestionCount = findViewById(R.id.textViewQuestionCount);
        tvCountDown = findViewById(R.id.textViewCountDown);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        btnConfirmNext = findViewById(R.id.confirmBtn);

        // get text colors for both Radial Buttons and Count Down Timer
        textColorDefaultRB = rb1.getTextColors();
        textColorDefaultCD = tvCountDown.getTextColors();

        // Declare and Initialize a new QuizDBHelper object
        QuizDBHelper dbHelper = new QuizDBHelper(this);

        // Store questions from the QuizDBHelper object into the questionList list
        questionList = dbHelper.getAllQuestions();

        // Get max number of questions in the questionList
        questionCountTotal = questionList.size();

        // Shuffle the questions in the questionList
        Collections.shuffle(questionList);

        // Show question
        showNextQuestion();

        /** btnConfirmNext button on click listener
         *  when button is clicked, checks whether the radial buttons are clicked or not
         *      if a radial option is clicked, call the checkAnswer method.
         *      else prompts a toast message to select one of the options before clicking the next button
         *  When a user clicks on one of the radial options and the answer is displayed through text color either being green or red,
         *  the user clicks the button again to go to the next question.
         */
        btnConfirmNext.setOnClickListener(new View.OnClickListener(){

            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v){
                if(!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please Select an Answer", Toast.LENGTH_LONG);
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    /**
     * showNextQuestion method
     * when called, set radial button option to the default color, clear any selections on the radial group,
     * checks if there are questions to be asked from the shuffled questionList.
     *      if there is, then get and set the question, set the radial button text to the options for the pertaining question,
     *         increment the question counter, update the question count textfield, change the answered boolean to false,
     *         set the text of the btnConfirmNext button to confirm, set the timer back to 30 seconds, and start the count down
     *      else, finish the quiz
     */
    private void showNextQuestion() {
        // resets radial options color to default color
        rb1.setTextColor(textColorDefaultRB);
        rb2.setTextColor(textColorDefaultRB);
        rb3.setTextColor(textColorDefaultRB);

        // resets radial group selection
        rbGroup.clearCheck();

        // checks if there are more questions to offer
        if (questionCounter < questionCountTotal){
            // get the next incremented question from the questionList
            currentQuestion = questionList.get(questionCounter);

            // set question TextField to next question
            tvQuestion.setText(currentQuestion.getQuestion());

            // set radial button text to the options of the next question
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            // increment the question counter
            questionCounter++;

            // set the question counter textfield
            tvQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);

            // set the answered boolean to false
            answered = false;

            // set the btnConfirmNext button's text to confirm
            btnConfirmNext.setText("Confirm");

            // set the count down timer
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;

            // start the count down timer
            startCountDown();
        } else {
            // if there are no questions to ask, call finishQuiz method.
            finishQuiz();
        }
    }

    /**
     * startCountDown method
     * create new count down timer with starting time and decrement it by 1000 milliseconds.
     * Contains onTick method and onFinish method
     *      onTick method is called for every decrement of timeLeftInMillis
     *      onFinish method is called when the count down timer reaches zero
     */
    private void startCountDown() {
        // create new CountDownTimer object with timeLeftInMillis and decrement it by 1000 and start it.
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            /**
             * onTick method
             * decrement timeLeftInMillis byt miilisUntilFinished and update count down text.
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            /**
             * onFinish method
             * set timeLeftInMillis to 0
             * call updateCountDownText
             * call checkAnswer method.
             */
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    /**
     *  updateCountDownText method
     *  get the minute and second, format them into a string, set the text of tvCountDown to the formatted string
     *  check if timer is above 10 seconds, if it is under 10 seconds change the color of tvCountDown to red
     */
    private void updateCountDownText() {
        // get minutes
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        // get seconds
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        // format the minutes and seconds into strings
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        // set the tvCountDown text to the timeformatted
        tvCountDown.setText(timeFormatted);

        // if timer is less than 10 seconds, change the color of tvCountDown to red, else keep it default color.
        if (timeLeftInMillis < 10000) {
            tvCountDown.setTextColor(Color.RED);
        } else {
            tvCountDown.setTextColor(textColorDefaultCD);
        }
    }

    /**
     * checkAnswer method
     * set boolean answered to true, cancel timer, get the selected radial button, check if the radial button matches to the getAnswerNumber from the DB
     * if it matches then increment score by 1
     * Show the solution.
     */
    private void checkAnswer() {
        // set boolean answered to true
        answered = true;
        // cancel countDownTimer
        countDownTimer.cancel();

        // get the radiobutton that has been selected by the getCheckedRadioButtonId
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());

        // get the answerNumber which is the index of the child of the radio group + 1
        int answerNumber = rbGroup.indexOfChild(rbSelected) + 1;

        // if the selected radio button is = to the getAnswerNumber then increase the score by 1
        if(answerNumber == currentQuestion.getAnswerNumber()) {
            score++;
        }

        // call the showSolution method.
        showSolution();
    }

    /**
     * showSolution method
     * after a user selects and a radial button option and clicks the confirm button or when the timer ticks down to zero,
     * set the radial options text to red, change the one that is the correct answer to green and depending on how many questions are left
     * change the text of the button to next or finish.
     */
    private void showSolution() {
        // set text color for all radial option to red
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        // change the text color for the correct radial button to green
        switch (currentQuestion.getAnswerNumber()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }

        // if there are more question available set the button text for the btnConfirmNext to Next, else set the text to finish
        if (questionCounter < questionCountTotal) {
            btnConfirmNext.setText("Next");
        } else {
            btnConfirmNext.setText("Finish");
        }
    }

    /**
     * finishQuiz method
     * when a user answers all questions available, the quiz finishes which calls this function,
     * in this method, it creates a new intent from this activity to the result activity,
     * puts the score and question count total into the intent,
     * adds a flag to the intent,
     * starts the activity of the intent
     * and destroys this activity.
     */
    public void finishQuiz() {
        // create a new intent from this activity to the result activity
        Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
        // put the score into the intent
        resultIntent.putExtra(EXTRA_SCORE, score);
        // put the question count total into the intent
        resultIntent.putExtra("totalQuestion", questionCountTotal);
        // add a flag to the intent
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        // start the activity
        startActivity(resultIntent);
        // destroy this activity
        finish();
    }

    /**
     * onBackPressed method
     * finishes the quiz when the user presses the back button twice with 2 sec interval.
     */
    @Override
    public void onBackPressed() {
        // if back button is pressed again within 2 seconds
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            // finish the quiz
            finishQuiz();
        } else  {
            // prompt a toast message that says to press it again to finish
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        // get the time after the back button is pressed.
        backPressedTime = System.currentTimeMillis();
    }

    /**
     * onDestroy method
     * when activity is destroyed, it first checks if the count down timer is not null
     * if the count down timer is not null, then cancel the timer
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // if count down timer is not null cancel it
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
