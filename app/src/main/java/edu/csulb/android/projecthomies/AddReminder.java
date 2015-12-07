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

import java.util.Calendar;
import java.util.Date;


public class AddReminder extends AppCompatActivity
{
    private EditText alarmName;
    private DatePicker dPicker;

    //private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        findViews();

        alarmName.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
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
        getDateFromDatePicket(dPicker);

        // Pass new Reminder info back to homePage (Parent activity)
        Intent i = new Intent();
        i.putExtra("reminderName", alarm);
        setResult(RESULT_OK, i);
        finish();
    }

    // Find all the GUI elements by views
    private void findViews()
    {
        alarmName = (EditText) findViewById(R.id.editText_alarmName);
        dPicker = (DatePicker) findViewById(R.id.datePicker);
        findViewById(R.id.timePicker);
    }

    // UTILITY - Get date
    public static Date getDateFromDatePicket(DatePicker datePicker)
    {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
