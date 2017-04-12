package sailedeer.scorecard.activities.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewCourseActivity;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.activities.fragments.handling.CourseFragmentListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;
import sailedeer.scorecard.util.Constants;

/**
 * Created by Eli on 3/7/2017.
 */

public class CourseTabFragment extends ListFragment{

    Course toEdit;
    ListView list;
    DatabaseHelper mDbHelper;
    CourseFragmentListAdapter adapter;
    public CourseTabFragment customListView = null;
    public ArrayList<Course> customListViewArr = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //get an instance of the database helper
        mDbHelper = new DatabaseHelper(getContext());

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        customListView = this;

        Resources res = getResources();

        setListData();
        adapter = new CourseFragmentListAdapter(customListView, customListViewArr, res);
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
        customListViewArr = mDbHelper.getAllCourses();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(
                adapter.getSize() <= 0 ? R.menu.course_context_menu_editless : R.menu.course_context_menu,
                menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent(getContext(), NewCourseActivity.class);
        switch (item.getItemId()) {
            case R.id.new_course:
                intent.setAction(Constants.ADD_COURSE);
                startActivity(intent);
                return true;
            case R.id.edit_course:
                toEdit = adapter.getItem(info.position);
                intent.setAction(Constants.EDIT_COURSE);
                intent.putExtra(Constants.K_COURSE, toEdit);
                startActivity(intent);
                return true;
            case R.id.remove_course:
                toEdit = adapter.getItem(info.position);
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Course")
                        .setMessage("Are you sure you want to delete this course?")
                        .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDbHelper.removeCourse(toEdit);
                                adapter.remove(toEdit);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
