package sailedeer.scorecard.activities.fragments.handling;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.GameActivity;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.activities.fragments.GameTabFragment;

/**
 * Created by Eli on 3/8/2017.
 */

public class GameFragmentListAdapter extends BaseAdapter implements View.OnClickListener {

    private GameTabFragment fragment;
    private ArrayList<Game> data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Game tempValues = null;
    int i = 0;

    public GameFragmentListAdapter(Fragment a, ArrayList d, Resources resLocal)
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

    public int getSize()
    {
        return data.size();
    }

    public Game getItem(int position)
    {
        return data.get(position);
    }

    public void remove(int position){
        data.remove(position);
    }

    public void remove(Game g) {
        data.remove(g);
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
//            holder.gameName = (TextView) vi.findViewById(R.id.player_name);
//            holder.courseName = (TextView) vi.findViewById(R.id.handicap);
//            holder.currentHole = (TextView) vi.findViewById(R.id.hole);

            vi.setTag(holder);
        }
        else
            holder = (ViewHolder)vi.getTag();

        if (data.size()<=0) {
//            holder.gameName.setText("No Data");
//            holder.courseName.setText("");
//            holder.currentHole.setText("");
        }
        else
//        {
//            tempValues = null;
//            tempValues = (Game)data.get(position);
//
//            holder.courseName.setText("Course: " + tempValues.getCourseName());
//            holder.currentHole.setText("Current Hole: " + tempValues.getCurrentHole());

            vi.setOnClickListener(new OnItemClickListener(position));
//        }
        return vi;
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(fragment.getContext(), GameActivity.class);
        fragment.startActivity(intent);
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
            //fragment.onItemClick(mPosition);
        }
    }
}

