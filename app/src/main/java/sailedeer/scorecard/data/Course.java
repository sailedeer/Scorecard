package sailedeer.scorecard.data;

import java.io.Serializable;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Course implements Serializable{
    private String mName;
    private int mSlope;

    public Course(String name, int slope)
    {
        mName = name;
        mSlope = slope;
    }

    public String getName()
    {
        return mName;
    }

    public int getSlope()
    {
        return mSlope;
    }
}
