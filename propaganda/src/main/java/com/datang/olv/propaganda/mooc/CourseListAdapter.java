package com.datang.olv.propaganda.mooc;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

/**
 * @author nannan
 * @createTime 2014-8-27 下午2:19:30 
 * 
 */
public class CourseListAdapter extends BaseAdapter{
	private List<Video> list;
	private Context context;

	public CourseListAdapter(List<Video> list, Context context) {
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
			convertView=LayoutInflater.from(context).inflate(R.layout.course_list_item, null);
			holder=new Holder();
			holder.cover=(ImageView)convertView.findViewById(R.id.course_item_image);
			holder.name=(TextView)convertView.findViewById(R.id.course_item_name);
			holder.classify=(TextView)convertView.findViewById(R.id.course_item_type);
			holder.playCount=(TextView)convertView.findViewById(R.id.course_item_play_counts);
//			holder.listener=new MyListener();
//			holder.cover.setOnClickListener(holder.listener);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		//		holder.cover=加载网络图片
		holder.name.setText(list.get(position).getName());
		holder.classify.setText(list.get(position).getClassify());
		holder.playCount.setText(list.get(position).getPlayCount()+"");
//		holder.listener.setVideo(list.get(position));
		return convertView;
	}
	private class Holder{
		private ImageView cover;
		private TextView name;
		private TextView classify;
		private TextView playCount;
		private MyListener listener;
	}
	private class MyListener implements OnClickListener{
		private Video video;
		public void setVideo(Video video) {
			this.video = video;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//			Intent intent=new Intent();
			//			intent.setClass(context, cls)
		}

	}


}
