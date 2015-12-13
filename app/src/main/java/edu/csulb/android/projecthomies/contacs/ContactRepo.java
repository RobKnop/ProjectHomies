package edu.csulb.android.projecthomies.contacs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactRepo {

    private ContactsDatabase dbHelper;

    public ContactRepo(Context context) {
        dbHelper = new ContactsDatabase(context);
    }
    public int insert(Contact contact) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contact.KEY_FIRST_NAME, contact.first_name_data);
        values.put(Contact.KEY_LAST_NAME,contact.last_name_data);
        values.put(Contact.KEY_COMPANY, contact.company_data);
        values.put(Contact.KEY_EMAIL, contact.email_data);
        values.put(Contact.KEY_ADDRESS, contact.address_data);
        values.put(Contact.KEY_BIRTHDAY, contact.birthday_data);
        values.put(Contact.KEY_NOTES, contact.notes_data);
        values.put(Contact.IMG_LOCATION, contact.img_location);

        // Inserting Row
        long student_Id = db.insert(contact.TABLE_LABELS, null, values);
        db.close(); // Closing database connection
        return (int)student_Id;
    }

    public void delete(int contact_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Contact.TABLE_LABELS, Contact.KEY_ID + "= ?", new String[]{String.valueOf(contact_Id)});
        db.close(); // Closing database connection
    }

    public void update(Contact contact) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.KEY_FIRST_NAME, contact.first_name_data);
        values.put(Contact.KEY_LAST_NAME,contact.last_name_data);
        values.put(Contact.KEY_COMPANY, contact.company_data);
        values.put(Contact.KEY_EMAIL, contact.email_data);
        values.put(Contact.KEY_ADDRESS, contact.address_data);
        values.put(Contact.KEY_BIRTHDAY, contact.birthday_data);
        values.put(Contact.KEY_NOTES, contact.notes_data);
        values.put(Contact.IMG_LOCATION, contact.img_location);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Contact.TABLE_LABELS, values, Contact.KEY_ID + "= ?", new String[]{String.valueOf(contact.contact_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getContactList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Contact.KEY_ID + "," +
                Contact.KEY_FIRST_NAME + "," +
                Contact.KEY_LAST_NAME + "," +
                Contact.KEY_COMPANY + "," +
                Contact.KEY_EMAIL + "," +
                Contact.KEY_ADDRESS + "," +
                Contact.KEY_BIRTHDAY + "," +
                Contact.KEY_NOTES + "," +
                Contact.IMG_LOCATION +
                " FROM " + Contact.TABLE_LABELS;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();

                try {
                    student.put("id", cursor.getString(cursor.getColumnIndex(Contact.KEY_ID)));
                    student.put("first", cursor.getString(cursor.getColumnIndex(Contact.KEY_FIRST_NAME)));
                    student.put("last", cursor.getString(cursor.getColumnIndex(Contact.KEY_LAST_NAME)));
                    student.put("company", cursor.getString(cursor.getColumnIndex(Contact.KEY_COMPANY)));
                    student.put("email", cursor.getString(cursor.getColumnIndex(Contact.KEY_EMAIL)));
                    student.put("address", cursor.getString(cursor.getColumnIndex(Contact.KEY_ADDRESS)));
                    student.put("birthday", cursor.getString(cursor.getColumnIndex(Contact.KEY_BIRTHDAY)));
                    student.put("notes", cursor.getString(cursor.getColumnIndex(Contact.KEY_NOTES)));
                } catch(Exception e) {

                }

                contactList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;

    }

    public Contact getContactById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Contact.KEY_ID + "," +
                Contact.KEY_FIRST_NAME + "," +
                Contact.KEY_LAST_NAME + "," +
                Contact.KEY_COMPANY + "," +
                Contact.KEY_EMAIL + "," +
                Contact.KEY_ADDRESS + "," +
                Contact.KEY_BIRTHDAY + "," +
                Contact.KEY_NOTES + "," +
                Contact.IMG_LOCATION +
                " FROM " + Contact.TABLE_LABELS
                + " WHERE " +
                Contact.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Contact contact = new Contact();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                contact.contact_ID = cursor.getInt(cursor.getColumnIndex(Contact.KEY_ID));
                contact.first_name_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_FIRST_NAME));
                contact.last_name_data  = cursor.getString(cursor.getColumnIndex(Contact.KEY_LAST_NAME));
                contact.company_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_COMPANY));
                contact.email_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_EMAIL));
                contact.address_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_ADDRESS));
                contact.birthday_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_BIRTHDAY));
                contact.notes_data = cursor.getString(cursor.getColumnIndex(Contact.KEY_NOTES));
                contact.img_location = cursor.getString(cursor.getColumnIndex(Contact.IMG_LOCATION));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contact;
    }
}
