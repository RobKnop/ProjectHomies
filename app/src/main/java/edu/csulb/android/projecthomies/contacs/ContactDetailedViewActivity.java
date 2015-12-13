package edu.csulb.android.projecthomies.contacs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import edu.csulb.android.projecthomies.R;


public class ContactDetailedViewActivity extends AppCompatActivity {

    private TextView first_n;
    private TextView last_n;
    private TextView company;
    private TextView phoneNumber;
    private TextView emailAddress;
    private TextView address;
    private TextView birthday;
    private TextView notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_contact_view);
        SQLiteDatabase readable = new ContactsDatabase(this).getReadableDatabase();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_contact_settings) {
            Intent i = new Intent(this, ContactEditViewActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

