package sailedeer.scorecard.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game {

    private ArrayList<Player> mPlayers;
    private ArrayList<Game> mSubGames;
    private Course mCourse;
    private GameType mGameType;
    private int[] mRoundScore;
    private int mCurrentHole;
    private String mName;

    public Game(Course course, GameType gameType, ArrayList<Player> players) {
        mCourse = course;
        mGameType = gameType;
        mPlayers = players;
        mName = "Game";
        mCurrentHole = 0;

        mSubGames = new ArrayList<>();
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

    public String getGameName()
    {
     return mName;
    }
}
