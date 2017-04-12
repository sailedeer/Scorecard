package sailedeer.scorecard.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.util.Constants;

public class NewPlayerActivity extends AppCompatActivity {

    Player toEdit;
    private ActionBar ab;
    private Intent newPlayerIntent;
    private Button addPlayer;
    private EditText edtHandicap;
    private EditText edtName;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_np);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();

        addPlayer = (Button) findViewById(R.id.btnAddPlayer);
        edtHandicap = (EditText) findViewById(R.id.edtTxtHandicap);
        edtName = (EditText) findViewById(R.id.edtTxtName);

        addPlayer.setOnClickListener(new AddPlayerOnClickListener());

        if (getIntent().getAction().equals(Constants.EDIT_PLAYER))
        {
            isEditing = true;
        }

        if (isEditing) {
            ab.setTitle("Edit Player");
            toEdit = (Player) getIntent().getSerializableExtra(Constants.K_PLAYER);
            edtName.setText(toEdit.getName());
            edtHandicap.setText(Integer.toString(toEdit.getHandicap()));
            addPlayer.setText("Save Player");
        }
    }

    class AddPlayerOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            try {
                String name = edtName.getText().toString();
                try {
                    int handicap = Integer.parseInt(edtHandicap.getText().toString());

                    if (edtName.getText().equals("") || edtHandicap.getText().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Bad data", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (name.isEmpty()) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Bad data", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (handicap == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Bad data", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Player p = new Player(name, handicap);
                        if (isEditing) p.setId(toEdit.getId());
                        newPlayerIntent = new Intent();
                        newPlayerIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        newPlayerIntent.setAction(isEditing ? Constants.EDIT_PLAYER : Constants.ADD_PLAYER);
                        newPlayerIntent.setClass(getApplicationContext(), MainActivity.class);
                        //if (isEditing) newPlayerIntent.putExtra(Constants.K_PLAYER + "_edit", toEdit);
                        newPlayerIntent.putExtra(Constants.K_PLAYER, p);
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
