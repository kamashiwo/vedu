package com.datang.olv.propaganda.news;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.majors.MajorsDetailActivity;
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
public class NewsActivity extends FragmentActivity {
    SlidingMenu menu;

    private ListView mListView;

    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<NewsItem> mList;
    private Context mctx;
    private NewListAdapter mAdapter;

    private static String newsoneZhu  = "我院召开二届二次教代会，一届二次工代会";
    private static String newsoneFu  = "7月7日下午13:00,我院在嘉定校区报告厅召开二届二次教代会，一届二次工代会。出席会议的学院领导有...";

    private static String newstwoZhu  = "我院参加2014非营利性民办高等学校联盟信息联络员会议并作交流发言";
    private static String newstwoFu  = "为更好地推进非营利性民办高等学校联盟单位间交流互助和协同创新关系，进一步加强各成员单位与秘书处的工作......";

    private static String newsthreeZhu  = "我院召开2014年度师资队伍建设工作会议";
    private static String newsthreeFu  = "7月4日，我院2014年度师资队伍建设工作会议在嘉定校区大多媒体召开......";

    private static String newsfourZhu  = "学院召开汽车、机电理实一体综合实训中心第二轮功能设计讨论会议";
    private static String newsfourFu  = "2014年6月30日，学院召开汽车、机电理实一体综合实训中心第二轮功能设计讨论会议.......";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsActivity.this.finish();

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

        // 获取Resources对象
        Resources res = this.getResources();

        mListView = (ListView)findViewById(R.id.news_list);

        mctx = this;

        mList = new ArrayList<NewsItem>();

        NewsItem item = new NewsItem();
        item.setZhuText(newsoneZhu);
        item.setImage(res.getDrawable(R.drawable.news1));
        item.setFuText(newsoneFu);
        mList.add(item);

        item = new NewsItem();
        item.setZhuText(newstwoZhu);
        item.setImage(res.getDrawable(R.drawable.news2));
        item.setFuText(newstwoFu);
        mList.add(item);

        item = new NewsItem();
        item.setZhuText(newsthreeZhu);
        item.setImage(res.getDrawable(R.drawable.news3));
        item.setFuText(newsthreeFu);
        mList.add(item);

        item = new NewsItem();
        item.setZhuText(newsfourZhu);
        item.setImage(res.getDrawable(R.drawable.news4));
        item.setFuText(newsfourFu);
        mList.add(item);

        mAdapter = new NewListAdapter(mList, mctx, this);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //MajorsDetailItem detailinfo = mDetailItem.get(i);
                Intent intent = new Intent(mctx,
                        NewsDetailActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",i);

                intent.putExtra("DATA",value);

                mctx.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    public class NewsItem {
        private Drawable image;
        private String ZhuText;
        private String FuText;
        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }
        public String getZhuText() {
            return ZhuText;
        }
        public void setZhuText(String zhuText) {
            ZhuText = zhuText;
        }
        public String getFuText() {
            return FuText;
        }
        public void setFuText(String fuText) {
            FuText = fuText;
        }
    }
}
