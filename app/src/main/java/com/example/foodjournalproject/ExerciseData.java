package com.example.foodjournalproject;

public class ExerciseData {
    private String exercise;
    private int sets;
    private int reps;
    private String description;

    public ExerciseData( String exercise, int sets, int reps, String description) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.description = description;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public String getDescription() {
        return description;
    }

    public String getExercise() {
        return exercise;
    }
}
