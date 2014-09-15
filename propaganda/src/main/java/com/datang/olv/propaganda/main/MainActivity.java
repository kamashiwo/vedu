package com.datang.olv.propaganda.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.enrollment.EnrollmentActivity;
import com.datang.olv.propaganda.evaluate.EvaluatingActivity;
import com.datang.olv.propaganda.institution.InstitutionActivity;
import com.datang.olv.propaganda.majors.MajorsActivity;
import com.datang.olv.propaganda.map.MapActivity;
import com.datang.olv.propaganda.mooc.MoocActivity;
import com.datang.olv.propaganda.news.NewsActivity;
import com.datang.olv.propaganda.overview.OverviewActivity;
import com.datang.olv.propaganda.scence.ScenceActivity;
import com.datang.olv.propaganda.schedule.ScheduleActivity;
import com.datang.olv.propaganda.social.SocialActivity;
import com.datang.olv.propaganda.test.TestActivity;

/**
 * Created by l on 14-7-21.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the Above View
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.iv_overview).setOnClickListener  (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OverviewActivity.class));
            }
        });
        this.findViewById(R.id.iv_scence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScenceActivity.class));
            }
        });
        this.findViewById(R.id.iv_majors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MajorsActivity.class));
            }
        });
        this.findViewById(R.id.iv_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
        this.findViewById(R.id.iv_enrollment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EnrollmentActivity.class));
            }
        });

        this.findViewById(R.id.iv_institution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InstitutionActivity.class));
            }
        });
        this.findViewById(R.id.iv_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
        this.findViewById(R.id.iv_social).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SocialActivity.class));
            }
        });
        this.findViewById(R.id.iv_mooc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MoocActivity.class));
            }
        });
        this.findViewById(R.id.iv_evaluating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EvaluatingActivity.class));
            }
        });
        this.findViewById(R.id.iv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
        this.findViewById(R.id.iv_schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
            }
        });


    }


}
