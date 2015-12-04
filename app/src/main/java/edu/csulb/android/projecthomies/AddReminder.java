package edu.csulb.android.projecthomies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


public class AddReminder extends AppCompatActivity
{
    private EditText alarmName;
    private DatePicker dPicker;
    private TimePicker tPicker;

    private Date date;
    private String time;
    private String alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        findViews();
    }

    // Save an alarm
    public void createAlarm(View v)
    {
        alarm = alarmName.getText().toString();
        date = getDateFromDatePicket(dPicker);

        Intent i = new Intent(AddReminder.this, Reminders.class);
        i.putExtra("reminderName", alarm);
        startActivity(i);
    }

    // Find all the GUI elements by views
    private void findViews()
    {
        alarmName = (EditText) findViewById(R.id.editText_alarmName);
        dPicker = (DatePicker) findViewById(R.id.datePicker);
        tPicker = (TimePicker) findViewById(R.id.timePicker);
    }

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
