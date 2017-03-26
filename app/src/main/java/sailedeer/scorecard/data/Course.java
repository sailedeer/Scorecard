package sailedeer.scorecard.data;


import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Course implements Serializable {
    private String mName;
    private int mSlope;
    private int mId;

    public Course() {}

    public Course(String name, int slope)
    {
        mName = name;
        mSlope = slope;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setSlope(int mSlope) {
        this.mSlope = mSlope;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName()
    {
        return mName;
    }

    public int getSlope()
    {
        return mSlope;
    }

    public int getID() { return mId; }
}
