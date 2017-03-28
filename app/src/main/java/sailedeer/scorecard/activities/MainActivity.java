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
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;

import sailedeer.scorecard.activities.fragments.handling.PagerAdapter;
import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.sql.DatabaseHelper;
import sailedeer.scorecard.util.Constants;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter adapter;
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
        adapter = new PagerAdapter
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
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_context_menu, menu);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        setIntent(intent);
        String action = intent.getAction();
        if (action.equals(Constants.ADD_PLAYER))
        {
            Player p = (Player) intent.getSerializableExtra(Constants.K_PLAYER);
            mDbHelper.addPlayer(p);

        }
        else if (action.equals(Constants.ADD_COURSE))
        {
            Course c = (Course) intent.getSerializableExtra(Constants.K_COURSE);
            mDbHelper.addCourse(c);
        }
        else if (action.equals(Constants.ADD_PLAYER))
        {
            Game g = (Game) intent.getSerializableExtra(Constants.K_GAME);
            mDbHelper.addGame(g);
        }
        else if (action.equals(Constants.EDIT_PLAYER))
        {

        }
        else if (action.equals(Constants.EDIT_PLAYER))
        {

        }
        else if (action.equals(Constants.DEL_PLAYER))
        {

        }
        else if (action.equals(Constants.DEL_GAME))
        {

        }
        else if (action.equals(Constants.DEL_COURSE))
        {

        }
        finish();
        startActivity(getIntent());
    }
}