package com.datang.olv.propaganda.enrollment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.overview.HistoryListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-7-28.
 */
public class AdmissionFragment extends Fragment {
    private ListView listView;
    private AdmissionListAdapter statusAdapter;
    private Context context;
    private LayoutInflater mflater;
    private List<String> mList = null;

    private View mview;
    private static String Str2014qiu = "上海新侨职业技术学院2014年秋季招生计划";
    private static String Str2014pu = "新侨学院2014年上海市普通高等学校招生简章";
    private static String Str2014san = "上海新侨学院2014年三校招生简章";
    private static String Str2014fa = "上海新侨职业技术学院2014年依法自主招生章程";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mflater = inflater;

        mview = (View)inflater.inflate(R.layout.fragment_enrollment_admission, null);

        listView = (ListView)mview.findViewById(R.id.admission_list);

        context = this.getActivity();

        mList = new ArrayList<String>();

        mList.add(Str2014qiu);
        mList.add(Str2014pu);
        mList.add(Str2014san);
        mList.add(Str2014fa);
        statusAdapter = new AdmissionListAdapter(context, mList);
        listView.setAdapter(statusAdapter);
        return mview;
    }

}
