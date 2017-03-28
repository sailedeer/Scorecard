package sailedeer.scorecard.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Player implements /*Parcelable, */Serializable  {
    private int mId;
    private String mName;
    private int mHandicap;

    public Player() {}

    public Player(String name, int handicap) {
        mHandicap = handicap;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getHandicap() {
        return mHandicap;
    }

    public void setHandicap(int mHandicap) {
        this.mHandicap = mHandicap;
    }

//    protected Player(Parcel in)
//    {
//        mId = in.readInt();
//        mHandicap = in.readInt();
//        mName = in.readString();
//    }
//
//    @Override
//    public int describeContents()
//    {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags)
//    {
//        dest.writeInt(mId);
//        dest.writeInt(mHandicap);
//        dest.writeString(mName);
//    }
//
//    @SuppressWarnings("unused")
//    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
//    {
//        @Override
//        public Player createFromParcel(Parcel in)
//        {
//            return new Player(in);
//        }
//
//        @Override
//        public Player[] newArray(int size)
//        {
//            return new Player[size];
//        }
//    };
}
