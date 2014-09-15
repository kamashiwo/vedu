package com.datang.olv.propaganda.mooc;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
//import com.datang.olv.propaganda.main.MenuListFragment.SampleAdapter;
//import com.datang.olv.propaganda.main.MenuListFragment.SampleItem;

/**
 * Created by qgf on 14-9-4.
 */
public class MoocMenuListFragment extends ListFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup containner, Bundle savedInstaceState){
        return inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstaceState){
        super.onActivityCreated(savedInstaceState);
        MenuListFragment mlf = new MenuListFragment();
        MenuListFragment.SampleAdapter adapter = mlf.new SampleAdapter(getActivity());
        adapter.add(mlf.new SampleItem("我的收藏", R.drawable.menu_mooc_favorite, FavoriteActivity.class));
        adapter.add(mlf.new SampleItem("我的作业", R.drawable.menu_mooc_homework, HomeworkActivity.class));
        //adapter.add(mlf.new SampleItem("断点续播", R.drawable.menu_mooc_playresume, FavoriteActivity.class));
        adapter.add(mlf.new SampleItem("播放历史", R.drawable.menu_mooc_playhistory, HistoryActivity.class));
        adapter.add(mlf.new SampleItem("缓存设置", R.drawable.menu_mooc_cachsetting, CachSettingActivity.class));
        setListAdapter(adapter);
    }
}
