package sailedeer.scorecard.activities.fragments.handling;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sailedeer.scorecard.activities.fragments.CourseTabFragment;
import sailedeer.scorecard.activities.fragments.GameTabFragment;
import sailedeer.scorecard.activities.fragments.PlayerTabFragment;

/**
 * Created by Eli on 3/7/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs)
    {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                PlayerTabFragment tab1 = new PlayerTabFragment();
                return tab1;
            case 1:
                GameTabFragment tab2 = new GameTabFragment();
                return tab2;
            case 2:
                CourseTabFragment tab3 = new CourseTabFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
