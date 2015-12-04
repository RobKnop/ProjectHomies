package edu.csulb.android.projecthomies;

import java.sql.SQLException;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class ContactsContentProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "edu.csulb.android.projecthomies";

    /** A uri to do operations on locations table. A content provider is identified by its uri */
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contacts");

    /** Constant to identify the requested operation */
    private static final int LOCATIONS = 1;

    private static final UriMatcher uriMatcher ;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contacts", LOCATIONS);
    }

    ContactsDatabase mContactsDB;

    @Override
    public boolean onCreate() {
        mContactsDB = new ContactsDatabase(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = mContactsDB.insertLabel(values);
        Uri _uri = null;
        if(rowID > 0){
            _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        }else {
            try {
                throw new SQLException("Failed to insert : " + uri);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return _uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int delete = 0;
        delete = mContactsDB.deleteAllLabels();
        return delete;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        if (uriMatcher.match(uri) == LOCATIONS){
            return mContactsDB.getAllLabels();
        }
        return  null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}