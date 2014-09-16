package com.datang.olv.propaganda.mooc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.datang.olv.propaganda.HttpClient.HttpClientInterface;
import com.datang.olv.propaganda.OlvApplication;
import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.evaluate.TeachersActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by l on 14-9-2.
 */
public class CourseActivity  extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private List<Video> list;
    private ListView listView;//视频课程列表
    private LinearLayout dropdownMenu;//下拉菜单按钮
    private ImageView slideMenu;//滑动菜单按钮
    private ImageView back;//返回按钮
    private BaseAdapter adapter;//课程适配器
    private PopupWindow popupWindow;//标题栏下拉菜单
    private ListView courseClassesListView;//下拉菜单中的listview
    private ArrayAdapter<String> arrayAdapter;//下拉菜单适配器
    private String[] courseList;

    private Context mctx;

    private android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Toast.makeText(mctx, "获取信息成功", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
//                    m_dialog.cancel();
//                    Intent intent=new Intent();
//                    intent.setClass(mctx, TeachersActivity.class);
//                    startActivity(intent);
//                    finish();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
//                    m_dialog.cancel();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mctx = this;
        init();
        setData();
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
        popupWindow=new PopupWindow(layout,300,600);

        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        courseClassesListView=(ListView)layout.findViewById(R.id.course_classes_list);
        courseClassesListView.setOnItemClickListener(this);

        OlvApplication olvapp = (OlvApplication)getApplication();
        String strtoken = olvapp.getHttpClientToken();

        HttpClientInterface.GetCoursesInfo(strtoken, new Callback<List<HttpClientInterface.Courses>>() {
            @Override
            public void success(List<HttpClientInterface.Courses> courseses, Response response) {
                handler.sendMessage(handler.obtainMessage(1));

                for(HttpClientInterface.Courses df:courseses){
                    Video v=new Video();
                    v.setId(df.get_id());
                    v.setName(df.getName());
                    v.setClassify(df.getInfo());
                    list.add(v);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                handler.sendMessage(handler.obtainMessage(2));
            }
        });
    }
    /**
     * 添加测试数据
     */
    private void setData(){
        list=new ArrayList<Video>();
//        for(int i=0;i<30;i++){
//            Video v=new Video();
//            v.setId(i);
//            v.setName("课程：课程"+i);
//            v.setClassify("教育 综合");
//            v.setPlayCount(10000+i);
//            list.add(v);
//        }
        adapter=new CourseListAdapter(list,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        courseList=new String[10];
        for(int j=0;j<10;j++){
            courseList[j]="分类"+j;
        }
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseList);
        courseClassesListView.setAdapter(arrayAdapter);
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
            case R.id.course_classes:
                popupWindow.showAsDropDown(dropdownMenu, -50, 0);
                break;
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
        }else if(parent.getAdapter().equals(this.arrayAdapter)){
            popupWindow.dismiss();
        }
    }


}