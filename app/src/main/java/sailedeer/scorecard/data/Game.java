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
    private Press backPress;
    private Press frontPress;
    private Press gamePress;
    private Course mCourse;
    private GameType mGameType;
    private int[] mRoundScore;
    private int mCurrentHole;
    private int mID;

    public Game(){}

    public Game(Course course, GameType gameType, ArrayList<Player> players) {
        mCourse = course;
        mGameType = gameType;
        mPlayers = players;
        mCurrentHole = 0;

        //mSubGames = new ArrayList<>();
        mRoundScore = new int[18];
    }

    public static int calculateRoundScore(int round, int[] scores, GameType type)
    {
        //no idea what to do with this
        return  69;
    }

    public void setmPlayers(ArrayList<Player> mPlayers) {
        this.mPlayers = mPlayers;
    }

    public void setmCourse(Course mCourse) {
        this.mCourse = mCourse;
    }

    public void setmGameType(GameType mGameType) {
        this.mGameType = mGameType;
    }

    public void setmRoundScore(int[] mRoundScore) {
        this.mRoundScore = mRoundScore;
    }

    public void setmCurrentHole(int mCurrentHole) {
        this.mCurrentHole = mCurrentHole;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getCourseName()
    {
        return mCourse.getName();
    }

    public int getCurrentHole()
    {
        return mCurrentHole;
    }

    public int getGameID()
    {
     return mID;
    }
}
