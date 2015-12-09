package edu.csulb.android.projecthomies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactsDatabase extends SQLiteOpenHelper {

    public static String DB_NAME = "contactsqlite";
    public static int VERSION = 1;
    private static final String TABLE_LABELS = "contacts";
    public static final String KEY_ID = "_id";
    public static final String FIRST_NAME = "first_n";
    public static final String LAST_NAME = "last_n";
    public static final String COMPANY = "company";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String BIRTHDAY = "birthday";
    public static final String NOTES = "notes";
    public static final String IMG_LOCATION = "img location";

    private SQLiteDatabase mDB;

    public ContactsDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =     "CREATE TABLE " + TABLE_LABELS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                LAST_NAME + " TEXT," +
                FIRST_NAME + " TEXT," +
                COMPANY + " TEXT," +
                EMAIL + " TEXT," +
                ADDRESS + " TEXT," +
                BIRTHDAY + " TEXT," +
                NOTES + " TEXT," +
                IMG_LOCATION + " TEXT" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        // Create tables again
        onCreate(db);
    }

    public long insertLabel(ContentValues contentValues){
        long rowID = mDB.insert(TABLE_LABELS, null, contentValues);
        return rowID;
    }

    public int deleteAllLabels(){
        int cnt = mDB.delete(TABLE_LABELS, null, null);
        return cnt;
    }

    public Cursor getAllLabels(){
        return mDB.query(TABLE_LABELS, new String[] {
                KEY_ID,
                FIRST_NAME,
                LAST_NAME,
                COMPANY,
                EMAIL,
                ADDRESS,
                NOTES,
                IMG_LOCATION} , null, null, null, null, null);
    }

    public List<Integer> getKeys() {
        List<Integer> labels = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_ID + " FROM " +
                TABLE_LABELS;
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
        String selectQuery = "SELECT " + FIRST_NAME + " FROM " +
                TABLE_LABELS;
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

    public List<String> getLastNameLabels() {
        List<String> labels = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + LAST_NAME + " FROM " +
                TABLE_LABELS;
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
        String selectQuery = "SELECT " + IMG_LOCATION + " FROM " +
                TABLE_LABELS;
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
        String selectQuery = "SELECT " + COMPANY + " FROM " +
                TABLE_LABELS;
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
