package com.datang.olv.propaganda.scence;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;

/**
 * Created by liqing3 on 14-8-4.
 */
public class ScenceDetailTemplateActivity extends Activity {
    ImageView imageView;
    ImageButton panoButton;
    final ArrayList<Integer> mPanoPicID = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sencedetailtemplet);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScenceDetailTemplateActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle value = intent.getBundleExtra("DATA");

        int ImageID = value.getInt("ID");

        ArrayList<Integer> panoPicID = new ArrayList<Integer>();
        panoPicID = value.getIntegerArrayList("pano");

        imageView = (ImageView)this.findViewById(R.id.scence_detail_image);
        panoButton = (ImageButton)this.findViewById(R.id.scence_pano_image_Button);

        if(panoPicID.size()>0){
            panoButton.setClickable(true);
            panoButton.setVisibility(View.VISIBLE);
        }
        else{
            panoButton.setVisibility(View.INVISIBLE);
            panoButton.setClickable(false);
        }

        Resources res = this.getResources();

        imageView.setImageResource(ImageID);
        //res.getDrawable(strImageID);
        //textView.setText(TextDetail);
        final ArrayList<Integer> finalPanoPicID = panoPicID;
        panoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceDetailTemplateActivity.this,
                        ScencePanoramaActivity.class);

                Bundle value = new Bundle();
                value.putIntegerArrayList("ID", finalPanoPicID);
                intent.putExtra("DATA",value);

                ScenceDetailTemplateActivity.this.startActivity(intent);
            }
        });
    }
}
