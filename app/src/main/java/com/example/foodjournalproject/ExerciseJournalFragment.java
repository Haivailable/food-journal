package com.example.foodjournalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseJournalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseJournalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciseJournalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseJournalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseJournalFragment newInstance(String param1, String param2) {
        ExerciseJournalFragment fragment = new ExerciseJournalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    //Arraylists that will be necessary
    ListView listView;
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> exerciseList = new ArrayList<>();
    ArrayList<String> setsList = new ArrayList<>();
    ArrayList<String> repsList = new ArrayList<>();
    ArrayList<String> allNotes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercise_journal, container, false);
        FloatingActionButton addExercise = (FloatingActionButton) v.findViewById(R.id.exercise_fab);

        addExercise.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ExerciseEntry.class);
            startActivity(intent);
            getActivity().finish();
        });

        //create a listview and add entry info to respective Arraylists
        listView = (ListView) v.findViewById(R.id.exerciseJournalListView);
        for (int i = 0; i<ExerciseEntry.exerciseJournal.size();i++){
            dates.add(ExerciseEntry.exerciseJournal.get(i).getDate());
            exerciseList.add(ExerciseEntry.exerciseJournal.get(i).getExercise());
            setsList.add(ExerciseEntry.exerciseJournal.get(i).getSets());
            repsList.add(ExerciseEntry.exerciseJournal.get(i).getReps());
            allNotes.add(ExerciseEntry.exerciseJournal.get(i).getNotes());
        }

        //Define Adapter and set it
        ExerciseAdapter adapter = new ExerciseAdapter(getActivity(), dates,exerciseList,setsList,repsList,allNotes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    //Remove the item from the arraylist and from the listview display
                    public void onClick(DialogInterface dialog, int which) {
                        ExerciseEntry.exerciseJournal.remove(positionToRemove);
                        dates.remove(positionToRemove);
                        exerciseList.remove(positionToRemove);
                        setsList.remove(positionToRemove);
                        repsList.remove(positionToRemove);
                        allNotes.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();

            }
        });

        return v;
    }

    //Create an adapter that correctly displays string information onto the listview
    class ExerciseAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> rDate;
        ArrayList<String> rExercise;
        ArrayList<String> rSets;
        ArrayList<String> rReps;
        ArrayList<String> rNotes;

        ExerciseAdapter (Context c, ArrayList<String> date, ArrayList<String> exerciseList, ArrayList<String> setsList, ArrayList<String> repsList, ArrayList<String> notes){
            super(c, R.layout.list_exercise, R.id.date2, date);
            this.context=c;
            this.rDate=date;
            this.rExercise=exerciseList;
            this.rSets=setsList;
            this.rReps=repsList;
            this.rNotes=notes;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.list_exercise,parent,false);
            }

            TextView eJournalDate = convertView.findViewById(R.id.date2);
            TextView eJournalExercise = convertView.findViewById(R.id.exerciseTextView);
            TextView eJournalSets = convertView.findViewById(R.id.setsTextView);
            TextView eJournalReps = convertView.findViewById(R.id.repsTextView);
            TextView eJournalNotes = convertView.findViewById(R.id.notesTextView2);

            //Now set the resources on the views
            eJournalDate.setText("Date: " + rDate.get(position));
            eJournalExercise.setText("Exercise: "+rExercise.get(position));
            eJournalSets.setText("Sets: "+rSets.get(position));
            eJournalReps.setText("Reps: "+rReps.get(position));
            eJournalNotes.setText("Notes: "+rNotes.get(position));


            return super.getView(position, convertView, parent);
        }
    }

}