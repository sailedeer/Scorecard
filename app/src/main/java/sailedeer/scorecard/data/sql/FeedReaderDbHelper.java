package sailedeer.scorecard.data.sql;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Player;

/**
 * Created by Eli on 3/13/2017.
 */

public class FeedReaderDbHelper extends SQLiteOpenHelper {

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
    public static final String PLAYER_COLUMN = "name";
    public static final String HANDICAP_COLUMN = "handicap";

    // Game Column Names
    public static final String ROUND_SCORES_COLUMN = "scores";
    public static final String COURSE_ID_COLUMN = "course_id";
    public static final String PLAYER_ID_COLUMN = "player_ids";
    public static final String GAME_UUID_COLUMN = "uuid";
    public static final String GAME_TYPE_COLUMN = "game_type";
    public static final String HOLE_COLUMN = "hole";

    // Course Column Names
    public static final String COURSE_COLUMN = "name";
    public static final String SLOPE_COLUMN = "slope";



    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE " +
            PLAYER_COLUMN + "(" + KEY_ID + " INTEGER PRIMARY KEY " +
            PLAYER_COLUMN + " TEXT, " + HANDICAP_COLUMN + "INTEGER" +
            ")";

    private static final String CREATE_TABLE_GAME = "CREATE TABLE " +
            TABLE_GAME + "(" + KEY_ID + "INTEGER PRIMARY KEY," +
            GAME_UUID_COLUMN + "TEXT," + COURSE_ID_COLUMN +
            "INTEGER," + PLAYER_ID_COLUMN + "TEXT," + GAME_TYPE_COLUMN +
            "TEXT," + HOLE_COLUMN + "INTEGER," + ROUND_SCORES_COLUMN + "TEXT" + ")";

    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " +
            TABLE_COURSE + "(" + KEY_ID + "INTEGER PRIMARY KEY," +
            COURSE_COLUMN + "TEXT," + SLOPE_COLUMN + "INTEGER" + ")";

    public FeedReaderDbHelper(Context context)
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
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
