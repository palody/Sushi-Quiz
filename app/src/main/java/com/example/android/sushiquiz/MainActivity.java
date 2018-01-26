package com.example.android.sushiquiz;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText question3 = (EditText) findViewById(R.id.q3_text_field);
        Button btnSubmit = (Button) findViewById(R.id.check_result);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                int score = 0; // initialize score
                if (((RadioButton)findViewById(R.id.q1_false)).isChecked()) {score++;} // checks to see if correct radio button is selected
                if (((RadioButton)findViewById(R.id.q2_true)).isChecked()) {score++;} // same as above
                if (question3.getText().toString().equals("Japan")){score++;} // checks if text in EditText is an exact match

                if ((((CheckBox) findViewById(R.id.q4_anago)).isChecked()) && (((CheckBox) findViewById(R.id.q4_maguro)).isChecked()) && (((CheckBox) findViewById(R.id.q4_uni)).isChecked()))
                    score++; // checks if all three checkboxes were selected
                if (((CheckBox) findViewById(R.id.q4_nara)).isChecked()) {score--;} // deduct point if incorrect checkbox selected

                if (score < 0) {
                    score = 0;
                } // if score falls below 0, set to 0 (workaround for checkbox question; if only incorrect box is selected, score dips to -1)

                displayResult(score);
            }

        });}

    private void displayResult(int score) {
        String message = "You scored " + score;
        message += " out of 4";
        if (score == 4) {
            message += "\nPerfect score, good job!";
        } else {
            message += "\nTry again!";
        }
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.show();
    }
}