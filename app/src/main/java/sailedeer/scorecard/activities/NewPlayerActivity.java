package sailedeer.scorecard.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Player;

public class NewPlayerActivity extends AppCompatActivity {

    Intent newPlayerIntent;
    Bundle b;
    Button addPlayer;
    EditText edtHandicap;
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_player);
        addPlayer = (Button) findViewById(R.id.btnAddPlayer);
        edtHandicap = (EditText) findViewById(R.id.edtTxtHandicap);
        edtName = (EditText) findViewById(R.id.edtTxtName);

        addPlayer.setOnClickListener(new AddPlayerOnClickListener());
    }

    class AddPlayerOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            try {
                String name = edtName.getText().toString();
                try {
                    int handicap = Integer.parseInt(edtHandicap.getText().toString());

                    if (name.isEmpty() && handicap == 0) {
                        //this is way wrong
                    } else if (name.isEmpty()) {
                        //throw some kind of error
                    } else if (handicap == 0) {
                        //throw some other kind of error
                    } else {
                        Player p = new Player(name, handicap);
                        newPlayerIntent = new Intent();
                        newPlayerIntent.setAction("ADD_PLAYER");
                        newPlayerIntent.setClass(getApplicationContext(), MainActivity.class);
                        newPlayerIntent.putExtra("player", p);
                        startActivity(newPlayerIntent);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (Exception sne)
            {
                sne.printStackTrace();
            }
        }
    }
}
