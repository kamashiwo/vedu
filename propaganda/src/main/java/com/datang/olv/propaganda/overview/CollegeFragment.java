package com.datang.olv.propaganda.overview;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.datang.olv.propaganda.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public final class CollegeFragment extends Fragment {

    private Context mctx;

    private View mview;

    private LayoutInflater mflater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mflater = inflater;

        mview = (View)inflater.inflate(R.layout.fragment_college, null);

        // 获取Resources对象
        Resources res = this.getResources();

        mctx = this.getActivity();
        return mview;
    }


}
