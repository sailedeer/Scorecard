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
    private String mInitials;
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

    public String getInitials() { return generateInitials(); }

    private String generateInitials()
    {
        String initials = "";
        String first;
        String last;
        mName.trim();

        first = mName.substring(0, mName.indexOf(' ')).substring(0, 1);
        last = mName.substring(mName.indexOf(' ') + 1).substring(0, 1);
        initials += first + last;

        return initials;
    }
}
