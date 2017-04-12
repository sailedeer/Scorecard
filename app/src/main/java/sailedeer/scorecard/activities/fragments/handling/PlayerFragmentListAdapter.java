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
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.activities.fragments.PlayerTabFragment;

/**
 * Created by Eli on 3/9/2017.
 */

public class PlayerFragmentListAdapter extends BaseAdapter implements View.OnClickListener {

    private PlayerTabFragment fragment;
    private ArrayList<Player> data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Player tempValues = null;
    int i = 0;

    public PlayerFragmentListAdapter(Fragment a, ArrayList d, Resources resLocal)
    {
        fragment = (PlayerTabFragment)a;
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

    public Player getItem(int position)
    {
        return data.get(position);
    }

    public void remove(int position)
    {
        data.remove(position);
    }

    public void remove(Player p)
    {
        data.remove(p);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        public TextView playerName;
        public TextView handicap;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        PlayerFragmentListAdapter.ViewHolder holder;

        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.player_row_layout, null);

            holder = new PlayerFragmentListAdapter.ViewHolder();
            holder.playerName = (TextView) vi.findViewById(R.id.player_name);
            holder.handicap = (TextView) vi.findViewById(R.id.handicap);

            vi.setTag(holder);
        }
        else
            holder = (PlayerFragmentListAdapter.ViewHolder)vi.getTag();

        if (data.size()<=0) {
            holder.playerName.setText("No Data");
            holder.handicap.setText("");
        }
        else {
            tempValues = null;
            tempValues = data.get(position);
            holder.playerName.setText(tempValues.getName());
            holder.handicap.setText("Handicap: " + tempValues.getHandicap());

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
