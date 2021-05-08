package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Intent intent = getIntent();
        int correct = intent.getIntExtra(MainActivity.EXTRA_TEXT1, 0);
        int wrong = intent.getIntExtra(MainActivity.EXTRA_TEXT2, 0);


        TextView textView1 = (TextView)findViewById(R.id.textview1);
        textView1.setText("You have finished the test. The number of correct answers " + correct + ". The number of wrong answers " + wrong);

    }
}