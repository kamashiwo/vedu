package com.datang.olv.propaganda.institution;

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
 * Created by liqing3 on 14-7-30.
 */
public class InstitutionDetailActivity extends Activity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.institution_detail_layout);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstitutionDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle value = intent.getBundleExtra("DATA");

        String TextDetail = value.getString("text");

        textView = (TextView)this.findViewById(R.id.institution_detail_text);

        Resources res = this.getResources();

        //res.getDrawable(strImageID);
        textView.setText(TextDetail);
    }
}

