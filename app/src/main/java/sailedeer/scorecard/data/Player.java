package sailedeer.scorecard.data;

import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Player {
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

    public String geName() {
        return mName;
    }
}
