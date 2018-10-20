package com.example.domenicomanna.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";

    private Button btn_True;
    private Button btn_False;
    private ImageButton btn_next;
    private ImageButton btn_previous;
    private TextView text_question;
    private int currentIndex = 0;
    private Question [] questions = new Question [] {
        (new Question(R.string.question_2add2, true)),
        (new Question(R.string.question_2into2, true)),
        (new Question(R.string.question_divide_by_zero, false)),
        (new Question(R.string.question_subtract5, true)) };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Called");

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        setContentView(R.layout.activity_main);

        btn_True = findViewById(R.id.btn_true);
        btn_False = findViewById(R.id.btn_false);
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        text_question = findViewById(R.id.question_display);
        changeText(currentIndex);
        btn_True.setOnClickListener(trueOrFalseListener);
        btn_False.setOnClickListener(trueOrFalseListener);
        btn_next.setOnClickListener(nextOrPreviousListener);
        btn_previous.setOnClickListener(nextOrPreviousListener);
    }



    View.OnClickListener trueOrFalseListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Question current = questions[currentIndex];
            boolean questionIsRight = current.getAnswer();

            if (v.getId() == btn_True.getId() && questionIsRight ){
                makeToast("Good job!!!");
            }

            else if (v.getId() == btn_False.getId() && !questionIsRight){
                makeToast("Good job!!!");
            }
            else {
                makeToast("Try again!");
            }
        }
    };

    private void makeToast(String feedback) {
        Toast.makeText(this, feedback, Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener nextOrPreviousListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: Next or previous clicked");
            if (v.getId() == btn_next.getId()){
                currentIndex++;
                if (currentIndex == questions.length){
                    currentIndex = 0;
                    changeText(0);
                }
            }
            else{
                currentIndex--;
                if (currentIndex == -1){
                    currentIndex = questions.length - 1;
                    changeText(currentIndex);
                }
            }
            changeText(currentIndex);
        }
    };

    private void changeText(int currentIndex) {
        Question question = questions[currentIndex];
        text_question.setText(question.getResStringQuestion());
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
    }
}
