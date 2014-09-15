package com.datang.olv.propaganda.mooc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qgf on 14-9-4.
 */
public class FavoriteActivity  extends FragmentActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    SlidingMenu menuMain;
    SlidingMenu menuMooc;

    private List<Video> list;
    private ListView listView;//视频课程列表
    private LinearLayout dropdownMenu;//下拉菜单按钮
    private ImageView slideMenu;//滑动菜单按钮
    private ImageView back;//返回按钮
    private BaseAdapter adapter;//课程适配器
//    private PopupWindow popupWindow;//标题栏下拉菜单
//    private ListView courseClassesListView;//下拉菜单中的listview
//    private ArrayAdapter<String> arrayAdapter;//下拉菜单适配器
    private String[] courseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mooc_favorite);
        init();
        setData();

        // configure the Main SlidingMenu
        menuMain = new SlidingMenu(this);
        menuMain.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menuMain.setShadowWidthRes(R.dimen.shadow_width);
        menuMain.setShadowDrawable(R.drawable.shadow);
        menuMain.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menuMain.setFadeDegree(.035f);
        menuMain.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menuMain.setMenu(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MenuListFragment()).commit();

        // configure the Mooc SlidingMenu
        menuMooc = new SlidingMenu(this);
        menuMooc.setMode(SlidingMenu.RIGHT);
        menuMooc.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menuMooc.setShadowWidthRes(R.dimen.shadow_width);
        menuMooc.setShadowDrawable(R.drawable.shadow);
        menuMooc.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menuMooc.setFadeDegree(.035f);
        menuMooc.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menuMooc.setMenu(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MoocMenuListFragment()).commit();
    }
    private void init(){
        listView=(ListView)findViewById(R.id.course_booklist);
        dropdownMenu=(LinearLayout)findViewById(R.id.course_classes);
        dropdownMenu.setOnClickListener(this);
        slideMenu=(ImageView)findViewById(R.id.course_slidemenu);
        slideMenu.setOnClickListener(this);
        back=(ImageView)findViewById(R.id.course_back);
        back.setOnClickListener(this);
        View layout= LayoutInflater.from(this).inflate(R.layout.course_classses_layout, null);
//        popupWindow=new PopupWindow(layout,300,600);
//
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        courseClassesListView=(ListView)layout.findViewById(R.id.course_classes_list);
//        courseClassesListView.setOnItemClickListener(this);

    }
    /**
     * 添加测试数据
     */
    private void setData(){
        list=new ArrayList<Video>();
        for(int i=0;i<30;i++){
            Video v=new Video();
            v.setId(i);
            v.setName("课程：课程"+i);
            v.setClassify("教育 综合");
            v.setPlayCount(10000+i);
            list.add(v);
        }
        adapter=new CourseListAdapter(list,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        courseList=new String[10];
        for(int j=0;j<10;j++){
            courseList[j]="分类"+j;
        }
//        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseList);
//        courseClassesListView.setAdapter(arrayAdapter);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.course_back:
                finish();
                break;
            case R.id.course_slidemenu:

                break;
//            case R.id.course_classes:
//                popupWindow.showAsDropDown(dropdownMenu, -50, 0);
//                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        if(parent.getAdapter().equals(this.adapter)){
            Intent intent=new Intent();
            intent.setClass(this, VideoPlayerActivity.class);
            startActivity(intent);
        }
//        else if(parent.getAdapter().equals(this.arrayAdapter)){
//            popupWindow.dismiss();
//        }
    }

    @Override
    public void onBackPressed() {
        if (menuMain.isMenuShowing()) {
            menuMain.showContent();
        } else {
            super.onBackPressed();
        }
    }

}