package sailedeer.scorecard.data.handling;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.activities.fragments.GameTabFragment;

/**
 * Created by Eli on 3/8/2017.
 */

public class GameListAdapter extends BaseAdapter implements View.OnClickListener {

    private GameTabFragment fragment;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Game tempValues = null;
    int i = 0;

    public GameListAdapter(Fragment a, ArrayList d, Resources resLocal)
    {
        fragment = (GameTabFragment) a;
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
        public TextView gameName;
        public TextView courseName;
        public TextView currentHole;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.game_row_layout, null);

            holder = new ViewHolder();
            holder.gameName = (TextView) vi.findViewById(R.id.player_name);
            holder.courseName = (TextView) vi.findViewById(R.id.handicap);
            holder.currentHole = (TextView) vi.findViewById(R.id.hole);

            vi.setTag(holder);
        }
        else
            holder = (ViewHolder)vi.getTag();

        if (data.size()<=0)
            holder.gameName.setText("No Data");
        else
        {
            tempValues = null;
            tempValues = (Game)data.get(position);

            holder.courseName.setText("Course: " + tempValues.getCourseName());
            holder.currentHole.setText("Current Hole: " + tempValues.getCurrentHole());

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    public void onClick(View v)
    {
        //launch some new activity
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position)
        {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0)
        {
            fragment.onItemClick(mPosition);
        }
    }
}

