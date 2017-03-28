package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    Course toEdit;
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
        return viewGroup;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState)
    {
        registerForContextMenu(getListView());
    }

    public void setListData()
    {
        customListViewArr = dbHelper.getAllCourses();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.course_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_course:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onItemLongClick(int mPosition)
    {
        toEdit = customListViewArr.get(mPosition);
    }
}
