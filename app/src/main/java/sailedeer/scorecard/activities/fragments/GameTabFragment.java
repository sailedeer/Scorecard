package sailedeer.scorecard.activities.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.NewPlayerActivity;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.GameType;
import sailedeer.scorecard.data.handling.GameListAdapter;

/**
 * Created by Eli on 3/7/2017.
 */

public class GameTabFragment extends ListFragment {

    ListView list;
    GameListAdapter adapter;
    public GameTabFragment customListView = null;
    public ArrayList<Game> customListViewArrs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
        //inflater.inflate();
    }

    public void onItemClick(int mPosition)
    {
        Game tempValues = ( Game ) customListViewArrs.get(mPosition);
        Intent intent = new Intent(getContext(), NewPlayerActivity.class);
        startActivity(intent);
    }

    private void setListData()
    {
        for (int i = 0; i < 11; i++)
        {
            final Game game = new Game(new Course("Name", 100), GameType.Individual, null);
            customListViewArrs.add(game);
        }
    }
}
