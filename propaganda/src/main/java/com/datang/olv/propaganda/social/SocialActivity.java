package com.datang.olv.propaganda.social;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.evaluate.EvaluatingFragment;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqjiaoshi02 on 14-7-31.
 */
public class SocialActivity extends FragmentActivity{

    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TestActivity.this.finish();
//
//            }
//        });

            final FragmentStatePagerAdapter adapter = new TestAdapter(this.getSupportFragmentManager());

        final ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0, false);

        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new MenuListFragment())
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    class TestAdapter extends FragmentStatePagerAdapter {
        //        private String[] CONTENT;
        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();

        public TestAdapter(FragmentManager fm) {
            super(fm);
//            CONTENT = getResources().getStringArray(R.array.overview_titile);
//            CollegeFragment collegeFragment = new CollegeFragment();
//            LeaderFragment leaderFragment = new LeaderFragment();
//            HistoryFragment historyFragment = new HistoryFragment();
//            fragmentList.add(collegeFragment);
//            fragmentList.add(leaderFragment);
//            fragmentList.add(historyFragment);
            fragmentList.add(SocialFragment.newInstance(R.drawable.social1));
            fragmentList.add(SocialFragment.newInstance(R.drawable.social11));
            fragmentList.add(SocialFragment.newInstance(R.drawable.social3));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position % fragmentList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position % fragmentList.size(), object);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position % fragmentList.size());
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return CONTENT[position % fragmentList.size()].toUpperCase();
            return "";
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
