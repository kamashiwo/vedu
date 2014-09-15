package com.datang.olv.propaganda.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.test.TestFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqjiaoshi02 on 14-7-31.
 */
public class ScheduleActivity  extends FragmentActivity{

    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_schedule);
//              this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
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
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public TestAdapter(FragmentManager fm) {
            super(fm);
//            CONTENT = getResources().getStringArray(R.array.overview_titile);
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule1));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule11));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule12));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule2));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule21));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule23));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule24));
            fragmentList.add(TestFragment.newInstance(R.drawable.schedule25));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position % fragmentList.size());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position % fragmentList.size());
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position % fragmentList.size(), object);
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
