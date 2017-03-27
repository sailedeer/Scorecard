package sailedeer.scorecard.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Game implements Parcelable {

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

    protected Game(Parcel in) {
        mCourse = (Course) in.readValue(Course.class.getClassLoader());
        mCurrentHole = in.readInt();
        mId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mCourse);
        dest.writeInt(mCurrentHole);
        dest.writeInt(mId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
