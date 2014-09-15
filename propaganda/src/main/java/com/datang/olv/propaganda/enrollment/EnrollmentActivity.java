package com.datang.olv.propaganda.enrollment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.overview.CollegeFragment;
import com.datang.olv.propaganda.overview.HistoryFragment;
import com.datang.olv.propaganda.overview.LeaderFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-7-21.
 */
public class EnrollmentActivity extends FragmentActivity {
    SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnrollmentActivity.this.finish();

            }
        });

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


        final FragmentPagerAdapter adapter = new EnrollmentAdapter(this.getSupportFragmentManager());

        final ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);

        TabPageIndicator indicator = (TabPageIndicator) this.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    class EnrollmentAdapter extends FragmentPagerAdapter {
        private String[] CONTENT;
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public EnrollmentAdapter(FragmentManager fm) {
            super(fm);
            CONTENT = getResources().getStringArray(R.array.enrollment_titile);
            AdmissionFragment admissionFragment = new AdmissionFragment();
            EmploymentFragment employmentFragment = new EmploymentFragment();
            fragmentList.add(admissionFragment);
            fragmentList.add(employmentFragment);

        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position % CONTENT.length);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position % CONTENT.length);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }


}
