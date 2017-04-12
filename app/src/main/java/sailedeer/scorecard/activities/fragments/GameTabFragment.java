package sailedeer.scorecard.activities.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewGameActivity;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.activities.fragments.handling.GameFragmentListAdapter;
import sailedeer.scorecard.data.sql.DatabaseHelper;

/**
 * Created by Eli on 3/7/2017.
 */

public class GameTabFragment extends ListFragment {

    Game toEdit;
    DatabaseHelper mDbHelper;
    GameFragmentListAdapter adapter;
    public GameTabFragment customListView = null;
    public ArrayList<Game> customListViewArrs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDbHelper = new DatabaseHelper(getContext());
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.tab_layout, container, false);
        customListView = this;

        Resources res = getResources();

        adapter = new GameFragmentListAdapter(customListView, customListViewArrs, res);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(
                adapter.getSize() <= 0 ? R.menu.game_context_menu_editless : R.menu.game_context_menu,
                menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.remove_game:
                toEdit = adapter.getItem(info.position);
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Player")
                        .setMessage("Are you sure you want to delete this player?")
                        .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDbHelper.removeGame(toEdit);
                                adapter.remove(toEdit);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
