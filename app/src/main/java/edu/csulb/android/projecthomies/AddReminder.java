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


public class AddReminder extends AppCompatActivity
{
    private EditText alarmName;
    private DatePicker dPicker;
    private TimePicker tPicker;

    //private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        findViews();

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

    // Save an alarm
    public void createAlarm(View v)
    {
        String alarm = alarmName.getText().toString();
        setNotification(dPicker, tPicker);

        // Pass new Reminder info back to homePage (Parent activity)
        Intent i = new Intent();
        i.putExtra("reminderName", alarm);
        setResult(RESULT_OK, i);
        finish();

        // Notify user of the notification
        Toast.makeText(this, "Notification set for: " + dPicker.getDayOfMonth() + "/" +
                dPicker.getMonth()+1 + "/" + dPicker.getYear(), Toast.LENGTH_SHORT).show();
    }

    // Find all the GUI elements by views
    private void findViews()
    {
        alarmName = (EditText) findViewById(R.id.editText_alarmName);
        dPicker = (DatePicker) findViewById(R.id.datePicker);
        tPicker = (TimePicker) findViewById(R.id.timePicker);
    }

    // UTILITY - Get date
    public static Date setNotification(DatePicker datePicker, TimePicker timePicker)
    {
        // Gets the date chosen on DatePicker
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        // Create new calender for date and time selected from DatePicker and TimePicker
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);

        // Call service to set alarm
        

        return calendar.getTime();
    }
}
