package com.datang.olv.propaganda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.datang.olv.propaganda.HttpClient.HttpClientInterface;
import com.datang.olv.propaganda.main.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the Above View
        setContentView(R.layout.activity_welcome);
//        Picasso.with(this).load("http://172.30.4.58:8080/image/home_bg").into((android.widget.ImageView) this.findViewById(R.id.welcome_bg));
        HttpClientInterface.GetSchoolInfo(new Callback<HttpClientInterface.Schoolinfo>() {
            @Override
            public void success(HttpClientInterface.Schoolinfo schoolinfo, Response response) {
                Toast.makeText(getApplicationContext(), "获取信息成功", Toast.LENGTH_SHORT).show();

                OlvApplication app = (OlvApplication) getApplication();
                app.setGschoolinfo(schoolinfo);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "获取信息失败，请检查网络后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 2300);


    }

}
