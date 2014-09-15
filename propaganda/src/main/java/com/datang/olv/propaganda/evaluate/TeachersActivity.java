package com.datang.olv.propaganda.evaluate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-9-2.
 */
public class TeachersActivity extends Activity implements View.OnClickListener{
    private ListView listView;
    private List<Teacher> teachers=new ArrayList<Teacher>();
    private TeacherListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        init();
        setData();
        alertDialog();
    }
    private void init(){
        listView=(ListView)findViewById(R.id.teachers_list);
        findViewById(R.id.teachers_back).setOnClickListener(this);
    }
    private void setData(){
        Teacher t1=new Teacher();
        t1.setId(1);
        t1.setName("钱宏");
        t1.setCourse("基于Java的Web应用开发");
        t1.setFace(R.drawable.te1);
        teachers.add(t1);

        Teacher t2=new Teacher();
        t2.setId(2);
        t2.setName("安子良");
        t2.setCourse("机电专业英语");
        t2.setFace(R.drawable.te2);
        teachers.add(t2);

        Teacher t3=new Teacher();
        t3.setId(3);
        t3.setName("夏月琴");
        t3.setCourse("汽车构造");
        t3.setFace(R.drawable.te3);
        teachers.add(t3);

        Teacher t4=new Teacher();
        t4.setId(4);
        t4.setName("阎庆华");
        t4.setCourse("液压与汽动技术");
        t4.setFace(R.drawable.te4);
        teachers.add(t4);

        for(int id=5;id<20;id++){
            Teacher t=new Teacher();
            t.setId(id);
            t.setName("XX"+id);
            t.setCourse("XXXX"+id);
            t.setFace(R.drawable.te1);
            teachers.add(t);
        }
        adapter=new TeacherListAdapter(teachers, this);
        listView.setAdapter(adapter);
    }
    private void alertDialog(){
        DisplayMetrics metrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final Dialog dialog=new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.setCancelable(false);
        Window window=dialog.getWindow();
        WindowManager.LayoutParams p=window.getAttributes();
        p.width=(int) (metrics.widthPixels*0.9);
        window.setAttributes(p);
        window.setContentView(R.layout.remark_dialog_layout);
        Button btnOK=(Button) window.findViewById(R.id.dialog_ok);
        Button btnClose=(Button)window.findViewById(R.id.dialog_close);
        TextView dollar=(TextView)window.findViewById(R.id.usersdollars);
        dollar.setText("50");
        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.getId()==R.id.teachers_back){
            finish();
        }
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100&&resultCode==Activity.RESULT_OK){
            int tid=data.getIntExtra("tid", -1);
            for(Teacher t:this.teachers){
                if(t.getId()==tid){
                    t.setHasRemarked(true);
                    break;
                }
            }
            this.adapter.notifyDataSetChanged();
        }
    }


}