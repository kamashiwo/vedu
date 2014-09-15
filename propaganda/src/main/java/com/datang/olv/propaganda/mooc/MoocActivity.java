package com.datang.olv.propaganda.mooc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import com.datang.olv.propaganda.R;

/**
* Created by xqjiaoshi02 on 14-7-31.
*/
public class MoocActivity extends FragmentActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mooc_logo);
        new Handler().postDelayed(this, 2000);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        startActivity(new Intent(this,CourseActivity.class));
        finish();
    }
}

//import android.app.Fragment;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.ViewGroup;
//
//import com.datang.olv.propaganda.R;
//import com.datang.olv.propaganda.evaluate.EvaluatingFragment;
//import com.datang.olv.propaganda.main.MenuListFragment;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
//import com.viewpagerindicator.TabPageIndicator;
//
//import java.util.ArrayList;
//import java.util.List;
//public class MoocActivity extends FragmentActivity{
//
//    private SlidingMenu menu;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        final FragmentStatePagerAdapter adapter = new TestAdapter(this.getSupportFragmentManager());
//
//        final ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
//        pager.setAdapter(adapter);
//        pager.setCurrentItem(0, false);
//
//        // configure the SlidingMenu
//        menu = new SlidingMenu(this);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        menu.setFadeDegree(0.35f);
//        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        menu.setMenu(R.layout.menu_frame);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.menu_frame, new MenuListFragment())
//                .commit();
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (menu.isMenuShowing()) {
//            menu.showContent();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    class TestAdapter extends FragmentStatePagerAdapter {
//        //        private String[] CONTENT;
//        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();
//
//        public TestAdapter(FragmentManager fm) {
//            super(fm);
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc0));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc1));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc2));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc11));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc12));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc12bofang));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc12zhifu));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc12zhifuchenggong));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc13));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc13xiangqing));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc3shoucang));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc3shoucanghuakai));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc13xiangqing));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc4zuoye));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc4zuoye1));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc4zuoye2));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc4zuoyefuxi));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc5huancun));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc5huancunshezhi));
//            fragmentList.add(MoocFragment.newInstance(R.drawable.mooc6bofanglishi));
//        }
//
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position % fragmentList.size());
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position % fragmentList.size(), object);
//        }
//
//        @Override
//        public android.support.v4.app.Fragment getItem(int position) {
//            return fragmentList.get(position % fragmentList.size());
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
////            return CONTENT[position % fragmentList.size()].toUpperCase();
//            return "";
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//    }
//}
