package sailedeer.scorecard.data.sql;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.Player;

/**
 * Created by Eli on 3/13/2017.
 */

@Deprecated
public class DatabaseHelper extends SQLiteOpenHelper {

    // Log tag
    private static final String LOG = "DatabaseHelper:";

    // database name
    private static final String DATABASE_NAME = "Scorecard.db";

    // database version
    public static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_PLAYER = "player_table";
    private static final String TABLE_GAME = "game_table";
    private static final String TABLE_COURSE = "course_table";

    // Common columns
    private static final String KEY_ID = "id";

    // Player Column Names
    public static final String KEY_NAME = "name";
    public static final String KEY_HANDICAP = "handicap";

    // Game Column Names
    public static final String KEY_SCORES = "scores";
    public static final String KEY_COURSE_ID = "course_id";
    public static final String KEY_PLAYER_ID = "player_ids";
    public static final String GAME_TYPE_COLUMN = "game_type";
    public static final String HOLE_COLUMN = "hole";

    // Course Column Names
    public static final String COURSE_COLUMN = "name";
    public static final String SLOPE_COLUMN = "slope";

    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE " +
            TABLE_PLAYER + "(" + KEY_ID + " INTEGER PRIMARY KEY " +
            KEY_NAME + " TEXT, " + KEY_HANDICAP + "INTEGER" +
            ")";

    private static final String CREATE_TABLE_GAME = "CREATE TABLE " +
            TABLE_GAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_COURSE_ID +
            "INTEGER," + KEY_PLAYER_ID + "TEXT," + GAME_TYPE_COLUMN +
            "TEXT," + HOLE_COLUMN + "INTEGER," + KEY_SCORES + "TEXT" + ")";

    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " +
            TABLE_COURSE + "(" + KEY_ID + "INTEGER PRIMARY KEY," +
            COURSE_COLUMN + "TEXT," + SLOPE_COLUMN + "INTEGER" + ")";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_PLAYER);
        db.execSQL(CREATE_TABLE_GAME);
        db.execSQL(CREATE_TABLE_COURSE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    //fetch all games
    public List<Game> getGames()
    {
        List<Game> games = new ArrayList<Game>();
        int[] scores;
        String[] splitPlayers;
        String[] splitScores;

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_GAME;

        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        //loop through rows
        if(cursor.moveToFirst())
        {
            do {
                Game game = new Game();
                splitPlayers = cursor.getString(cursor.getColumnIndex(KEY_PLAYER_ID)).split(",");
                splitScores = cursor.getString(cursor.getColumnIndex(KEY_SCORES)).split(",");
                scores = new int[splitScores.length];

                //get players based on ID in player_table
                //get course based on course ID in course_table
                game.setmCurrentHole(cursor.getInt(cursor.getColumnIndex(HOLE_COLUMN)));

                //convert scores to array to pass into game
                for (int i = 0; i < splitScores.length; i++)
                {
                    scores[i] = Integer.parseInt(splitScores[i]);
                }

                game.setmRoundScore(scores);
                game.setmID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));


            } while (cursor.moveToNext());
        }

        return null;
    }

    public ArrayList<Course> getCourses()
    {
        return null;
    }

    public ArrayList<Player> getPlayers()
    {
        return null;
    }
}
