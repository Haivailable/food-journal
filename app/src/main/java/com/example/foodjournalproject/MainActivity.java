package com.example.foodjournalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodjournalproject.Adapter.ToDoAdapter;
import com.example.foodjournalproject.Model.ToDoModel;
import com.example.foodjournalproject.Utils.DatabaseHandler;
import com.example.foodjournalproject.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private EditText ed;
    private Button adding_items;
    private ListView item_list;
    private ArrayList<String> values = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FoodJournalFragment()); //WILL NEED TO CHANGE THIS TO THE PASSCODE PAGE LATER. ONLY OPENING ON FOODJOURNAL PAGE TEMPORARILY.
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case(R.id.food_journal):
                    replaceFragment(new FoodJournalFragment());//enter the fragment page
                    break;
                case(R.id.exercise_journal):
                    replaceFragment(new ExerciseJournalFragment());
                    break;
                case(R.id.todo):
                    replaceFragment(new ToDoFragment());
                    break;
            }


            return true;
        });
        //----------Code for checklist FOR PETER---------------//
        ArrayList<String> entries = new ArrayList<>();
        Button clickButton = (Button) findViewById(R.id.add_item);
        //FIGURE OUT HOW TO GET THE STRING INFORMATION FROM THE EDITTEXT
        EditText text_edit = (EditText) findViewById(R.id.text_edit);
        String entry = text_edit.getText().toString();
        text_edit.setVisibility(View.GONE);
         //this is when the button is clicked on task_layout.xml
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do what you want with the click here***
                text_edit.setVisibility(View.VISIBLE);
                entries.add(entry);
                Button clickButton = (Button) view;
                text_edit.setText(text_edit.getText() + clickButton.getText().toString());

           }
        });
    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}