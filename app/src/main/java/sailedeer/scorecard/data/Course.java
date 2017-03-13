package sailedeer.scorecard.data;

/**
 * Created by sailedeer7 on 2/13/2017.
 */

public class Course {
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
