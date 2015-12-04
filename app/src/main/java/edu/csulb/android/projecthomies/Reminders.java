package edu.csulb.android.projecthomies;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Reminders extends AppCompatActivity {
    ArrayList<String> remindersList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private View fabAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        fabAction = findViewById(R.id.fabAddReminder);
        fabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Reminders.this, AddReminder.class);
                startActivityForResult(i, 1);
            }
        });

        // Initiate ListView with premade Reminders
        initList();
        // Add saved Reminder to ListView
        //Intent i = getIntent();
        //newReminder = i.getExtras().getString("reminderName");
        //remindersList.add(newReminder);

        ListView lv = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                remindersList);
        lv.setAdapter(adapter);
    }

    private void initList() {
        remindersList.add("Meet Up Reminder");
        remindersList.add("Call Your Friend Reminder");
        remindersList.add("Dinner Reminder");
        remindersList.add("Party Reminder");
    }

    // Adds new Reminder to current list
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String newReminder = data.getStringExtra("reminderName");
                remindersList.add(newReminder);
                ListView lv = (ListView) findViewById(R.id.listView);
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        remindersList);
                lv.setAdapter(adapter);

                //Toast.makeText(getApplicationContext(), "Total number of Items are:" + lv.getAdapter().getCount() , Toast.LENGTH_LONG).show();
            }
        }

    }
}