package sailedeer.scorecard.data;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Player {
    private String mName;
    private int mHandicap;

    public Player(String name, int handicap) {
        mHandicap = handicap;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getHandicap() {
        return mHandicap;
    }

    public void setHandicap(int handicap) {
        mHandicap = handicap;
    }
}
