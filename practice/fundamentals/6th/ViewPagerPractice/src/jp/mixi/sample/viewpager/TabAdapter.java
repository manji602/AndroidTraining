package jp.mixi.sample.viewpager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

public class TabAdapter extends FragmentPagerAdapter{
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final SparseArray<TabInfo> mTabs = new SparseArray<TabInfo>();

    static final class TabInfo {
        public final String tag;
        public final Class<?> clss;
        public final Bundle args;
        public Fragment fragment;

        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    };

    public TabAdapter(SherlockFragmentActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());

        mActionBar = activity.getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = pager;
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
    };

    public void addTab(String tag, Class<?> clazz, Bundle args) {
        ActionBar.Tab tab = this.mActionBar.newTab();
        tab.setText(tag);
        tab.setTabListener(mTabListener);
        this.mActionBar.addTab(tab);

        TabInfo info = new TabInfo(tag, clazz, args);
        mTabs.put(tab.getPosition(), info);
    };
    
    @Override
    public Fragment getItem(int position) {
        TabInfo info = mTabs.get(position);
        if (info != null) {
            if (info.fragment == null) {
                info.fragment = SampleFragment.newInstance(position);
            }
            return info.fragment;
        } else {
            return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return this.mTabs.size();
    }

    private final ActionBar.TabListener mTabListener = new ActionBar.TabListener() {
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
    };

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            mActionBar.selectTab(mActionBar.getTabAt(position));
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
