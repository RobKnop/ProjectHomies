package edu.csulb.android.projecthomies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDatabase extends SQLiteOpenHelper {

    private static String DBNAME = "contactsqlite";
    private static int VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String FIRST_NAME = "first_n";
    public static final String LAST_NAME = "last_n";
    public static final String COMPANY = "company";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String BIRTHDAY = "birthday";
    public static final String NOTES = "notes";
    private static final String DATABASE_TABLE = "locations";

    private SQLiteDatabase mDB;

    public ContactsDatabase(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =     "create table " + DATABASE_TABLE + " ( " +
                KEY_ID + " integer primary key autoincrement , " +
                LAST_NAME + " text , " +
                FIRST_NAME + " text , " +
                COMPANY + " text " +
                EMAIL + " text" +
                ADDRESS + " text " +
                BIRTHDAY + " text " +
                NOTES + " text " +
                " ) ";

        db.execSQL(sql);
    }

    public long insertLabel(ContentValues contentValues){
        long rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    public int deleteAllLabels(){
        int cnt = mDB.delete(DATABASE_TABLE, null, null);
        return cnt;
    }

    public Cursor getAllLabels(){
        return mDB.query(DATABASE_TABLE, new String[] {
                KEY_ID,
                FIRST_NAME,
                LAST_NAME,
                COMPANY,
                EMAIL,
                ADDRESS,
                NOTES} , null, null, null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
