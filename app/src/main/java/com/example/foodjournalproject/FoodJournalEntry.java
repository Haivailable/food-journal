package com.example.foodjournalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodJournalEntry extends AppCompatActivity
{
    private EditText breakfastText;
    private EditText lunchText;
    private EditText dinnerText;
    private EditText userNotes;
    private Button backNSave;

    public static final String shrd_Prefs = "shrdPreference";
    public static final String bf_Text = "BreakFast Text";
    public static final String l_Text = "Lunch Text";
    public static final String d_Text = "Dinner Text";
    public static final String n_Text = "User Notes Text";

    private String bfText;
    private String lText;
    private String dText;
    private String nText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_entry_page);

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
                breakfastText.setText(breakfastText.getText().toString());
                lunchText.setText(lunchText.getText().toString());
                dinnerText.setText(dinnerText.getText().toString());
                userNotes.setText(userNotes.getText().toString());
                saveData();
            }
        });

        loadData();
        updateEditTexts();
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(shrd_Prefs, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(bf_Text, breakfastText.getText().toString());
        editor.putString(l_Text, lunchText.getText().toString());
        editor.putString(d_Text, dinnerText.getText().toString());
        editor.putString(n_Text, userNotes.getText().toString());

        editor.apply();
        // Line below displays the message "Saved" if the editTexts are saved, commented out if it works.
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(shrd_Prefs, MODE_PRIVATE);
        bfText = sharedPreferences.getString(bf_Text, "Empty");
        lText = sharedPreferences.getString(l_Text, "Empty");
        dText = sharedPreferences.getString(d_Text, "Empty");
        nText = sharedPreferences.getString(n_Text, "Empty");
    }

    public void updateEditTexts()
    {
        breakfastText.setText(bfText);
        lunchText.setText(lText);
        dinnerText.setText(dText);
        userNotes.setText(nText);
    }

}
