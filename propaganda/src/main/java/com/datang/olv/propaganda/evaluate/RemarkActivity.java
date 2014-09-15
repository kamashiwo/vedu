package com.datang.olv.propaganda.evaluate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-9-2.
 */
public class RemarkActivity extends Activity implements View.OnClickListener {
    private ImageView teacherImage;
    private TextView teacherName;
    private TextView teacherCourse;
    private Teacher teacher;
    private List<Remark> list=new ArrayList<Remark>();
    private RemarkListAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        init();
        setData();
    }
    private void init(){
        teacherImage=(ImageView)findViewById(R.id.teacher_image);
        teacherName=(TextView)findViewById(R.id.teacher_name);
        teacherCourse=(TextView)findViewById(R.id.teacher_course);
        findViewById(R.id.remark_back).setOnClickListener(this);
        listView=(ListView)findViewById(R.id.remark_list);
        Bundle bundle=this.getIntent().getExtras();
        teacher=(Teacher) bundle.getSerializable("teacher");
    }
    private void setData(){
        teacherImage.setImageResource(teacher.getFace());
        teacherName.setText(teacher.getName());
        teacherCourse.setText(teacher.getCourse());
        String[] remarkArray=this.getResources().getStringArray(R.array.remark_array);
        for(int i=0;i<remarkArray.length;i++){
            Remark remark=new Remark();
            remark.setId(i);
            remark.setContent(remarkArray[i]);
            list.add(remark);
        }
        adapter=new RemarkListAdapter(this, list,teacher);
        listView.setAdapter(adapter);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.getId()==R.id.remark_back){
            finish();
        }
    }
}