package sailedeer.scorecard.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game implements Serializable{

    private ArrayList<Player> mPlayers;
    //private ArrayList<Game> mSubGames;
    private Course mCourse;
    private GameType mGameType;
    private int[] mRoundScore;
    private int mCurrentHole;
    private UUID mID;

    public Game(Course course, GameType gameType, ArrayList<Player> players) {
        mCourse = course;
        mGameType = gameType;
        mPlayers = players;
        mID = UUID.randomUUID();
        mCurrentHole = 0;

        //mSubGames = new ArrayList<>();
        mRoundScore = new int[18];
    }

    public static int calculateRoundScore(int round, int[] scores, GameType type)
    {
        //no idea what to do with this
        return  69;
    }

    public String getCourseName()
    {
        return mCourse.getName();
    }

    public int getCurrentHole()
    {
        return mCurrentHole;
    }

    public String getGameID()
    {
     return mID.toString();
    }
}
