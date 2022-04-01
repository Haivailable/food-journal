package com.example.foodjournalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;


import com.example.foodjournalproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FoodJournalFragment());
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

    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}