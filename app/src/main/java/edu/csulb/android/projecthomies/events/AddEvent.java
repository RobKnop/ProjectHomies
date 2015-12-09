package edu.csulb.android.projecthomies.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.csulb.android.projecthomies.R;


public class AddEvent extends AppCompatActivity
{
    private EditText eventName;
    private DatePicker dPicker;

    //private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add_activity);
        findViews();

        eventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(eventName.getWindowToken(),
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
        String alarm = eventName.getText().toString();
        Date date = getDateFromDatePicket(dPicker);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        Log.v("DATE", s);

        // Pass new Reminder info back to homePage (Parent activity)
        Intent i = new Intent();
        i.putExtra("eventName", alarm);
        i.putExtra("date", s);
        setResult(RESULT_OK, i);
        finish();
    }

    // Find all the GUI elements by views
    private void findViews()
    {
        eventName = (EditText) findViewById(R.id.eventName);
        dPicker = (DatePicker) findViewById(R.id.datePicker);
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
