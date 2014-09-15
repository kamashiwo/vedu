package com.datang.olv.propaganda.evaluate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqjiaoshi02 on 14-7-31.
 */
public class EvaluatingActivity extends FragmentActivity implements View.OnClickListener{
        //extends FragmentActivity {

    private SlidingMenu menu;

    private EditText username;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluating);
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity((android.app.Activity)this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new MenuListFragment())
                .commit();

        init();
    }

    private void init(){
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login_bt);
        login.setOnClickListener(this);
//        this.findViewById(R.id.login_back).setOnClickListener((View)->{
//            EvaluatingActivity.this.finish();
//        });
//        }
        findViewById(R.id.login_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.equals(login)){
            Intent intent=new Intent();
            intent.setClass(this, TeachersActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId()==R.id.login_back){
            finish();
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
////        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                TestActivity.this.finish();
////
////            }
////        });
////
////        final FragmentPagerAdapter adapter = new TestAdapter(this.getSupportFragmentManager());
////
////        final ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
////        pager.setAdapter(adapter);
////        pager.setCurrentItem(0, false);
//
//        // configure the SlidingMenu
//
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

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

//    class TestAdapter extends FragmentPagerAdapter {
//        //        private String[] CONTENT;
//        List<Fragment> fragmentList = new ArrayList<Fragment>();
//
//        public TestAdapter(FragmentManager fm) {
//            super(fm);
////            CONTENT = getResources().getStringArray(R.array.overview_titile);
////            CollegeFragment collegeFragment = new CollegeFragment();
////            LeaderFragment leaderFragment = new LeaderFragment();
////            HistoryFragment historyFragment = new HistoryFragment();
////            fragmentList.add(collegeFragment);
////            fragmentList.add(leaderFragment);
////            fragmentList.add(historyFragment);
//            fragmentList.add(EvaluatingFragment.newInstance(R.drawable.evaluating1));
//            fragmentList.add(EvaluatingFragment.newInstance(R.drawable.evaluating11));
//            fragmentList.add(EvaluatingFragment.newInstance(R.drawable.evaluating2));
//            fragmentList.add(EvaluatingFragment.newInstance(R.drawable.evaluating3));
//            fragmentList.add(EvaluatingFragment.newInstance(R.drawable.evaluating4));
//        }


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
//        public Fragment getItem(int position) {
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
}
