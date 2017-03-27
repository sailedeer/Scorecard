package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
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
import sailedeer.scorecard.activities.NewGameActivity;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.handling.GameListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;

/**
 * Created by Eli on 3/7/2017.
 */

public class GameTabFragment extends ListFragment {

    ListView list;
    DatabaseHelper databaseHelper;
    GameListAdapter adapter;
    public GameTabFragment customListView = null;
    public ArrayList<Game> customListViewArrs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        customListView = this;

        Resources res = getResources();

        adapter = new GameListAdapter(customListView, customListViewArrs, res);

        setListData();

        setListAdapter(adapter);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return viewGroup;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_game, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onItemClick(int mPosition)
    {
        Game tempValues = ( Game ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewGameActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                Intent intent = new Intent(getContext(), NewGameActivity.class);
                startActivity(intent);
                return true;
            case R.id.remove_game:
                //remove player here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setListData()
    {
        //customListViewArrs = databaseHelper.getAllGames();
    }
}
