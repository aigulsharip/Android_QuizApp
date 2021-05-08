package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.util.ICUUncheckedIOException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT1 = "com.example.intentexample.EXTRA_TEXT1";
    public static final String EXTRA_TEXT2 = "com.example.intentexample.EXTRA_TEXT2";

    String [] questions = {"Who is the founder of Facebook?", "Which messenger is the most popular?",
            "Which of the suggested logos belongs to the file hosting?", "Who invented the Java?"};
    int[][] pictures = {{R.drawable.answer11, R.drawable.answer12, R.drawable.answer13, R.drawable.answer14},
                         {R.drawable.answer21, R.drawable.answer22, R.drawable.answer23, R.drawable.answer24},
                         {R.drawable.answer31, R.drawable.answer32, R.drawable.answer33, R.drawable.answer34},
                        {R.drawable.answer41, R.drawable.answer42, R.drawable.answer43, R.drawable.answer44},
                        };
    int[] rightAnswers = {R.drawable.answer11, R.drawable.answer23, R.drawable.answer32, R.drawable.answer44};


    TextView questionNumberText, questionText;
    ImageView image1, image2, image3, image4;
    ArrayList<ImageView> images;
    Button nextBtn, finish;
    int currentQuestion = 0;
    // Task 2: counters of correct and wrong answers
    int correctAnswers = 0;
    int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        questionNumberText = findViewById(R.id.questionNumberText);
        questionText = findViewById(R.id.questionText);
        nextBtn = findViewById(R.id.nextBtn);



        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(image1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(image2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(image3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(image4);
            }
        });

        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpNextQuestion();
            }
        });
        nextBtn.setEnabled(false);
        setUpNextQuestion();
    }
    // Task 3: onClickFinishTest allows to show the number of correct and wrong answer in new window
    private void onClickFinishTest() {
        //int number = Integer.parseInt(editText2.getText().toString());
        int correct = correctAnswers;
        int wrong = wrongAnswers;

        Intent intent = new Intent(this, Results.class);

        intent.putExtra(EXTRA_TEXT1, correct);
        intent.putExtra(EXTRA_TEXT2, wrong);

        startActivity(intent);
    }


    public void checkAnswer(ImageView answer) {
        setImageClickable(true);
        nextBtn.setEnabled(true);
        if (answer.getContentDescription()== "answer") {
            answer.setBackgroundColor(Color.parseColor("#46ff97"));
            correctAnswers++;
            setImageClickable(false);

        } else {
            answer.setBackgroundColor(Color.parseColor("#ff6279"));
            wrongAnswers++;
            setImageClickable(false);

        }
    }

    public void setUpNextQuestion() {
        // Task 3: After finishing the test, this function will re-direct to new window
        if (currentQuestion >= questions.length) {
            onClickFinishTest();
        }
        /*
        //This fragment for showing pop-up dialog alert when the test finish
        if (currentQuestion >= questions.length) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thank you").
                    setMessage("You have finished the test. The number of correct answers " + correctAnswers + ". The number of wrong answers " + wrongAnswers).
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            currentQuestion = 0;
                            setUpNextQuestion();
                            //onClickStartDialog();
                        }
                    }).show();
            return;
        }

         */

        setImageClickable(true);
        nextBtn.setEnabled(false);
        questionText.setText(questions[currentQuestion]);
        questionNumberText.setText("Question " + (currentQuestion+1));
        for (int i = 0; i < pictures[currentQuestion].length; i++) {
            Drawable a = getDrawable(pictures[currentQuestion][i]);
            images.get(i).setImageDrawable(a);
            images.get(i).setBackgroundColor(Color.parseColor("#00ffffff"));

            if (rightAnswers[currentQuestion] == pictures[currentQuestion][i]) {
                images.get(i).setContentDescription("answer");

            } else {
                images.get(i).setContentDescription("");
            }
        }
        currentQuestion++;
    }

    public void setImageClickable(boolean flag) {
        image1.setClickable(flag);
        image2.setClickable(flag);
        image3.setClickable(flag);
        image4.setClickable(flag);
    }
}