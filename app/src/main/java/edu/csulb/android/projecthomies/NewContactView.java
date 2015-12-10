package edu.csulb.android.projecthomies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class NewContactView extends AppCompatActivity {

    private EditText first_n;
    private EditText last_n;
    private EditText company;
    private EditText email;
    private EditText address;
    private EditText birthday;
    private EditText notes;
    private Button changePhoto;
    private Button saveContact;
    private ImageView image;
    private int _Contact_Id = 0;

    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_view);

        changePhotoID();

        first_n = (EditText)findViewById(R.id.firstNew);
        last_n = (EditText)findViewById(R.id.lastNew);
        company = (EditText)findViewById(R.id.newCompany);
        email = (EditText)findViewById(R.id.emailNew);
        address = (EditText)findViewById(R.id.newAddress);
        birthday = (EditText)findViewById(R.id.newBirthday);
        notes = (EditText)findViewById(R.id.notesNew);
        image = (ImageView)findViewById(R.id.profilePicNew);

        saveContact = (Button)findViewById(R.id.saveButton);

        saveContact();

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

    public void saveContact() {

        /*
        _Student_Id =0;
        Intent intent = getIntent();
        _Student_Id =intent.getIntExtra("student_Id", 0);
        StudentRepo repo = new StudentRepo(this);
        Student student = new Student();
        student = repo.getContactById(_Student_Id);

        editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(student.name);
        editTextEmail.setText(student.email);
         */
        int _Contact_id = 0;
        Intent intent = getIntent();
        _Contact_Id = intent.getIntExtra("contact_Id", 0);
        ContactRepo repo = new ContactRepo(this);
        Contact contact = new Contact();
        contact = repo.getContactById(_Contact_id);

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactRepo repo = new ContactRepo(getApplicationContext());
                Contact student = new Contact();

                student.first_name_data = first_n.getText().toString();
                student.last_name_data = last_n.getText().toString();
                student.company_data = company.getText().toString();
                student.email_data = email.getText().toString();
                student.address_data = address.getText().toString();
                student.birthday_data = birthday.getText().toString();
                student.notes_data = notes.getText().toString();

                //Save location of image into database?

                if (_Contact_Id == 0){
                    _Contact_Id = repo.insert(student);
                    Toast.makeText(getApplicationContext(),"New Contact Added!", Toast.LENGTH_SHORT).show();
                }
                else {
                    repo.update(student);
                    Toast.makeText(getApplicationContext(),"Student Record updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void changePhotoID() {
        changePhoto = (Button)findViewById(R.id.newPhoto);
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

