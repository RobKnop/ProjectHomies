package edu.csulb.android.projecthomies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


// SHOWS ALL CONTACTS
public class ContactsPage extends Activity {

    private String TAG = "Contacts Page";
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

    // TEST CARDS
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new ContactsPageCardData("Emma Wilson", "BIO 200"));
        persons.add(new ContactsPageCardData("Lavery Maiss", "Fitness Instructor"));
        persons.add(new ContactsPageCardData("Lillie Watts", "Movie Critic"));
        persons.add(new ContactsPageCardData("Molie Vasquez", "Foodie"));
        persons.add(new ContactsPageCardData("Dee Williams", "Promoter"));
        persons.add(new ContactsPageCardData("Lynn Thompson", "Writer"));
        persons.add(new ContactsPageCardData("Dawn Zaragoza", "Book Worm"));
        persons.add(new ContactsPageCardData("Dean Soto", "UBER Driver"));
        persons.add(new ContactsPageCardData("Melinda Houchins", "Professional Wrestler"));
    }

    private void initializeAdapter(){
        ContactsPageListAdapter adapter = new ContactsPageListAdapter(persons);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContactsPageListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent i = new Intent("edu.csulb.android.projecthomies.EditContactView");
                startActivity(i);
            }
        });
    }
}
