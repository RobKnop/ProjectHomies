package edu.csulb.android.projecthomies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Reminders extends AppCompatActivity
{
    private View fabAction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        fabAction = findViewById(R.id.fabAddReminder);
        fabAction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Reminders.this, AddReminder.class);
                startActivity(i);
            }
        });
    }
}
