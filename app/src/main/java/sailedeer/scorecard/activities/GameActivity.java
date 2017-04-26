package sailedeer.scorecard.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import sailedeer.scorecard.R;
import sailedeer.scorecard.activities.handling.GameEditViewAdapter;

public class GameActivity extends AppCompatActivity {

    private GridView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameView = (GridView) findViewById(R.id.game_grid);

        gameView.setAdapter(new GameEditViewAdapter(this, new String[90]));
    }
}
