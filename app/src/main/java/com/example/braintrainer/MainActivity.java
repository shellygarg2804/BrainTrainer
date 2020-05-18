package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    TextView resulttextview;
    TextView sumtextview;
    TextView pointtextview;
    TextView timertext;
    int locationofcorrectanswer;
    int score=0;
    int totalquestions=0;
    Button button0 ;
    Button button1;
    Button button2;
    Button button3;
    Button PlayAgainButton;

    ConstraintLayout mainScreen;

    public void generateQuestion(){

        Random random= new Random();
        int a= random.nextInt(21);
        int b= random.nextInt(21);
        sumtextview.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationofcorrectanswer=random.nextInt(4);

        int incorrectanswer;
        answers.clear();

        for(int i=0;i<4;i++){
            if(i==locationofcorrectanswer){
                answers.add(a+b);
            }
            else{

                incorrectanswer=random.nextInt(41);
                while(incorrectanswer==a+b){

                    incorrectanswer=random.nextInt(41);

                }
                answers.add(incorrectanswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }






    public void chooseAnswer(View view) {


        if (view.getTag().toString().equals(Integer.toString(locationofcorrectanswer)))
        {
            score++;
            resulttextview.setText("CORRECT!");
            resulttextview.setVisibility(View.VISIBLE);
        }
        else {
            resulttextview.setText("WRONG!");
            resulttextview.setVisibility(View.VISIBLE);
        }

        totalquestions++;
        pointtextview.setText(score+"/"+totalquestions);
        generateQuestion();


    }


    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        mainScreen.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){

        score=0;
        totalquestions=0;
        timertext.setText("30s");
        resulttextview.setVisibility(View.INVISIBLE);
        pointtextview.setText("0/0");

        PlayAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30000+300,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timertext.setText((millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timertext.setText("0s");
                resulttextview.setText("YOUR SCORE: "+ (score+"/"+totalquestions));
                resulttextview.setVisibility(View.VISIBLE);
                PlayAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton =(Button) findViewById(R.id.GoButton);
        button0= (Button)findViewById(R.id.button0);
        button1= (Button)findViewById(R.id.button1);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        resulttextview= (TextView)findViewById(R.id.resulttextview);
        sumtextview= (TextView)findViewById(R.id.sumTextView) ;
        pointtextview=(TextView)findViewById(R.id.scoretext);
        timertext=(TextView)findViewById(R.id.timertext);
        PlayAgainButton= (Button)findViewById(R.id.playagain);
        mainScreen=findViewById(R.id.mainScreen);

        playAgain(findViewById(R.id.playagain));


    }


}
