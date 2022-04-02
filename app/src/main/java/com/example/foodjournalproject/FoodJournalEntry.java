package com.example.foodjournalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodJournalEntry extends AppCompatActivity
{
    private EditText date;
    private EditText breakfastText;
    private EditText lunchText;
    private EditText dinnerText;
    private EditText userNotes;
    private Button backNSave;
    public static ArrayList<FoodJournalNotes> foodJournal = new ArrayList<FoodJournalNotes>();
    private FoodJournalNotes newFoodJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_entry_page);

        date = (EditText) findViewById(R.id.dateinput);
        breakfastText = (EditText)  findViewById(R.id.bfMultiLineText);
        lunchText = (EditText)  findViewById(R.id.lMultiLineText);
        dinnerText = (EditText) findViewById(R.id.dMultiLineText);
        userNotes = (EditText)  findViewById(R.id.nMultiLineText);
        backNSave = (Button)  findViewById(R.id.backButton_UserEntryPage);


        backNSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //CODE TO CREATE OBJECTS
                newFoodJournal = new FoodJournalNotes(date.getText().toString(), breakfastText.getText().toString(),lunchText.getText().toString(),dinnerText.getText().toString(),userNotes.getText().toString());
                foodJournal.add(newFoodJournal);
                //finish activity
                startActivity(new Intent(FoodJournalEntry.this, MainActivity.class));
                ExerciseEntry.whichPage = false;
                finish();
            }
        });



    }


}
