package com.datang.olv.propaganda.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.util.List;

/**
 * Created by l on 14-7-26.
 */
public class HistoryListAdapter  extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<String> oneList;
    private Context context;

    public HistoryListAdapter(Context context, List<String> oneList) {
        this.oneList = oneList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return oneList.size();
    }

    @Override
    public Object getItem(int i) {
        return oneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        // ³õÊŒ»¯item view
        if (convertView == null) {
            // Íš¹ýLayoutInflaterœ«xmlÖÐ¶šÒåµÄÊÓÍŒÊµÀý»¯µœÒ»žöViewÖÐ
            convertView = inflater.inflate(
                    R.layout.fragment_history_list_item, null);

            // ÊµÀý»¯Ò»žö·â×°ÀàListItemView£¬²¢ÊµÀý»¯ËüµÄÁœžöÓò
            textView = (TextView) convertView
                    .findViewById(R.id.history_right_text);

            // œ«ListItemView¶ÔÏóŽ«µÝžøconvertView
            convertView.setTag(textView);
        } else {
            // ŽÓconverViewÖÐ»ñÈ¡ListItemView¶ÔÏó
            textView = (TextView) convertView.getTag();
        }

        // »ñÈ¡µœmListÖÐÖž¶šË÷ÒýÎ»ÖÃµÄ×ÊÔŽ

        String title = oneList.get(position);

        // œ«×ÊÔŽŽ«µÝžøListItemViewµÄÁœžöÓò¶ÔÏó
        //listItemView.imageView.setImageDrawable(img);
        textView.setText(title);

        // ·µ»ØconvertView¶ÔÏó

        return convertView;
    }
}
