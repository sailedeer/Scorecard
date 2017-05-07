package sailedeer.scorecard.data.sql;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Game;
import sailedeer.scorecard.data.Player;

/**
 * Created by Eli on 3/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    // Log tag
    private static final String LOG = "DatabaseHelper:";

    // database name
    public static final String DATABASE_NAME = "scorecard.db";

    // database version
    public static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_PLAYER = "player_table";
    private static final String TABLE_GAME = "game_table";
    private static final String TABLE_COURSE = "course_table";

    // Common columns
    private static final String KEY_ID = "id";

    // Player Column Names
    private static final String KEY_PLAYER_NAME = "name";
    private static final String KEY_HANDICAP = "handicap";
    private static final String[] PLAYER_COLUMNS = {KEY_ID, KEY_PLAYER_NAME, KEY_HANDICAP};

    // Game Column Names
    private static final String KEY_SCORES = "scores";
    private static final String KEY_COURSE_ID = "course_id";
    private static final String KEY_PLAYER_IDS = "player_ids";
    private static final String KEY_HOLE = "hole";
    private static final String[] GAME_COLUMNS = {KEY_ID, KEY_SCORES, KEY_COURSE_ID, KEY_PLAYER_IDS, KEY_HOLE};

    // Course Column Names
    private static final String KEY_COURSE = "name";
    private static final String KEY_SLOPE = "slope";
    private static final String[] COURSE_COLUMNS = {KEY_ID, KEY_COURSE, KEY_SLOPE};

    // Database create statements
    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE " +
            TABLE_PLAYER + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_PLAYER_NAME + " TEXT, " + KEY_HANDICAP + " INTEGER " +
            ")";

    private static final String CREATE_TABLE_GAME = "CREATE TABLE " +
            TABLE_GAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_COURSE_ID +
            " INTEGER, " + KEY_PLAYER_IDS + " TEXT, " +
            KEY_HOLE + " INTEGER, " + KEY_SCORES + " TEXT " + ")";

    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " +
            TABLE_COURSE + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_COURSE + " TEXT, " + KEY_SLOPE + " INTEGER " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PLAYER);
        db.execSQL(CREATE_TABLE_GAME);
        db.execSQL(CREATE_TABLE_COURSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //fetch all games
    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();
        int[] pIds;
        Player[] players;
        int[] scores;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_GAME;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        //loop through rows
        if (c.moveToFirst()) {
            do {
                //split ids from db into array
                String[] strPIds = c.getString(c.getColumnIndex(KEY_PLAYER_IDS)).split(",");
                pIds = new int[strPIds.length];
                for (int j = 0; j < strPIds.length; j++) {
                    pIds[j] = Integer.parseInt(strPIds[j].trim());
                }
                //now add those ids to int[] to pass into method that gets players
                players = new Player[pIds.length];
                for (int i = 0; i < pIds.length; i++) {
                    players[i] = getPlayer(pIds[i]);
                }

                //split comma-delineated scores into int[]
                String[] strScores = c.getString(c.getColumnIndex(KEY_SCORES)).split(",");
                scores = new int[strScores.length];
                for (int j = 0; j < strScores.length; j++)
                {
                    scores[j] = Integer.parseInt(strScores[j]);
                }

                Game game = new Game();
                game.setID(c.getInt(c.getColumnIndex(KEY_ID)));
                game.setCourse(getCourse(c.getInt(c.getColumnIndex(KEY_COURSE_ID))));
                game.setPlayers(players);
                game.setRoundScore(scores);
                game.setCurrentHole(c.getInt(c.getColumnIndex(KEY_HOLE)));
                games.add(game);
            } while (c.moveToNext());
        }

        db.close();
        return games;
    }


    public ArrayList<Course> getAllCourses()
    {
        ArrayList<Course> courses = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        //loop through rows
        if (c.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                course.setName(c.getString(c.getColumnIndex(KEY_COURSE)));
                course.setSlope(c.getInt(c.getColumnIndex(KEY_SLOPE)));
                courses.add(course);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return courses;
    }

    public ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> players = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PLAYER;

        Log.e(LOG, selectQuery);

        try {
            Cursor c = db.rawQuery(selectQuery, null);

            //loop through rows
            if (c.moveToFirst()) {
                do {
                    Player player = new Player();
                    player.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                    player.setName(c.getString(c.getColumnIndex(KEY_PLAYER_NAME)));
                    player.setHandicap(c.getInt(c.getColumnIndex(KEY_HANDICAP)));
                    players.add(player);
                } while (c.moveToNext());
            }

            c.close();
            db.close();
        }
        catch(SQLiteException sqe)
        {
            Log.e(LOG, sqe.getMessage());
        }
        return players;
    }

    public Game getGame(int id) {

        String[] pIds;
        Player[] players;
        int[] scores;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_GAME,
                GAME_COLUMNS,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (c != null)
            c.moveToFirst();

        //split ids from db into array
        pIds = c.getString(c.getColumnIndex(KEY_PLAYER_IDS)).split(",");
        //now add those ids to int[] to pass into method that gets players
        players = new Player[pIds.length];
        for (int i = 0; i < pIds.length; i++) {
            players[i] = getPlayer(Integer.parseInt(pIds[i]));
        }

        //split comma-delineated scores into int[]
        String[] strScores = c.getString(c.getColumnIndex(KEY_SCORES)).split(",");
        scores = new int[strScores.length];
        for (int j = 0; j < strScores.length; j++)
        {
            scores[j] = Integer.parseInt(strScores[j]);
        }

        Game game = new Game();
        game.setID(c.getInt(c.getColumnIndex(KEY_ID)));
        game.setCourse(getCourse(c.getInt(c.getColumnIndex(KEY_COURSE_ID))));
        game.setPlayers(players);
        game.setRoundScore(scores);
        game.setCurrentHole(c.getInt(c.getColumnIndex(KEY_HOLE)));

        c.close();
        db.close();
        return game;
    }

    public Course getCourse(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_COURSE,
                COURSE_COLUMNS,
                "id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null);

        if (c != null)
            c.moveToFirst();

        Course course = new Course();
        course.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        course.setName(c.getString(c.getColumnIndex(KEY_COURSE)));
        course.setSlope(c.getInt(c.getColumnIndex(KEY_SLOPE)));

        c.close();
        db.close();
        return course;
    }

    public Player getPlayer(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_PLAYER,
                PLAYER_COLUMNS,
                "id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {
            Player player = new Player();
            player.setId(c.getInt(c.getColumnIndex(KEY_ID)));
            player.setName(c.getString(c.getColumnIndex(KEY_PLAYER_NAME)));
            player.setHandicap(c.getInt(c.getColumnIndex(KEY_HANDICAP)));

            c.close();
            db.close();
            return player;
        }
        else return null;
    }

    public void addPlayer(Player p)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_PLAYER_NAME, p.getName());
        cv.put(KEY_HANDICAP, p.getHandicap());

        db.insert(TABLE_PLAYER, null, cv);
        db.close();
    }

    public void addCourse(Course c)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_COURSE, c.getName());
        cv.put(KEY_SLOPE, c.getSlope());

        db.insert(TABLE_COURSE, null, cv);
        db.close();
    }

    public void addGame(Game g)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_COURSE_ID, g.getCourse().getId());
        int[] scores = g.getRoundScore();
        String strScores = "";

        // convert scores to comma delineated string
        for (int i = 0; i < scores.length; i++) {
            strScores += scores[i] +
                    (i < scores.length - 1 ? "," : "");
        }
        cv.put(KEY_SCORES, strScores);

        // get array of player ids, convert to string
        String pIds = "";
        Player[] players = g.getPlayers();
        for (int j = 0; j < g.getPlayers().length; j++) {
            pIds += players[j].getId() + (j < g.getPlayers().length - 1 ? "," : "");
        }
        cv.put(KEY_PLAYER_IDS, pIds);
        cv.put(KEY_HOLE, g.getCurrentHole());

        db.insert(TABLE_GAME, null, cv);
        db.close();
    }

    public void replacePlayer(Player p)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_PLAYER_NAME, p.getName());
        cv.put(KEY_HANDICAP, p.getHandicap());
        getWritableDatabase().update(TABLE_PLAYER, cv, KEY_ID + " = " + p.getId(), null);
    }

    public void replaceCourse(Course c)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_COURSE, c.getName());
        cv.put(KEY_SLOPE, c.getSlope());
        getWritableDatabase().update(TABLE_COURSE, cv, KEY_ID + " = " + c.getId(), null);
    }

    public void removePlayer(Player p)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PLAYER, "id = ?", new String[] {String.valueOf(p.getId())});
        db.close();
    }

    public void removeGame(Game g)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GAME, "id = ?", new String[] {String.valueOf(g.getId())});
        db.close();
    }

    public void removeCourse(Course c)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_COURSE, "id = ?", new String[] {String.valueOf(c.getId())});
        db.close();
    }
}
