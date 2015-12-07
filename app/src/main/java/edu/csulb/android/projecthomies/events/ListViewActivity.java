package edu.csulb.android.projecthomies.events;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import edu.csulb.android.projecthomies.R;

public class ListViewActivity extends Activity implements OnClickListener {

    private ListView lv_android;
    private AndroidListAdapter list_adapter;
    private Button btn_calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_view);

        CalendarCollection.date_collection_arr=new ArrayList<CalendarCollection>();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-01","John Birthday"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-04","Client Meeting at 5 p.m."));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-06","A Small Party at my office"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-02","Marriage Anniversary"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-11","Live Event and Concert of sonu"));
        
    
        getWidget();
    }

    
    
    public void getWidget(){
        btn_calender = (Button) findViewById(R.id.btn_calender);
        btn_calender.setOnClickListener(this);
        
        lv_android = (ListView) findViewById(R.id.lv_android);
        list_adapter=new AndroidListAdapter(ListViewActivity.this,R.layout.event_list_item, CalendarCollection.date_collection_arr);
        lv_android.setAdapter(list_adapter);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_calender:
            startActivity(new Intent(ListViewActivity.this,CalenderActivity.class));
            break;
        default:
            break;
        }
        
    }
    
}

