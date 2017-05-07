package sailedeer.scorecard.activities.fragments.handling;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.activities.fragments.CourseTabFragment;

/**
 * Created by Eli on 3/9/2017.
 */

public class CourseFragmentListAdapter extends BaseAdapter implements View.OnClickListener {

    private CourseTabFragment fragment;
    private ArrayList<Course> data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Course tempValues = null;
    int i = 0;

    public CourseFragmentListAdapter(Fragment a, ArrayList d, Resources resLocal)
    {
        fragment = (CourseTabFragment) a;
        data = d;
        res = resLocal;

        inflater = a.getActivity().getLayoutInflater();
    }

    public int getCount()
    {
        if(data.size() <= 0)
            return 1;
        return data.size();
    }

    public int getSize()
    {
        return data.size();
    }

    public Course getItem(int position)
    {
        return data.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        public TextView courseName;
        public TextView slope;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        CourseFragmentListAdapter.ViewHolder holder;

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.course_row_layout, null);

            holder = new CourseFragmentListAdapter.ViewHolder();
            holder.courseName = (TextView) vi.findViewById(R.id.course);
            holder.slope = (TextView) vi.findViewById(R.id.slope);

            vi.setTag(holder);
        }
        else
            holder = (CourseFragmentListAdapter.ViewHolder)vi.getTag();

        if (data.size()<=0) {
            holder.courseName.setText("No courses");
            holder.slope.setText("");
        }
        else
        {
            tempValues = null;
            tempValues = data.get(position);
            holder.courseName.setText(tempValues.getName());
            holder.slope.setText("Slope: " + tempValues.getSlope());
        }
        return vi;
    }

    public void onClick(View v)
    {

    }

    public void remove(int position)
    {
        data.remove(position);
    }

    public void remove(Course c)
    {
        data.remove(c);
    }

    private class OnItemLongClickListener implements View.OnLongClickListener {
        private int mPosition;

        OnItemLongClickListener(int position)
        {
            mPosition = position;
        }

        @Override
        public boolean onLongClick(View arg0)
        {
            fragment.onItemLongClick(mPosition);
            return true;
        }
    }
}
