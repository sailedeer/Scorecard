package sailedeer.scorecard.data.handling;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.activities.fragments.CourseTabFragment;

/**
 * Created by Eli on 3/9/2017.
 */

public class CourseListAdapter extends BaseAdapter implements View.OnClickListener {

    private CourseTabFragment fragment;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Course tempValues = null;
    int i = 0;

    public CourseListAdapter(Fragment a, ArrayList d, Resources resLocal)
    {
        fragment = (CourseTabFragment) a;
        data = d;
        res = resLocal;

        inflater = a.getActivity().getLayoutInflater();
    }

    public int getCount()
    {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position)
    {
        return position;
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
        CourseListAdapter.ViewHolder holder;

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.course_row_layout, null);

            holder = new CourseListAdapter.ViewHolder();
            holder.courseName = (TextView) vi.findViewById(R.id.course);
            holder.slope = (TextView) vi.findViewById(R.id.slope);

            vi.setTag(holder);
        }
        else
            holder = (CourseListAdapter.ViewHolder)vi.getTag();

        if (data.size()<=0)
            holder.courseName.setText("No Data");
        else
        {
            tempValues = null;
            tempValues = (Course)data.get(position);
            holder.courseName.setText("Course: " + tempValues.getName());
            holder.slope.setText("Slope: " + tempValues.getSlope());

            //vi.setOnLongClickListener(new OnItemLongClickListener(position));
        }
        return vi;
    }

    public void onClick(View v)
    {

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
