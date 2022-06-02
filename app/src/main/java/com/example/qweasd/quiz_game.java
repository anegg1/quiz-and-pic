package com.example.qweasd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;
public class quiz_game extends AppCompatActivity {
    Button startBtn;
    TextView TimeTextView;
    TextView ScoreTextView;
    TextView AlertTextView;
    TextView QuestionTextView;
    TextView FinalScoreTextView;
    Button button0;
    Button button5;
    Button button1;
    Button button2;
    int maxtime = 30000;
    Button button3;
    CountDownTimer countDownTimer;
    ConstraintLayout constraintLayout;
    ConstraintLayout lastLayout;
    Button buttonPlayAgain;
    Button resultTable;


    Random random =new Random();
    int a;
    int b;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int totalQuestions = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);
        startBtn=(Button)findViewById(R.id.btnStart);

        TimeTextView = findViewById(R.id.TimeTextView);
        ScoreTextView = findViewById(R.id.ScoreTextView);
        FinalScoreTextView=findViewById(R.id.FinalscoreTextView);

        QuestionTextView =findViewById(R.id.QuestionTextView);


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonPlayAgain=findViewById(R.id.buttonPlayAgain);

        constraintLayout=findViewById(R.id.quizUi);
        lastLayout=findViewById(R.id.lastUi);

        lastLayout.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.INVISIBLE);






    }

    @SuppressLint("SetTextI18n")
    public void NextQuestion(){
        a = random.nextInt(10);
        b = random.nextInt(10);
        int random1 = random.nextInt(4);

        switch (random1) {
            case 1:
                QuestionTextView.setText(a + "+" + b);
                indexOfCorrectAnswer = random.nextInt(4);
                answers.clear();
                for (int i = 0; i < 4; i++) {

                    if (indexOfCorrectAnswer == i) {
                        answers.add(a + b);
                    } else {
                        int wrongAnswer = random.nextInt(20);
                        while (wrongAnswer == a + b) {

                            wrongAnswer = random.nextInt(20);
                        }
                        answers.add(wrongAnswer);
                    }
                }
                break;

            case 2:
                QuestionTextView.setText(a + "-" + b);
                indexOfCorrectAnswer = random.nextInt(4);
                answers.clear();
                for (int i = 0; i < 4; i++) {

                    if (indexOfCorrectAnswer == i) {
                        answers.add(a - b);
                    } else {
                        int wrongAnswer = random.nextInt(20);
                        while (wrongAnswer == a - b) {

                            wrongAnswer = random.nextInt(20);
                        }
                        answers.add(wrongAnswer);
                    }
                }
                break;
            case 3:
                QuestionTextView.setText(a + "*" + b);
                indexOfCorrectAnswer = random.nextInt(4);
                answers.clear();
                for (int i = 0; i < 4; i++) {

                    if (indexOfCorrectAnswer == i) {
                        answers.add(a * b);
                    } else {
                        int wrongAnswer = random.nextInt(20);
                        while (wrongAnswer == a * b) {

                            wrongAnswer = random.nextInt(20);
                        }
                        answers.add(wrongAnswer);
                    }
                }
                break;

            case 4:
                QuestionTextView.setText(a + "/" + b);
                indexOfCorrectAnswer = random.nextInt(4);
                answers.clear();
                for (int i = 0; i < 4; i++) {

                    if (indexOfCorrectAnswer == i) {
                        answers.add(a / b);
                    } else {
                        int wrongAnswer = random.nextInt(20);
                        while (wrongAnswer == a / b) {

                            wrongAnswer = random.nextInt(20);
                        }
                        answers.add(wrongAnswer);
                    }
                }
                break;

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @SuppressLint("SetTextI18n")
    public void optionSelect(View view){
        totalQuestions++;
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            points++;



        }else {

        }

        ScoreTextView.setText(points +"/"+ totalQuestions);

        NextQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view){
        points=0;
        totalQuestions=0;
        ScoreTextView.setText(points +"/"+ totalQuestions);
        countDownTimer.start();
        lastLayout.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);

    }





    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        NextQuestion();
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                TimeTextView.setText("Time Up!");
                FinalScoreTextView.setText(Integer.toString(points));
                constraintLayout.setVisibility(View.INVISIBLE);
                lastLayout.setVisibility(View.VISIBLE);

                button5 = findViewById(R.id.button5);
                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(quiz_game.this, menu_game.class);
                        startActivity(intent);
                    }
                });



            }
        }.start();


    }
}