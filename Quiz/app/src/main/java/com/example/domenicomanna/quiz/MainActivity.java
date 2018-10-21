package com.example.domenicomanna.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String NUMBER_OF_ANSWERS_RIGHT = "number of answers right";
    private static final String DISABLE_TRUE_AND_FALSE_BUTTONS = "disable true and false buttons";
    private static final String RIGHT_ANSWER_FEEDBACK = "Good job!";
    private static final String WRONG_ANSWER_FEEDBACK = "That is wrong.";
    private static final String RETAKE_QUIZ = "Retake the quiz to score higher!";
    private static final String NICE_SCORE = "You scored well! Nice!";

    private Button btn_True;
    private Button btn_False;
    private ImageButton btn_next;
    private TextView text_question;
    private int currentIndex = 0;
    private int numberOfCorrectAnswers = 0;
    private Question [] questions = new Question [] {
        (new Question(R.string.question_2add2, true)),
        (new Question(R.string.question_2into2, true)),
        (new Question(R.string.question_divide_by_zero, false)),
        (new Question(R.string.question_subtract5, true)) };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Called");
        setContentView(R.layout.activity_main);
        btn_True = findViewById(R.id.btn_true);
        btn_False = findViewById(R.id.btn_false);
        btn_next = findViewById(R.id.btn_next);
        text_question = findViewById(R.id.question_display);
        restorePreviousData(savedInstanceState);
        changeQuestion(currentIndex);
        configureClickListeners();
    }

    /**
     *
     * @param savedInstanceState holds the data that will be restored
     */
    private void restorePreviousData(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            numberOfCorrectAnswers = savedInstanceState.getInt(NUMBER_OF_ANSWERS_RIGHT, 0);
            boolean trueAndFalseButtonsShouldBeDisabled = savedInstanceState.getBoolean(DISABLE_TRUE_AND_FALSE_BUTTONS, false);
            if (trueAndFalseButtonsShouldBeDisabled){
                disableTrueAndFalseButtons();
            }

        }
    }

    /**
     * Activate click listeners for all buttons
     */
    private void configureClickListeners() {
        btn_True.setOnClickListener((View v) -> checkIfAnswerIsTrue());
        btn_False.setOnClickListener((View v) -> checkIfAnswerIsFalse());
        btn_next.setOnClickListener((View v) -> goToNextQuestion());
    }

    /**
     * Checks if the answer of the current question is true
     */
    private void checkIfAnswerIsTrue() {
        Question current = questions[currentIndex];
        boolean questionIsTrue = current.getAnswer();
        if (questionIsTrue){
            numberOfCorrectAnswers++;
            makeToast(RIGHT_ANSWER_FEEDBACK);
            disableTrueAndFalseButtons();
        }
        else {
            makeToast(WRONG_ANSWER_FEEDBACK);
            disableTrueAndFalseButtons();
        }
    }

    /**
     * Checks if the answer of the current question is false
     */
    private void checkIfAnswerIsFalse() {
        Question current = questions[currentIndex];;
        boolean answer = current.getAnswer();
        if (!answer){
            numberOfCorrectAnswers++;
            makeToast(RIGHT_ANSWER_FEEDBACK);
            disableTrueAndFalseButtons();
        }
        else {
            makeToast(WRONG_ANSWER_FEEDBACK);
            disableTrueAndFalseButtons();
        }
    }

    /**
     * Renders the true and false buttons un-clickable
     */
    private void disableTrueAndFalseButtons() {
        btn_True.setEnabled(false);
        btn_False.setEnabled(false);
    }

    /**
     * Renders the true and false buttons clickable
     */
    private void reactivateTrueAndFalseButtons() {
        btn_True.setEnabled(true);
        btn_False.setEnabled(true);
    }

    /**
     * Goes to the next question
     */
    private void goToNextQuestion() {
        reactivateTrueAndFalseButtons();
        currentIndex++;
        if (currentIndex == questions.length){
            currentIndex = 0;
            showScoreReport();
            numberOfCorrectAnswers = 0;
        }
        changeQuestion(currentIndex);
    }

    /**
     *
     * @param currentIndex the index of the question array
     */
    private void changeQuestion(int currentIndex) {
        Question question = questions[currentIndex];
        text_question.setText(question.getResStringQuestion());
    }

    /**
     * A toast message showing the number of correct answers out of the total number
     * of questions
     */
    private void showScoreReport() {
        int numberOfQuestionsRightAdjusted = numberOfCorrectAnswers;
        if (numberOfCorrectAnswers >questions.length){
            numberOfQuestionsRightAdjusted = questions.length;
        }
        String toDisplay = String.format("%d/%d right!", numberOfQuestionsRightAdjusted, questions.length);
        if (numberOfCorrectAnswers < 2){
            makeToast(toDisplay + " " + RETAKE_QUIZ);
        }
        else {
            makeToast(toDisplay + " " + NICE_SCORE);
        }
    }

    /**
     *
     * @param message The message to be displayed on screen
     */
    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: Called");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: Called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: Called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: Called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: Called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: Called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState: Called");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        savedInstanceState.putInt(NUMBER_OF_ANSWERS_RIGHT, numberOfCorrectAnswers);
        if (btn_True.isEnabled()) {
            savedInstanceState.putBoolean(DISABLE_TRUE_AND_FALSE_BUTTONS, false);
        }
        else {
            savedInstanceState.putBoolean(DISABLE_TRUE_AND_FALSE_BUTTONS, true);
        }
    }
}
