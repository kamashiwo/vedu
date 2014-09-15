package com.datang.olv.propaganda.overview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;

/**
 * Created by liqing3 on 14-7-26.
 */
public class LeaderListAdapter extends BaseAdapter {
    private ArrayList<LeaderFragment.LeaderListItem> mList;
    private Context context;
    private LeaderFragment acti;

    private LayoutInflater inflater = null;

    public LeaderListAdapter(ArrayList<LeaderFragment.LeaderListItem> list, Context context, LeaderFragment activity) {
        this.context = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
        //isSelected = new HashMap<Integer, Boolean>();
        acti = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ListItemView listItemView;

        // 初始化item view
        if (convertView == null) {
            // 通过LayoutInflater将xml中定义的视图实例化到一个View中
            convertView = inflater.inflate(
                    R.layout.fragment_leader_list_item, null);

            // 实例化一个封装类ListItemView，并实例化它的两个域
            listItemView = new ListItemView();
            listItemView.imageView = (ImageView) convertView
                    .findViewById(R.id.leader_item_image);
            listItemView.textView = (TextView) convertView
                    .findViewById(R.id.leader_item_title);

            // 将ListItemView对象传递给convertView
            convertView.setTag(listItemView);
        } else {
            // 从converView中获取ListItemView对象
            listItemView = (ListItemView) convertView.getTag();
        }

        // 获取到mList中指定索引位置的资源
        Drawable img = mList.get(position).getImage();
        String title = mList.get(position).getTitle();

        // 将资源传递给ListItemView的两个域对象
        listItemView.imageView.setImageDrawable(img);
        listItemView.textView.setText(title);

        // 返回convertView对象

        return convertView;
    }

    class ListItemView {
        ImageView imageView;
        TextView textView;
    }
}
