package edu.csulb.android.projecthomies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditContactView extends AppCompatActivity {

    private EditText first_n;
    private EditText last_n;
    private EditText company;
    private EditText email;
    private EditText address;
    private EditText birthday;
    private EditText notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        SQLiteDatabase writeable = new ContactsDatabase(this).getWritableDatabase();
        SQLiteDatabase readable = new ContactsDatabase(this).getReadableDatabase();
        ContentValues newValues = new ContentValues();

        first_n = (EditText)findViewById(R.id.firstEdit);
        last_n = (EditText)findViewById(R.id.lastEdit);
        company = (EditText)findViewById(R.id.editCompany);
        email = (EditText)findViewById(R.id.emailEdit);
        address = (EditText)findViewById(R.id.editAddress);
        birthday = (EditText)findViewById(R.id.editBirthday);
        notes = (EditText)findViewById(R.id.notesEdit);

        String f_name = first_n.getText().toString();
        String l_name = last_n.getText().toString();
        String company_str = company.getText().toString();
        String email_str = email.getText().toString();
        String address_str = address.getText().toString();
        String birthday_str = birthday.getText().toString();
        String notes_str = notes.getText().toString();

        newValues.put(ContactsDatabase.FIRST_NAME, f_name);
        newValues.put(ContactsDatabase.LAST_NAME, l_name);
        newValues.put(ContactsDatabase.COMPANY, company_str);
        newValues.put(ContactsDatabase.EMAIL, email_str);
        newValues.put(ContactsDatabase.ADDRESS, address_str);
        newValues.put(ContactsDatabase.BIRTHDAY, birthday_str);
        newValues.put(ContactsDatabase.NOTES, notes_str);

        String selection = ContactsDatabase.KEY_ID + " LIKE? ";
        String[] selectionArgs = {"1"};
        int count = writeable.update(ContactsDatabase.DBNAME, newValues, selection, selectionArgs);

    }
}

