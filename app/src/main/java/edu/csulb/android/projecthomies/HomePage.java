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
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    ArrayList<String> web;
    private ImageButton fab;

    private boolean expanded = false;

    private View fabAction1;
    private View fabAction2;
    private View fabAction3;

    private float offset1;
    private float offset2;
    private float offset3;
    private ArrayList<String> remindersList = new ArrayList<String>();
    private ArrayAdapter<String> reminderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView1 = (ImageView) findViewById(R.id.userProfilePic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.user_profile_pic);
        RoundImage roundedImage = new RoundImage(bm);
        imageView1.setImageDrawable(roundedImage);

        remindersList.add("Meet Up Reminder");
        remindersList.add("Call Your Friend Reminder");
        remindersList.add("Dinner Reminder");
        remindersList.add("Party Reminder");

        web = new ArrayList<String>();
        web.add("RK");
        web.add("DC");
        web.add("EH");
        web.add("MM");
        web.add("TS");

        //Sets up the ListView
        HomePageListAdapter adapter = new HomePageListAdapter(HomePage.this, web);
        GridView listView = (GridView) findViewById(R.id.gridView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ListView lv = (ListView) findViewById(R.id.listView);
        reminderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                remindersList);
        lv.setAdapter(reminderAdapter);

        final View contactCategory =  findViewById(R.id.card_view3);
        contactCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = contactCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = contactCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });

        final View remindersCategory =  findViewById(R.id.card_view2);
        remindersCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = remindersCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = remindersCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });

        final View eventsCategory =  findViewById(R.id.card_view);
        eventsCategory.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;
            final int targetHeight = eventsCategory.getContext().getResources().getDisplayMetrics().heightPixels;
            final int initialHeight = eventsCategory.getLayoutParams().height;

            public void onClick(View v) {
                expanded = expandAndCollapse(v, expanded, targetHeight, initialHeight);
            }
        });


        final Button mainContactBtn = (Button) findViewById(R.id.mainContactBtn);
        mainContactBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent("edu.csulb.android.projecthomies.ContactsPage");
                startActivity(i);

            }
        });
        final Button mainRemindersBtn = (Button) findViewById(R.id.mainRemindersBtn);
        mainRemindersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent i = new Intent(HomePage.this, AddReminder.class);
                startActivity(i);
            }
        });
        final Button mainEventsBtn = (Button) findViewById(R.id.mainEventsBtn);
        mainEventsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });

        final ViewGroup fabContainer = (ViewGroup) findViewById(R.id.fab_container);
        fabContainer.setZ(10000);
        fab = (ImageButton) findViewById(R.id.fab);
        fabAction1 = findViewById(R.id.fab_action_1);
        fabAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own amazing action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                Snackbar.make(v, "Replace with your own amazing action3", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                reminderAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        remindersList);
                lv.setAdapter(reminderAdapter);

            }
        }

    }

}