package sailedeer.scorecard.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game implements Serializable {

    Press mFrontPress;
    Press mBackPress;
    private Player[] mPlayers;
    private Course mCourse;
    private int[][] mPersonalScores;
    private int[] mRoundScore;
    private int mCurrentHole;
    private int mId;

    public Game(){}

    public Game(Course course, Player[] players) {
        mCourse = course;
        mPlayers = players;
        mCurrentHole = 1;
        mRoundScore = new int[18];
        mPersonalScores = new int[4][18];
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

    public void setFrontPress(Press p) { mFrontPress = p; }

    public void setBackPress(Press p) {mBackPress = p;}

    public Player[] getPlayers() {
        return mPlayers;
    }

    public Course getCourse() {
        return mCourse;
    }

    public int[] getRoundScore() {
        return mRoundScore;
    }

    public int[][] getPersonalScores() { return mPersonalScores; }

    public void setPersonalScores(int[][] scores) { mPersonalScores = scores; }

    public int getCurrentHole() {
        return mCurrentHole;
    }

    public int getId() {
        return mId;
    }

    public Press getFrontPress()
    {
        return mFrontPress;
    }

    public Press getBackPress(){
        return mBackPress;
    }

    public void calculateHoleScore()
    {

    }
}
