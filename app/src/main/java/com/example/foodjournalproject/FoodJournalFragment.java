package com.example.foodjournalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodJournalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodJournalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodJournalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodJournalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodJournalFragment newInstance(String param1, String param2) {
        FoodJournalFragment fragment = new FoodJournalFragment();
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
    ArrayList<String> breakfasts = new ArrayList<>();
    ArrayList<String> lunches = new ArrayList<>();
    ArrayList<String> dinners = new ArrayList<>();
    ArrayList<String> allNotes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.homepage, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        //when button is clicked, open entry page
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FoodJournalEntry.class);
            startActivity(intent);
            getActivity().finish();
        });

        //create a listview and add entry info to respective Arraylists
        listView = (ListView) v.findViewById(R.id.journalListView);
        for (int i = 0; i<FoodJournalEntry.foodJournal.size();i++){
            dates.add(FoodJournalEntry.foodJournal.get(i).getDate());
            breakfasts.add(FoodJournalEntry.foodJournal.get(i).getBfText());
            lunches.add(FoodJournalEntry.foodJournal.get(i).getlText());
            dinners.add(FoodJournalEntry.foodJournal.get(i).getdText());
            allNotes.add(FoodJournalEntry.foodJournal.get(i).getnText());
        }
        //testing stuff rn
        System.out.println(dates);

        //Define Adapter and set it
        FoodAdapter adapter = new FoodAdapter(getActivity(), dates,breakfasts,lunches,dinners,allNotes);
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
                        dates.remove(positionToRemove);
                        System.out.println(dates); //testing rn
                        breakfasts.remove(positionToRemove);
                        lunches.remove(positionToRemove);
                        dinners.remove(positionToRemove);
                        allNotes.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();

            }
        });

        return v;
    }

    //Create an adapter that correctly displays string information onto the listview
    class FoodAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<String> rDate;
        ArrayList<String> rBreakfast;
        ArrayList<String> rLunch;
        ArrayList<String> rDinner;
        ArrayList<String> rNotes;

        FoodAdapter (Context c, ArrayList<String> date, ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner, ArrayList<String> notes){
            super(c, R.layout.list_food, R.id.date, date);
            this.context=c;
            this.rDate=date;
            this.rBreakfast=breakfast;
            this.rLunch=lunch;
            this.rDinner=dinner;
            this.rNotes=notes;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.list_food,parent,false);
            }

            TextView journalDate = convertView.findViewById(R.id.date);
            TextView journalBF = convertView.findViewById(R.id.breakfastTextView);
            TextView journalLunch = convertView.findViewById(R.id.lunchTextView);
            TextView journalDin = convertView.findViewById(R.id.dinnerTextView);
            TextView journalNotes = convertView.findViewById(R.id.notesTextView);

            //Now set the resources on the views
            journalDate.setText("Date: " + rDate.get(position));
            journalBF.setText("Breakfast: "+rBreakfast.get(position));
            journalLunch.setText("Lunch: "+rLunch.get(position));
            journalDin.setText("Dinner: "+rDinner.get(position));
            journalNotes.setText("Notes: "+rNotes.get(position));


            return super.getView(position, convertView, parent);
        }


    }
}