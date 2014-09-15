package com.datang.olv.propaganda.overview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

/**
 * Created by liqing3 on 14-7-29.
 */
public class LeaderDetailActivity extends Activity {
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.leader_detail_layout);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeaderDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle value = intent.getBundleExtra("DATA");

        int ImageID = value.getInt("ID");
        String TextDetail = value.getString("text");



        imageView = (ImageView)this.findViewById(R.id.leader_detail_image);
        textView = (TextView)this.findViewById(R.id.leader_detail_text);

        Resources res = this.getResources();

        imageView.setImageResource(ImageID);
        //res.getDrawable(strImageID);
        textView.setText(TextDetail);
    }
}
