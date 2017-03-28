package sailedeer.scorecard.activities.fragments.handling;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import sailedeer.scorecard.activities.fragments.CourseTabFragment;
import sailedeer.scorecard.activities.fragments.GameTabFragment;
import sailedeer.scorecard.activities.fragments.PlayerTabFragment;

/**
 * Created by Eli on 3/7/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    PlayerTabFragment tab1;
    GameTabFragment tab2;
    CourseTabFragment tab3;
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
                tab1 = new PlayerTabFragment();
                return tab1;
            case 1:
                tab2 = new GameTabFragment();
                return tab2;
            case 2:
                tab3 = new CourseTabFragment();
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
