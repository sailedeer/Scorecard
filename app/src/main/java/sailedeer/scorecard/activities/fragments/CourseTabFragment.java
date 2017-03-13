package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.handling.CourseListAdapter;

/**
 * Created by Eli on 3/7/2017.
 */

public class CourseTabFragment extends ListFragment{

    ListView list;
    CourseListAdapter adapter;
    public CourseTabFragment customListView = null;
    public ArrayList<Course> customListViewArrs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        customListView = this;

        Resources res = getResources();

        adapter = new CourseListAdapter(customListView, customListViewArrs, res);

        setListData();
        setListAdapter(adapter);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return viewGroup;
    }

    private void setListData()
    {
        for (int i = 0; i < 11; i++)
        {
            final Course course = new Course("Course name", 100);
            customListViewArrs.add(course);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_course, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onItemClick(int mPosition)
    {
        Course tempValues = ( Course ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        startActivity(intent);
    }

    //stand-in until I figure out SQL in android
    private Course[] retrieveCoursesFromDB()
    {
        return null;
    }
}
