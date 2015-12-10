package edu.csulb.android.projecthomies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactsDatabase extends SQLiteOpenHelper {

    public static String DB_NAME = "contactsqlite";

    public static int VERSION = 1;
    private static String TAG = "ContactsDatabase";

    /*private static final String TABLE_LABELS = "contacts";
    public static final String KEY_ID = "_id";
    public static final String KEY_FIRST_NAME = "first_n";
    public static final String KEY_LAST_NAME = "last_n";
    public static final String KEY_COMPANY = "company";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_NOTES = "notes";
    public static final String IMG_LOCATION = "img location";
*/
    private SQLiteDatabase mDB;

    public ContactsDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =     "CREATE TABLE " + Contact.TABLE_LABELS + "(" +
                Contact.KEY_ID + " INTEGER PRIMARY KEY," +
                Contact.KEY_LAST_NAME + " TEXT," +
                Contact.KEY_FIRST_NAME + " TEXT," +
                Contact.KEY_COMPANY + " TEXT," +
                Contact.KEY_EMAIL + " TEXT," +
                Contact.KEY_ADDRESS + " TEXT," +
                Contact.KEY_BIRTHDAY + " TEXT," +
                Contact.KEY_NOTES + " TEXT," +
                Contact.IMG_LOCATION + " TEXT" +
                ");";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d(TAG, "Error!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_LABELS + ";");
        // Create tables again
        onCreate(db);
    }
/*
    public long insertLabel(ContentValues contentValues){
        long rowID = mDB.insert(Contact.TABLE_LABELS, null, contentValues);
        return rowID;
    }

    public int deleteAllLabels(){
        int cnt = mDB.delete(Contact.TABLE_LABELS, null, null);
        return cnt;
    }

    public Cursor getAllLabels(){
        return mDB.query(Contact.TABLE_LABELS, new String[] {
                Contact.KEY_ID,
                Contact.KEY_FIRST_NAME,
                Contact.KEY_LAST_NAME,
                Contact.KEY_COMPANY,
                Contact.KEY_EMAIL,
                Contact.KEY_ADDRESS,
                Contact.KEY_NOTES,
                Contact.IMG_LOCATION} , null, null, null, null, null);
    }

    public List<Integer> getKeys() {
        List<Integer> labels = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + Contact.KEY_ID + " FROM " +
                Contact.TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return labels;
    }

    public List<String> getFirstNameLabels() {
        List<String> labels = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + Contact.KEY_FIRST_NAME + " FROM " +
                Contact.TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return labels;
    }*/

    public List<String> getLastNameLabels() {
        List<String> labels = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + Contact.KEY_LAST_NAME + " FROM " +
                Contact.TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return labels;
    }

    public List<String> getImageLocationLabels() {
        List<String> labels = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + Contact.IMG_LOCATION + " FROM " +
                Contact.TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return labels;
    }

    public List<String> getCompanyLabels() {
        List<String> labels = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + Contact.KEY_COMPANY + " FROM " +
                Contact.TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return labels;
    }

}
