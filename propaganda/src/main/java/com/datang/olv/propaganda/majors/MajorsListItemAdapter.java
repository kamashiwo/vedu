package com.datang.olv.propaganda.majors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;

/**
 * Created by liqing3 on 14-7-28.
 */
public class MajorsListItemAdapter extends BaseAdapter {

    private ArrayList<MajorsActivity.MajorsItem> mList;
    private Context context;
    private MajorsActivity acti;

    private LayoutInflater inflater = null;

    public MajorsListItemAdapter(ArrayList<MajorsActivity.MajorsItem> list, Context context, MajorsActivity activity) {
        this.context = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
        //isSelected = new HashMap<Integer, Boolean>();
        acti = activity;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        MajorListItemView listItemView;

        // 初始化item view
        if (convertView == null) {
            // 通过LayoutInflater将xml中定义的视图实例化到一个View中
            convertView = inflater.inflate(
                    R.layout.major_list_item, null);

            // 实例化一个封装类ListItemView，并实例化它的两个域
            listItemView = new MajorListItemView();
            listItemView.zhuView = (TextView) convertView
                    .findViewById(R.id.majorslist_text);
            listItemView.fuView = (TextView) convertView
                    .findViewById(R.id.majorslist_futext);

            // 将ListItemView对象传递给convertView
            convertView.setTag(listItemView);
        } else {
            // 从converView中获取ListItemView对象
            listItemView = (MajorListItemView) convertView.getTag();
        }

        // 获取到mList中指定索引位置的资源
        String zhutext = mList.get(position).getZhuText();
        String futext = mList.get(position).getFuText();

        // 将资源传递给ListItemView的两个域对象
        listItemView.zhuView.setText(zhutext);
        listItemView.fuView.setText(futext);

        // 返回convertView对象
        return convertView;
    }

    public class MajorListItemView {
        TextView zhuView;
        TextView fuView;
    }
}
