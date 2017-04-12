package sailedeer.scorecard.activities;

import android.content.DialogInterface;
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

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.data.sql.DatabaseHelper;

public class NewGameActivity extends AppCompatActivity {

    Spinner spinnerCourses;
    Spinner spinnerPlayers;
    RadioButton rbFour;
    RadioButton rbFive;
    Button buttonAddPlayer;
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

        rbFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) maxPlayers = 4;

                fourChecked = isChecked;
                fiveChecked = !isChecked;
            }
        });

        rbFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) maxPlayers = 5;

                fourChecked = !isChecked;
                fiveChecked = isChecked;
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
                tempPlayerSelected = getPlayer((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing for now
            }
        });

        buttonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPlayers.size() < maxPlayers) {
                    if (tempPlayerSelected != null) {
                        selectedPlayers.add(tempPlayerSelected);
                        playerListViewAdapter.add(tempPlayerSelected.getName());
                        tempPlayerSelected = null;
                    }
                    else {
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle("No Selection")
                                .setMessage("Please select a player before adding them to this game.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //do nothing
                                    }
                                })
                                .show();
                    }
                }
                else {
                    new AlertDialog.Builder(getApplicationContext())
                            .setTitle("Too Many Players")
                            .setMessage("You have selected too many players. Change your selection to add more.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .show();
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
