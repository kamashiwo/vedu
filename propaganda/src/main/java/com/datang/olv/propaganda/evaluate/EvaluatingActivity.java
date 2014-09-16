package com.datang.olv.propaganda.evaluate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.datang.olv.propaganda.OlvApplication;
import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import java.util.logging.LogRecord;

/**
 * Created by xqjiaoshi02 on 14-7-31.
 */
public class EvaluatingActivity extends FragmentActivity implements View.OnClickListener{
    //extends FragmentActivity {

    private SlidingMenu menu;

    private ProgressDialog m_dialog;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Toast.makeText(mctx, "登陆成功", Toast.LENGTH_SHORT).show();
                    m_dialog.cancel();
                    Intent intent=new Intent();
                    intent.setClass(mctx, TeachersActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_SHORT).show();
                    m_dialog.cancel();
                    break;
            }
        }
    };

    private EditText username;
    private EditText password;
    private Button login;
    private Context mctx;
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

        mctx = this;
        m_dialog = new ProgressDialog(EvaluatingActivity.this);
        m_dialog.setTitle("登陆中......");
        m_dialog.setMessage("请稍等......");
        //m_dialog.
        m_dialog.setCanceledOnTouchOutside(false);


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
            m_dialog.show();
            Thread thd = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient client = new DefaultHttpClient();
                    String path ="http://119.254.110.62/auth/local"; //请求地址
                    List parameters = new ArrayList();
                    //请求数据(代替了输出流)
                    parameters.add(new BasicNameValuePair("email","student@student.com"));
                    parameters.add(new BasicNameValuePair("password","student"));
                    HttpPost httppost= new HttpPost(path);
                    try {
                        httppost.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    try {
                        HttpResponse response = client.execute(httppost);
                        int code = response.getStatusLine().getStatusCode();
                        if(code == 200){
                            InputStream is = response.getEntity().getContent();
//                      String result = new String(StreamTools.getBytes(is));
                            String result = inputStream2String(is);
                            //String strrel = EntityUtils.toString(response.getEntity());
                            //String result = new String(StreamTools.getBytes(is));
                            TokenInfo tkinfo = new Gson().fromJson(result, TokenInfo.class);
                            OlvApplication app = (OlvApplication)getApplication();
                            app.setHttpClientToken(tkinfo.token);

                            handler.sendMessage(handler.obtainMessage(1));
                        }
                        else {

                            handler.sendMessage(handler.obtainMessage(2));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thd.start();


        }else if(v.getId()==R.id.login_back){
            finish();
        }
    }

    public class TokenInfo{
        String token;
    }

    private String inputStream2String (InputStream in) throws IOException{
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for(int n; (n = in.read(b))!= -1;){
            out.append(new String(b,0,n));
        }
        return out.toString();
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
