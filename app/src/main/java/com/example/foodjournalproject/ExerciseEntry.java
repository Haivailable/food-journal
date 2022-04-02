

package com.example.foodjournalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ExerciseEntry extends AppCompatActivity {

    private EditText date;
    private EditText exercise;
    private EditText sets;
    private EditText reps;
    private EditText notes;
    private Button backNSave;
    public static ArrayList<ExerciseJournalNotes> exerciseJournal = new ArrayList<ExerciseJournalNotes>();
    private ExerciseJournalNotes newExerciseJournal;
    public static boolean whichPage = false; //helps decide which fragment to start on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_entry);

        date = (EditText) findViewById(R.id.dateinput2);
        exercise = (EditText)  findViewById(R.id.exMultiLineText);
        sets = (EditText)  findViewById(R.id.setsMultiLineText);
        reps = (EditText) findViewById(R.id.repsMultiLineText);
        notes = (EditText)  findViewById(R.id.nMultiLineText2);
        backNSave = (Button)  findViewById(R.id.backButton_ExerciseEntryPage);

        backNSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //CODE TO CREATE OBJECTS
                newExerciseJournal = new ExerciseJournalNotes(date.getText().toString(), exercise.getText().toString(),sets.getText().toString(),reps.getText().toString(),notes.getText().toString());
                exerciseJournal.add(newExerciseJournal);
                //finish activity
                startActivity(new Intent(ExerciseEntry.this, MainActivity.class));
                whichPage = true;
                finish();
            }
        });
    }
}