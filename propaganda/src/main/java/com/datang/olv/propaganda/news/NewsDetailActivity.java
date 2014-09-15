package com.datang.olv.propaganda.news;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

/**
 * Created by liqing3 on 14-8-11.
 */
public class NewsDetailActivity extends Activity {
    private ImageView imageView;
    private TextView textView;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_detail_layout);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle value = intent.getBundleExtra("DATA");

        int newsID = value.getInt("ID");
        //webview = new WebView(this);
        webview = (WebView)this.findViewById(R.id.news_webView);
        Resources res = this.getResources();
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);

        webview.getSettings().setBuiltInZoomControls(true); // 显示放大缩小 controler
        webview.getSettings().setSupportZoom(true); // 可以缩放
        webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);// 默认缩放模式

        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        switch(newsID)
        {
            case 0:
                webview.loadUrl("file:///android_asset/news1.html");
                break;
            case 1:
                webview.loadUrl("file:///android_asset/news2.html");
                break;
            case 2:
                webview.loadUrl("file:///android_asset/news3.html");
                break;
            case 3:
                webview.loadUrl("file:///android_asset/news4.html");
                break;
        }

    }
}
