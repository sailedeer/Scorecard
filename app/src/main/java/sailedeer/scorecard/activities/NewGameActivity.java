package sailedeer.scorecard.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.sql.DatabaseHelper;
import sailedeer.scorecard.util.Constants;

public class NewGameActivity extends AppCompatActivity {

    Context context;

    Spinner spinnerCourses;
    Spinner spinnerPlayers;
    RadioButton rbFour;
    RadioButton rbFive;
    Button buttonAddPlayer;
    Button buttonStartGame;
    ListView playerListView;

    boolean fourChecked = false;
    boolean fiveChecked = false;

    int maxPlayers = 4;

    DatabaseHelper mDbHelper;

    Course selectedCourse;
    ArrayList<Course> courses;

    Player tempPlayerSelected;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> selectedPlayers = new ArrayList<>();

    ArrayList<String> courseNames = new ArrayList<>();
    ArrayList<String> playerNames = new ArrayList<>();

    ArrayAdapter<String> playerSpinAdapter;
    ArrayAdapter<String> courseSpinAdapter;

    ArrayAdapter<String> playerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        mDbHelper = new DatabaseHelper(this);

        playerListViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        courses = mDbHelper.getAllCourses();
        players = mDbHelper.getAllPlayers();

        for (Course c : courses)
        {
            courseNames.add(c.getName());
        }

        for (Player p : players)
        {
            playerNames.add(p.getName());
        }

        spinnerCourses = (Spinner) findViewById(R.id.spinner_courses);
        spinnerPlayers = (Spinner) findViewById(R.id.spinner_players);
        rbFour = (RadioButton) findViewById(R.id.radio_button_five_p);
        rbFive = (RadioButton) findViewById(R.id.radio_button_five_p);
        buttonAddPlayer = (Button) findViewById(R.id.button_add_player);
        playerListView = (ListView) findViewById(R.id.list_selected_players);
        buttonStartGame = (Button) findViewById(R.id.button_start_game);

        rbFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                fourChecked = isChecked;
                if (fourChecked) maxPlayers = 4;
                else maxPlayers = 5;
            }
        });

        rbFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                fiveChecked = isChecked;
                if (isChecked) maxPlayers = 5;
                else maxPlayers = 4;
            }
        });

        courseSpinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseNames);
        courseSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(courseSpinAdapter);

        playerSpinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerNames);
        playerSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlayers.setAdapter(playerSpinAdapter);

        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = getCourse((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing for now
            }
        });


        spinnerPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tempPlayerSelected = getPlayer((String) parent.getItemAtPosition(position));
                int item = spinnerPlayers.getSelectedItemPosition();
                tempPlayerSelected = getPlayer(playerSpinAdapter.getItem(item));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //tempPlayerSelected = getPlayer((String)parent.getItemAtPosition(0));    //if there is no selection, choose the first available item
            }
        });

        buttonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPlayers.size() < maxPlayers) {
                    if (tempPlayerSelected != null) {
                        selectedPlayers.add(tempPlayerSelected);
                        playerListViewAdapter.add(tempPlayerSelected.getName());
                        playerSpinAdapter.remove(tempPlayerSelected.getName());
                        spinnerPlayers.setAdapter(playerSpinAdapter);   //updates adapter with new data set
                    }
                    else {
                        new AlertDialog.Builder(context)
                                .setTitle("No Selection")
                                .setMessage("Please select a player before adding them to this game.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
                else {
                    new AlertDialog.Builder(context)
                            .setTitle("Too Many Players")
                            .setMessage("You have selected too many players. Change your selection to add more.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPlayers.size() < maxPlayers) {
                    new AlertDialog.Builder(context)
                            .setTitle("Not Enough Players")
                            .setMessage("You haven't selected enough players.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    Intent newGameIntent = new Intent(context, GameActivity.class);
                    Player[] temp = new Player[selectedPlayers.size()];

                    for(int i = 0; i < selectedPlayers.size(); i++)
                    {
                        temp[i] = selectedPlayers.get(i);
                    }
                    Game game = new Game(selectedCourse, temp);
                    newGameIntent.setAction(Constants.START_GAME);
                    newGameIntent.putExtra(Constants.K_GAME, game);
                    startActivity(newGameIntent);
                }
            }
        });

        playerListView.setAdapter(playerListViewAdapter);
        registerForContextMenu(playerListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_game_player_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.
                getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.remove_player_from_game:
                final String toRemove = selectedPlayers.get(info.position).getName();
                if (toRemove != null) {
                    new AlertDialog.Builder(this)
                            .setTitle("Delete Player")
                            .setMessage("Are you sure you want to delete this player?")
                            .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    playerListViewAdapter.remove(toRemove);
                                    playerSpinAdapter.add(toRemove);
                                    selectedPlayers.remove(getPlayer(toRemove));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private Course getCourse(String name) {
        for (Course c : courses) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    private Player getPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }
}
