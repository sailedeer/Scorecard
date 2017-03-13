package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.handling.PlayerListAdapter;

/**
 * Created by Eli on 3/7/2017.
 */

public class PlayerTabFragment extends ListFragment {

    ListView list;
    PlayerListAdapter adapter;
    public PlayerTabFragment customListView = null;
    public ArrayList<Player> customListViewArrs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);

        customListView = this;

        Resources res = getResources();

        adapter = new PlayerListAdapter(customListView, customListViewArrs, res);

        setListData();

        setListAdapter(adapter);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return viewGroup;
    }


    public void onItemClick(int mPosition)
    {
        Player tempValues = ( Player ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        startActivity(intent);
    }

    private void setListData()
    {
        for (int i = 0; i < 11; i++)
        {
            final Player player = new Player("Player", 6);
            customListViewArrs.add(player);
        }
    }
}
