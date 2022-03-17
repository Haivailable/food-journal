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

        /** PETER UR STUFF ISN'T WORKING - MESSAGE FROM SHABBIR
        getSupportActionBar().hide();
        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab = findViewById(R.id.fab);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });**/
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