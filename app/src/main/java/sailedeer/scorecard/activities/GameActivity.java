package sailedeer.scorecard.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.handling.GameEditViewAdapter;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.sql.DatabaseHelper;
import sailedeer.scorecard.util.Constants;

public class GameActivity extends AppCompatActivity {

    private GridView gameView;
    private ActionBar ab;
    private Game game;
    private ArrayList<EditText> ets = new ArrayList<>();
    private GameEditViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_4);

        game = (Game) getIntent().getSerializableExtra(Constants.K_GAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_g);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        gameView = (GridView) findViewById(R.id.game_grid);

        adapter = new GameEditViewAdapter(this, new String[90]);

        gameView.setAdapter(adapter);

        ab.setTitle(game.getCourse().getName());

        ets = adapter.getTextFields();
    }

    @Override
    public void onBackPressed()
    {
        Intent mainStart = new Intent();
        mainStart.setAction(Intent.ACTION_MAIN);
        mainStart.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainStart.setClass(this, MainActivity.class);
        startActivity(mainStart);
    }
}
