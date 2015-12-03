package edu.csulb.android.projecthomies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class ContactsPage extends AppCompatActivity {

    private ContactsPageListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_contactspage);
        //setContentView(R.layout.activity_contacts_page_card_view);
        //getActionBar().setTitle("Contacts");
        listView = (ListView)findViewById(R.id.card_listView);
        adapter = new ContactsPageListAdapter(getApplicationContext(), R.layout.activity_detailed_contact_view);

        for (int i = 0; i < 10; i++) {
            ContactsPageCard card = new ContactsPageCard("Contact " + (i + 1));
            adapter.add(card);
        }

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
