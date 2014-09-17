package com.datang.olv.propaganda.overview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.datang.olv.propaganda.HttpClient.HttpClientInterface;
import com.datang.olv.propaganda.OlvApplication;
import com.datang.olv.propaganda.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public final class HistoryFragment extends Fragment {

    private ListView listView;
    private HistoryListAdapter statusAdapter;
    private Context context;
    private LayoutInflater mflater;
    private List<String> mList = null;

    private View mview;
    private static String Str1993 = "1993年，上海新侨职业技术学院（简称新侨学院）创建";
    private static String Str1999 = "1999年，新侨学院成为上海市首批由国家教育部批准独立设置、面向全国招生的全日制民办高职院校之一";
    private static String Str2005 = "2005年，新侨学院成为上海市首批依法自主招生的高校之一";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mflater = inflater;

        mview = (View)inflater.inflate(R.layout.fragment_history, null);

        listView = (ListView)mview.findViewById(R.id.history_item_list);

        context = this.getActivity();

        mList = new ArrayList<String>();

        OlvApplication app = (OlvApplication)getActivity().getApplication();

        HttpClientInterface.Schoolinfo mch = app.getGschoolinfo();

        if (mch != null){
            for (int i=0; i<mch.histories.size();i++){
                HttpClientInterface.historiesinfo temp = mch.histories.get(i);
                mList.add(temp.getDesc());
            }
        }

        //mList.add(Str1993);
        //mList.add(Str1999);
        //mList.add(Str2005);

        statusAdapter = new HistoryListAdapter(context, mList);
        listView.setAdapter(statusAdapter);
        return mview;
    }


}
