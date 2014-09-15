package com.datang.olv.propaganda.evaluate;

import java.util.List;

import com.datang.olv.propaganda.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author nannan
 *
 */
public class TeacherListAdapter extends BaseAdapter {
	private List<Teacher> list;
	private Context context;
	public TeacherListAdapter(List<Teacher> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	private Holder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.teacher_list_item, null);
			holder=new Holder();
			holder.face=(ImageView)convertView.findViewById(R.id.teachers_item_face);
			holder.name=(TextView)convertView.findViewById(R.id.teachers_item_name);
			holder.course=(TextView)convertView.findViewById(R.id.teachers_item_course);
			holder.remark=(Button)convertView.findViewById(R.id.teachers_item_remark);
			holder.listener=new MyListener();
			holder.remark.setOnClickListener(holder.listener);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		holder.face.setImageResource(list.get(position).getFace());
		holder.name.setText(list.get(position).getName());
		holder.course.setText(list.get(position).getCourse());
		holder.listener.setIndex(position);
		if(list.get(position).isHasRemarked()){
			holder.remark.setText(context.getResources().getString(R.string.hasremarked));
			holder.remark.setBackgroundColor(context.getResources().getColor(R.color.color_selected));
		}else{
			holder.remark.setText(context.getResources().getString(R.string.goremark));
			//			holder.remark.setBackground(context.getResources().getDrawable(R.drawable.button_backgroud));
			//			holder.remark.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.button_backgroud));
			holder.remark.setBackgroundResource(R.drawable.button_backgroud);
		}
		return convertView;
	}
	private class Holder{
		private ImageView face;
		private TextView name;
		private TextView course;
		private Button remark;
		private MyListener listener;
	}
	private class MyListener implements OnClickListener{
		private int index;

		public void setIndex(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(list.get(index).isHasRemarked()){
				return;
			}
			Intent intent=new Intent();
			Bundle bundle=new Bundle();
			bundle.putSerializable("teacher", list.get(index));
			intent.putExtras(bundle);
			intent.setClass(context, RemarkActivity.class);
			((Activity)context).startActivityForResult(intent, 100);
		}

	}

}
