package com.example.flashcrd_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        //Retrieve data / text from previous activity
        String quesEdit = getIntent().getStringExtra("qEdit");
        String ansEdit = getIntent().getStringExtra("aEdit");
//        String wrgEdit1 = getIntent().getStringExtra("wEdit1");
//        String wrgEdit2 = getIntent().getStringExtra("wEdit2");

        //Set retrieved data / text to editText view on this activity
        ((EditText) findViewById(R.id.questionText)).setText(quesEdit);
        ((EditText) findViewById(R.id.answerText)).setText(ansEdit);
//        ((EditText) findViewById(R.id.wrongAns1)).setText(wrgEdit1);
//        ((EditText) findViewById(R.id.wrongAns2)).setText(wrgEdit2);


        findViewById(R.id.save_Card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Grabbing Text From Edit Text Field & storing values into string variables
                String question = ((EditText) findViewById(R.id.questionText)).getText().toString();
                String answer = ((EditText) findViewById(R.id.answerText)).getText().toString();
//                String wrongOpt1 = ((EditText) findViewById(R.id.wrongAns1)).getText().toString();
//                String wrongOpt2 = ((EditText) findViewById(R.id.wrongAns2)).getText().toString();

                if(question.isEmpty() || answer.isEmpty() ){//|| wrongOpt1.isEmpty() || wrongOpt2.isEmpty()) {
                    //Toast Error Message To User To Populate Both EditText Fields
                    Toast toast = Toast.makeText(getApplicationContext(), "Be Sure To Have Both Question & Answer!", Toast.LENGTH_SHORT);
                    TextView tstView = toast.getView().findViewById(android.R.id.message);
                    tstView.setTextColor(Color.WHITE);
                    tstView.setTypeface(null, Typeface.BOLD);
                    toast.getView().setBackground(getResources().getDrawable(R.drawable.toast));
                    toast.setGravity(0, 1, 600);
                    toast.show();
                }
                else {
                    //Putting Strings Into Intent To Be Passed To Next Activity
                    Intent data = new Intent(); //create a new Intent to store String data
                    data.putExtra("questString", question); //Pass Strings to new Intent, one is Key, other is value
                    data.putExtra("anString", answer); ////Pass Strings to new Intent, one is Key, other is value
//                    data.putExtra("wrongCh1", wrongOpt1);
//                    data.putExtra("wrongCh2", wrongOpt2);
                    setResult(RESULT_OK, data); //set result code and bundle data for response
                    finish(); //Close current activity & pass along data to previous activity that launched this activity
                }
            }
        });


        findViewById(R.id.cancel_Card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                AddCardActivity.this.startActivity(intent);
                finish(); //Close / Dismiss current activity
            }
        });
    }
}
