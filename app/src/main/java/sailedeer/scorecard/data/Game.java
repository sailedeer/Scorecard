package sailedeer.scorecard.data;

import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game implements Serializable{

    private Player[] mPlayers;
    private Course mCourse;
    private int[] mRoundScore;
    private int mCurrentHole;
    private int mId;

    public Game(){}

    public Game(Course course, Player[] players) {
        mCourse = course;
        mPlayers = players;
        mCurrentHole = 0;
        mRoundScore = new int[18];
    }

    public void setPlayers(Player[] mPlayers) {
        this.mPlayers = mPlayers;
    }

    public void setCourse(Course mCourse) {
        this.mCourse = mCourse;
    }

    public void setRoundScore(int[] mRoundScore) {
        this.mRoundScore = mRoundScore;
    }

    public void setCurrentHole(int mCurrentHole) {
        this.mCurrentHole = mCurrentHole;
    }

    public void setID(int mId) {
        this.mId = mId;
    }

    public Player[] getPlayers() {
        return mPlayers;
    }

    public Course getCourse() {
        return mCourse;
    }

    public int[] getRoundScore() {
        return mRoundScore;
    }

    public int getCurrentHole() {
        return mCurrentHole;
    }

    public int getID() {
        return mId;
    }
}
