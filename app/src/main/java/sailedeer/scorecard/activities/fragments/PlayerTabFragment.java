package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewCourseActivity;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.handling.PlayerListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;
import sailedeer.scorecard.util.Constants;

/**
 * Created by Eli on 3/7/2017.
 */

public class PlayerTabFragment extends ListFragment {

    Player toEdit;
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
        return viewGroup;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState)
    {
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        try {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.player_context_menu, menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        switch (item.getItemId()) {
            case R.id.edit_player:
                toEdit = adapter.getItem(info.position);
                boolean edit = true;
                intent.setAction(Constants.EDIT_PLAYER);
                intent.putExtra(Constants.K_PLAYER, toEdit);
                intent.putExtra(Constants.BOOL_EDIT, edit);
                startActivity(intent);
                return true;
            case R.id.new_player:
                intent.setAction(Constants.ADD_PLAYER);
                startActivity(intent);
                return true;
            case R.id.remove_player:
                toEdit = adapter.getItem(info.position);
                mDbHelper.removePlayer(toEdit);
                //getActivity().finish();
                startActivity(getActivity().getIntent());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onItemClick(int mPosition)
    {
        //Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        //startActivity(intent);
    }

    public void onItemLongClick(int mPosition)
    {
        toEdit = customListViewArrs.get(mPosition);
    }

    public void setListData()
    {
        customListViewArrs = mDbHelper.getAllPlayers();
    }


}
