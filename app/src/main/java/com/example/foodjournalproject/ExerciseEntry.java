package com.example.foodjournalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ExerciseEntry extends AppCompatActivity {


    private EditText exerciseName;
    private EditText sets;
    private EditText reps;
    private EditText description;
    private ExerciseData newExerciseData;
    public static ArrayList<ExerciseData> exerciseData = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_entry);

        Button backNSave = (Button)  findViewById(R.id.backButton_UserEntryPage);

        exerciseName = (EditText) findViewById(R.id.exMultiLineText);
        sets = (EditText) findViewById(R.id.setsMultiLineText);
        reps = (EditText) findViewById(R.id.repsMultiLineText);
        description = (EditText) findViewById(R.id.descMultiLineText);

        backNSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {//more code to save info
                newExerciseData = new ExerciseData(exerciseName.getText().toString(), Integer.parseInt(String.valueOf(sets.getText())), Integer.parseInt(String.valueOf(sets.getText())), description.getText().toString());
                exerciseData.add(newExerciseData);

                startActivity(new Intent(ExerciseEntry.this, MainActivity.class));
                finish();
            }
        });
    }
}