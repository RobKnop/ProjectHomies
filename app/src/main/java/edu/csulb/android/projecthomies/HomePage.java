package edu.csulb.android.projecthomies;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import edu.csulb.android.projecthomies.events.AddEvent;
import edu.csulb.android.projecthomies.events.AndroidListAdapter;
import edu.csulb.android.projecthomies.events.CalendarCollection;
import edu.csulb.android.projecthomies.events.CalenderActivity;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> web;
    private ImageButton fab;

    private boolean expanded = false;

    private View fabAction1;
    private View fabAction2;
    private View fabAction3;

    private float offset1;
    private float offset2;
    private float offset3;
    private ArrayList<String> remindersList = new ArrayList<>();
    private ArrayAdapter<String> reminderAdapter;
    private ListView lv_android;
    private AndroidListAdapter list_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageView imageView1 = (ImageView) findViewById(R.id.userProfilePic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.user_profile_pic);
        RoundImage roundedImage = new RoundImage(bm);
        imageView1.setImageDrawable(roundedImage);

        remindersList.add("Meet Up Reminder");
        remindersList.add("Call Your Friend Reminder");
        remindersList.add("Dinner Reminder");
        remindersList.add("Party Reminder");

        web = new ArrayList<>();
        web.add("RK");
        web.add("DC");
        web.add("EH");
        web.add("MM");
        web.add("TS");

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new FavoritesImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(HomePage.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // SETTING CONTACTS TO THE HOMEPAGE
        /*
        *
        * TEST PURPOSE ONLY
        * *
        RecyclerView rv;
        ArrayList<ContactsPageCardData> persons;

        rv = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        // TEST CARDS
        // To Do: Add Contacts to the HomePage
        persons = new ArrayList<>();
        persons.add(new ContactsPageCardData("Emma Wilson", "BIO 200"));
        persons.add(new ContactsPageCardData("Lavery Maiss", "Fitness Instructor"));
        persons.add(new ContactsPageCardData("Lillie Watts", "Movie Critic"));
        persons.add(new ContactsPageCardData("Molie Vasquez", "Foodie"));
        persons.add(new ContactsPageCardData("Dee Williams", "Promoter"));
        persons.add(new ContactsPageCardData("Lynn Thompson", "Writer"));
        persons.add(new ContactsPageCardData("Dawn Zaragoza", "Book Worm"));
        persons.add(new ContactsPageCardData("Dean Soto", "UBER Driver"));
        persons.add(new ContactsPageCardData("Melinda Houchins", "Professional Wrestler"));

        ContactsPageListAdapter adapter = new ContactsPageListAdapter(persons);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContactsPageListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent("edu.csulb.android.projecthomies.DetailedContactView");
                startActivity(i);
            }
        });
        */

        addContactCardsToHomePage();
        //

        ListView lv = (ListView) findViewById(R.id.listView);
        reminderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                remindersList);
        lv.setAdapter(reminderAdapter);


        CalendarCollection.date_collection_arr = new ArrayList<CalendarCollection>();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-01", "John Birthday"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-04", "Client Meeting at 5 p.m."));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-06", "A Small Party at my office"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-02", "Marriage Anniversary"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-12-11", "Live Event and Concert of sonu"));


        getWidget();

        final View contactCategory = findViewById(R.id.card_view3);
        contactCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = contactCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = contactCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });

        final View remindersCategory = findViewById(R.id.card_view2);
        remindersCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = remindersCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = remindersCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });

        final View eventsCategory = findViewById(R.id.card_view);
        eventsCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = eventsCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = eventsCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });

        final ViewGroup fabContainer = (ViewGroup) findViewById(R.id.fab_container);
        fabContainer.setZ(10000);
        fab = (ImageButton) findViewById(R.id.fab);
        fabAction1 = findViewById(R.id.fab_action_1);
        fabAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent("edu.csulb.edu.android.projecthomies.NewContactView");
                startActivity(p);
            }
        });
        fabAction2 = findViewById(R.id.fab_action_2);
        fabAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AddReminder.class);
                startActivityForResult(i, 1);
            }
        });
        fabAction3 = findViewById(R.id.fab_action_3);
        fabAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this, AddEvent.class);
                startActivityForResult(i, 2);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                if (expanded) {
                    expandFab();
                } else {
                    collapseFab();
                }
            }
        });
        fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                offset1 = fab.getY() - fabAction1.getY();
                fabAction1.setTranslationY(offset1);
                offset2 = fab.getY() - fabAction2.getY();
                fabAction2.setTranslationY(offset2);
                offset3 = fab.getY() - fabAction3.getY();
                fabAction3.setTranslationY(offset3);
                return true;
            }
        });

    }

    // ADDING CONTACTCARDS TO THE HOMEPAGE
    private void addContactCardsToHomePage() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        ContactRepo repo = new ContactRepo(this);

        ArrayList<HashMap<String, String>> contactList = repo.getContactList();

        String firstName;
        String lastName;
        String companyExtr;
        ArrayList<ContactsPageCardData> persons = new ArrayList<>();

        int sizeOf = contactList.size();
        for (int i = 0; i < sizeOf; i++) {
            HashMap<String, String> pulled = contactList.get(i);
            firstName = pulled.get("first");
            lastName = pulled.get("last");
            companyExtr = pulled.get("company");
            persons.add(new ContactsPageCardData(firstName + " " + lastName, companyExtr));
        }

        // TEST CARDS
        // To Do: Add Contacts to the HomePage
        persons.add(new ContactsPageCardData("Emma Wilson", "BIO 200"));
        persons.add(new ContactsPageCardData("Lavery Maiss", "Fitness Instructor"));
        persons.add(new ContactsPageCardData("Lillie Watts", "Movie Critic"));
        persons.add(new ContactsPageCardData("Molie Vasquez", "Foodie"));
        persons.add(new ContactsPageCardData("Dee Williams", "Promoter"));
        persons.add(new ContactsPageCardData("Lynn Thompson", "Writer"));
        persons.add(new ContactsPageCardData("Dawn Zaragoza", "Book Worm"));
        persons.add(new ContactsPageCardData("Dean Soto", "UBER Driver"));
        persons.add(new ContactsPageCardData("Melinda Houchins", "Professional Wrestler"));

        ContactsPageListAdapter adapter = new ContactsPageListAdapter(persons);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContactsPageListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent("edu.csulb.android.projecthomies.DetailedContactView");
                startActivity(i);
            }
        });

        /*ContactsDatabase db = new ContactsDatabase(getApplicationContext());
        List<Integer> keys = db.getKeys();
        List<String> firstNameLabels = db.getFirstNameLabels();
        List<String> lastNameLabels = db.getLastNameLabels();
        List<String> companyLabels = db.getCompanyLabels();
        List<String> imageLocationLabels = db.getImageLocationLabels();

        int size = keys.size();

        if (!keys.isEmpty()) {
            for (int i = 0; i < size; i++) {
                persons.add(new ContactsPageCardData(firstNameLabels.get(i) + " " + lastNameLabels.get(i), companyLabels.get(i)));
            }
        }





        String contactNameExt = "";
        String contactCompanyExt = "";
        String contactPhotoExt = "";

        ContactsPageListAdapter adapter = new ContactsPageListAdapter(persons);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContactsPageListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent("edu.csulb.android.projecthomies.DetailedContactView");
                startActivity(i);
            }
        });*/
    }

    private void collapseFab() {
        fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1),
                createCollapseAnimator(fabAction2, offset2),
                createCollapseAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        fab.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabAction1, offset1),
                createExpandAnimator(fabAction2, offset2),
                createExpandAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private static final String TRANSLATION_Y = "translationY";

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private void animateFab() {
        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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

    private boolean expandAndCollapse(final View v, boolean expanded, final int targetHeight, final int initialHeight) {
        final int heightDiff = targetHeight - initialHeight;

        if (!expanded) {
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = initialHeight + (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            // 1dp/ms
            a.setDuration((int) (3 * targetHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
            return true;
        } else {
            Animation b = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = initialHeight + (int) (heightDiff * (1 - interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            // 1dp/ms
            b.setDuration((int) (3 * targetHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(b);
            return false;
        }
    }

    // Adds new Reminder to current list
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String newReminder = data.getStringExtra("reminderName");
                remindersList.add(newReminder);
                ListView lv = (ListView) findViewById(R.id.listView);
                reminderAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        remindersList);
                lv.setAdapter(reminderAdapter);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String newReminder = data.getStringExtra("eventName");
                String date = data.getStringExtra("date");
                CalendarCollection.date_collection_arr.add(new CalendarCollection(date, newReminder));

                lv_android = (ListView) findViewById(R.id.lv_android);
                list_adapter = new AndroidListAdapter(HomePage.this, R.layout.event_list_item, CalendarCollection.date_collection_arr);
                lv_android.setAdapter(list_adapter);

            }
        }

    }

    public void getWidget() {
        ImageButton btn_calender = (ImageButton) findViewById(R.id.btn_calender);
        btn_calender.setOnClickListener(this);

        lv_android = (ListView) findViewById(R.id.lv_android);
        list_adapter = new AndroidListAdapter(HomePage.this, R.layout.event_list_item, CalendarCollection.date_collection_arr);
        lv_android.setAdapter(list_adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calender:
                startActivity(new Intent(HomePage.this, CalenderActivity.class));
                break;
            default:
                break;
        }

    }


}