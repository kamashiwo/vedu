package com.datang.olv.propaganda.enrollment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.datang.olv.propaganda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l on 14-7-28.
 */
public class EmploymentFragment extends Fragment {
    private ListView listView;
    private EmploymentListAdapter statusAdapter;
    private Context context;
    private LayoutInflater mflater;
    private List<String> mList = null;

    private View mview;
    private static String Str2012 = "2012届毕业生的就业率和签约率分别为98.55%和99.08%";
    private static String Str2013 = "2013届毕业生的就业率和签约率分别为99.75%和99.12%";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mflater = inflater;

        mview = (View)inflater.inflate(R.layout.fragment_enrollment_employment, null);

        listView = (ListView)mview.findViewById(R.id.employment_list);

        context = this.getActivity();

        mList = new ArrayList<String>();

        mList.add(Str2012);
        mList.add(Str2013);
        statusAdapter = new EmploymentListAdapter(context, mList);
        listView.setAdapter(statusAdapter);
        return mview;
    }
}
