package com.example.domenicomanna.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Question> questions;
    private Button btn_True;
    private Button btn_False;
    private Button btn_next;
    private Button btn_previous;
    private TextView text_question;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = new ArrayList<>();
        addQuestions();

        btn_True = findViewById(R.id.btn_true);
        btn_False = findViewById(R.id.btn_false);
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        text_question = findViewById(R.id.question_display);

        btn_True.setOnClickListener(trueOrFalseListener);
        btn_False.setOnClickListener(trueOrFalseListener);
        btn_next.setOnClickListener(nextOrPreviousListener);
        btn_previous.setOnClickListener(nextOrPreviousListener);
    }

    private void addQuestions() {
        questions.add(new Question(R.string.question_2add2, true));
        questions.add(new Question(R.string.question_2into2, true));
        questions.add(new Question(R.string.question_divide_by_zero, false));
        questions.add(new Question(R.string.question_subtract5, true));
    }

    View.OnClickListener trueOrFalseListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Question current = questions.get(currentIndex);
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
                btn_previous.setEnabled(true);
                if (currentIndex == questions.size()-1) btn_next.setEnabled(false);
            }
            else{
                currentIndex--;
                btn_next.setEnabled(true);
                if (currentIndex == 0) btn_previous.setEnabled(false);
            }
            changeText(currentIndex);
        }
    };

    private void changeText(int currentIndex) {
        Question question = questions.get(currentIndex);
        text_question.setText(question.getResStringQuestion());
    }
}
