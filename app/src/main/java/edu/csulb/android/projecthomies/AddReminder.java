package edu.csulb.android.projecthomies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class AddReminder extends AppCompatActivity {
    private EditText alarmName;
    private DatePicker dPicker;
    private TimePicker tPicker;

    private AlarmClient aClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        findViews();

        // Create new AlarmClient
        aClient = new AlarmClient(this);
        aClient.doBindService();

        alarmName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(alarmName.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
    }

    // Stop service on activity ending to prevent leaking into the system
    @Override
    protected void onStop()
    {
        if(aClient != null)
        {
            aClient.doUnbindService();
            super.onStop();
        }
    }

    // Save an alarm
    public void createAlarm(View v)
    {
        String alarm = alarmName.getText().toString();

        // All the stuff for creating the Alarm notification
        // Gets the date chosen on DatePicker
        int day = dPicker.getDayOfMonth();
        int month = dPicker.getMonth();
        int year =  dPicker.getYear();

        // Create new calender for date and time selected from DatePicker and TimePicker
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        calendar.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, tPicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);

        // Call service to set alarm
        aClient.setAlarmForNotification(calendar);

        // Pass new Reminder info back to homePage (Parent activity)
        Intent i = new Intent();
        i.putExtra("reminderName", alarm);
        setResult(RESULT_OK, i);
        finish();

        // Notify user of the notification
        Toast.makeText(this, "Notification set for: " + (month+1) + "/" +
                day + "/" + year, Toast.LENGTH_SHORT).show();
    }

    // Find all the GUI elements by views
    private void findViews()
    {
        alarmName = (EditText) findViewById(R.id.eventName);
        dPicker = (DatePicker) findViewById(R.id.datePicker);
        tPicker = (TimePicker) findViewById(R.id.timePicker);
    }

}
