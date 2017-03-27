package sailedeer.scorecard.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import sailedeer.scorecard.activities.fragments.CourseTabFragment;
import sailedeer.scorecard.activities.fragments.GameTabFragment;
import sailedeer.scorecard.activities.fragments.PlayerTabFragment;
import sailedeer.scorecard.activities.fragments.handling.PagerAdapter;
import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout.Tab mTab;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Players"));
        tabLayout.addTab(tabLayout.newTab().setText("Games"));
        tabLayout.addTab(tabLayout.newTab().setText("Courses"));

        pager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                mTab = tab;

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        setIntent(intent);
        String action = intent.getAction();
        if (action.equals("ADD_PLAYER"))
        {
            Player p = (Player) intent.getSerializableExtra("player");
            mDbHelper.addPlayer(p);

        }
        else if (action.equals("ADD_COURSE"))
        {
            Course c = (Course) intent.getSerializableExtra("Course");
            mDbHelper.addCourse(c);
        }

        else if (action.equals("ADD_GAME"))
        {
            Game g = (Game) intent.getSerializableExtra("Game");
            mDbHelper.addGame(g);
        }
    }
}