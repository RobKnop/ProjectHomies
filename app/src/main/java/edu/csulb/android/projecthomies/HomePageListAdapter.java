package edu.csulb.android.projecthomies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HomePageListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> web;

    public HomePageListAdapter(Activity context, ArrayList<String> web) {
        super(context, R.layout.list_view_homepage, web);
        this.context = context;
        this.web = web;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView  = inflater.inflate(R.layout.list_view_homepage, null, true);
        TextView txtTitle = (TextView)rowView.findViewById(R.id.txt);

        txtTitle.setText(web.get(position));

        return rowView;
    }
}