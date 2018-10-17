package com.example.domenicomanna.quiz;

public class Question {
    private int resStringQuestion;
    private boolean answer;
    public Question(int resStringQuestion, boolean answer) {
        this.resStringQuestion = resStringQuestion;
        this.answer = answer;
    }

    public int getResStringQuestion() {
        return resStringQuestion;
    }

    public void setResStringQuestion(int resStringQuestion) {
        this.resStringQuestion = resStringQuestion;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
