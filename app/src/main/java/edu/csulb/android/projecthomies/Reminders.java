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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Reminders extends AppCompatActivity
{
    ArrayList<String> remindersList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private View fabAction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        fabAction = findViewById(R.id.fabAddReminder);
        fabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Reminders.this, AddReminder.class);
                startActivity(i);
            }
        });

        initList();
        ListView lv = (ListView) findViewById(R.id.listView);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                remindersList);
        lv.setAdapter(adapter);
    }

    private void initList()
    {
        remindersList.add("Meet Up Reminder");
        remindersList.add("Call Your Friend Reminder");
        remindersList.add("Dinner Reminder");
        remindersList.add("Party Reminder");
    }
}
