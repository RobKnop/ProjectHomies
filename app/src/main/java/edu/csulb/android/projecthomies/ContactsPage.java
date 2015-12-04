package edu.csulb.android.projecthomies;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


// SHOWS ALL CONTACTS
public class ContactsPage extends Activity {

    private List<ContactsPageCardData> persons;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_view_contactspage);

        rv = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new ContactsPageCardData("Emma Wilson", "BIO 200"));
        persons.add(new ContactsPageCardData("Lavery Maiss", "Fitness Instructor"));
        persons.add(new ContactsPageCardData("Lillie Watts", "Movie Critic"));
    }

    private void initializeAdapter(){
        ContactsPageListAdapter adapter = new ContactsPageListAdapter(persons);
        rv.setAdapter(adapter);
    }
}
