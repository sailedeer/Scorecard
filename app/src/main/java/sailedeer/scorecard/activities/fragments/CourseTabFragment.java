package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewCourseActivity;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.handling.CourseListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;

/**
 * Created by Eli on 3/7/2017.
 */

public class CourseTabFragment extends ListFragment{

    ListView list;
    DatabaseHelper dbHelper;
    CourseListAdapter adapter;
    public CourseTabFragment customListView = null;
    public ArrayList<Course> customListViewArr = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //get an instance of the database helper
        dbHelper = new DatabaseHelper(getContext());

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        customListView = this;

        Resources res = getResources();

        setListData();
        adapter = new CourseListAdapter(customListView, customListViewArr, res);
        setListAdapter(adapter);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return viewGroup;
    }

    private void setListData()
    {
        customListViewArr = dbHelper.getAllCourses();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_course, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onItemClick(int mPosition)
    {
        Course tempValues = ( Course ) customListViewArr.get(mPosition);
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_course:
                Intent npIntent = new Intent(getContext(), NewCourseActivity.class);
                startActivity(npIntent);
                return true;
            case R.id.remove_course:
                //Intent rpIntent = new Intent(getContext(), NewPlayerActivity.class);
                //startActivity(rpIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
