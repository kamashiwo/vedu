package com.datang.olv.propaganda.evaluate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datang.olv.propaganda.R;
import com.squareup.picasso.Picasso;

/**
 * Created by liqing3 on 14-7-31.
 */
public class EvaluatingFragment extends Fragment {


    public static EvaluatingFragment newInstance(int imageId){
        EvaluatingFragment fragment = new EvaluatingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        bundle.putInt("iv_id", imageId);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test, null);
        int scaleSize = this.getResources().getInteger(R.integer.scale_size);
        Picasso.with(this.getActivity()).load(getArguments().getInt("iv_id")).resize(scaleSize,scaleSize).centerInside().into((android.widget.ImageView) view.findViewById(R.id.iv_test));
        return view;
    }
}
