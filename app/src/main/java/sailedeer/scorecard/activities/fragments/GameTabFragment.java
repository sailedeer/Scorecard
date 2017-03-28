package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        return viewGroup;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState)
    {
        registerForContextMenu(getListView());
    }

    public void onItemClick(int mPosition)
    {
        Game tempValues = ( Game ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewGameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.game_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.remove_game:
                //throw up a toast and ask if the user wants to delete the thing
                return true;
            case R.id.new_game:
                Intent intent = new Intent(getContext(), NewGameActivity.class);
                startActivity(intent);
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void setListData()
    {
        //customListViewArrs = databaseHelper.getAllGames();
    }
}
