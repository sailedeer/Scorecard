package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewCourseActivity;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.handling.PlayerListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;

/**
 * Created by Eli on 3/7/2017.
 */

public class PlayerTabFragment extends ListFragment {

    ListView list;
    PlayerListAdapter adapter;
    public PlayerTabFragment customListView = null;
    public ArrayList<Player> customListViewArrs = new ArrayList<>();
    private SQLiteDatabase db;
    private DatabaseHelper mDbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        mDbHelper = new DatabaseHelper(getContext());
        customListView = this;

        Resources res = getResources();

        setListData();

        adapter = new PlayerListAdapter(customListView, customListViewArrs, res);

        setListAdapter(adapter);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return viewGroup;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_player, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy()
    {
        mDbHelper.close();
        super.onDestroy();
    }

    public void onItemClick(int mPosition)
    {
        Player tempValues = ( Player ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_player:
                Intent intent = new Intent(getContext(), NewPlayerActivity.class);
                startActivity(intent);
                return true;
            case R.id.remove_player:
                //remove player here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setListData()
    {
        customListViewArrs = mDbHelper.getAllPlayers();
    }
}
