package com.datang.olv.propaganda.institution;

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
public class InstitutionListItemAdapter extends BaseAdapter {

    private ArrayList<String> mList;
    private Context context;
    private InstitutionActivity acti;

    private LayoutInflater inflater = null;

    public InstitutionListItemAdapter(ArrayList<String> list, Context context, InstitutionActivity activity) {
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
    public View getView(int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        TextView listItemText;

        // 初始化item view
        if (convertView == null) {
            // 通过LayoutInflater将xml中定义的视图实例化到一个View中
            convertView = inflater.inflate(
                    R.layout.institution_list_item, null);

            // 实例化一个封装类ListItemView，并实例化它的两个域

            listItemText = (TextView) convertView
                    .findViewById(R.id.institution_text);

            // 将ListItemView对象传递给convertView
            convertView.setTag(listItemText);
        } else {
            // 从converView中获取ListItemView对象
            listItemText = (TextView) convertView.getTag();
        }

        // 获取到mList中指定索引位置的资源
        String img = mList.get(i);


        // 将资源传递给ListItemView的两个域对象

        listItemText.setText(img);

        // 返回convertView对象

        return convertView;
    }
}
