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
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.util.Constants;

/**
 * Created by Eli on 3/8/2017.
 */

public class GameFragmentListAdapter extends BaseAdapter {

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
        public TextView playerNames;
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
            holder.playerNames = (TextView) vi.findViewById(R.id.game_tab_players);
            holder.courseName = (TextView) vi.findViewById(R.id.game_tab_course);
            holder.currentHole = (TextView) vi.findViewById(R.id.game_tab_hole);

            vi.setTag(holder);
        }
        else
            holder = (ViewHolder)vi.getTag();

        if (data.size()<=0) {
            holder.playerNames.setText("No games");
            holder.courseName.setText("");
            holder.currentHole.setText("");

            holder.playerNames.setGravity(50);
        }
        else
        {
            tempValues = null;
            tempValues = data.get(position);

            String initials = "";
            Player[] players = tempValues.getPlayers();
            for (int j = 0; j < players.length; j++) {
                initials += players[j].getInitials() + (j == players.length - 1 ? "" : ", ");
            }

            holder.playerNames.setText(initials);
            holder.courseName.setText("Course: " + tempValues.getCourse().getName());
            holder.currentHole.setText("Current Hole: " + tempValues.getCurrentHole());

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
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
            fragment.onClick(mPosition);
        }
    }
}

