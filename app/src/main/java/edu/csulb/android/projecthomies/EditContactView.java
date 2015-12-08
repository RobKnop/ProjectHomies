package edu.csulb.android.projecthomies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditContactView extends AppCompatActivity {

    private EditText first_n;
    private EditText last_n;
    private EditText company;
    private EditText email;
    private EditText address;
    private EditText birthday;
    private EditText notes;
    private Button changePhoto;
    private ImageView image;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        changePhotoID();

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
        image = (ImageView)findViewById(R.id.profilePicEdit);

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
        // PUT THIS SOMEWHERE ELSE FOR NOW
        //int count = writeable.update(ContactsDatabase.DBNAME, newValues, selection, selectionArgs);
    }
    private Bitmap yourSelectedImage = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    //InputStream imageStream = null;
                    try {
                        yourSelectedImage = decodeUri(selectedImage);
                        //imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (Exception e) {

                    }

                    //Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }

    public void changePhotoID() {
        changePhoto = (Button)findViewById(R.id.editphoto);
        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                image.setImageBitmap(yourSelectedImage);
            }
        });

    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }
}

