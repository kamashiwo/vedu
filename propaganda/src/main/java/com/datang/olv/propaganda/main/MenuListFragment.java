package com.datang.olv.propaganda.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.enrollment.EnrollmentActivity;
import com.datang.olv.propaganda.evaluate.EvaluatingActivity;
import com.datang.olv.propaganda.institution.InstitutionActivity;
import com.datang.olv.propaganda.majors.MajorsActivity;
import com.datang.olv.propaganda.map.MapActivity;
import com.datang.olv.propaganda.mooc.MoocActivity;
import com.datang.olv.propaganda.news.NewsActivity;
import com.datang.olv.propaganda.overview.OverviewActivity;
import com.datang.olv.propaganda.scence.ScenceActivity;
import com.datang.olv.propaganda.schedule.ScheduleActivity;
import com.datang.olv.propaganda.social.SocialActivity;
import com.datang.olv.propaganda.test.TestActivity;

public class MenuListFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SampleAdapter adapter = new SampleAdapter(getActivity());
        adapter.add(new SampleItem("概况", R.drawable.menu_overview, OverviewActivity.class));
        adapter.add(new SampleItem("风貌", R.drawable.menu_scence, ScenceActivity.class));
        adapter.add(new SampleItem("专业设置", R.drawable.menu_majors, MajorsActivity.class));
        adapter.add(new SampleItem("机构设置", R.drawable.menu_institution, InstitutionActivity.class));
        adapter.add(new SampleItem("新闻", R.drawable.menu_news, NewsActivity.class));
        adapter.add(new SampleItem("招生就业", R.drawable.menu_enrollment, EnrollmentActivity.class));
        adapter.add(new SampleItem("地图", R.drawable.menu_map, MapActivity.class));
        adapter.add(new SampleItem("课程表", R.drawable.menu_schedule, ScheduleActivity.class));
        adapter.add(new SampleItem("空中学院", R.drawable.menu_mooc, MoocActivity.class));
        adapter.add(new SampleItem("评教", R.drawable.menu_evaluate, EvaluatingActivity.class));
        adapter.add(new SampleItem("社团", R.drawable.menu_social, SocialActivity.class));
        adapter.add(new SampleItem("考试报名", R.drawable.menu_test, TestActivity.class));

        setListAdapter(adapter);
    }

    public class SampleItem {
        private final Class clazz;
        public String tag;
        public int iconRes;

        public SampleItem(String tag, int iconRes, Class clazz) {
            this.tag = tag;
            this.iconRes = iconRes;
            this.clazz = clazz;
        }
    }

    public class SampleAdapter extends ArrayAdapter<SampleItem> {

        public SampleAdapter(Context context) {
            super(context, 0);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
            }
            final SampleItem item = getItem(position);
            ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
            icon.setImageResource(item.iconRes);

            TextView title = (TextView) convertView.findViewById(R.id.row_title);
            title.setText(item.tag);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MenuListFragment.this.getActivity().getClass().getSimpleName().equals(item.clazz.getSimpleName())) {
                        MenuListFragment.this.getActivity().onBackPressed();
                        return;
                    }
                    Intent intent = new Intent(MenuListFragment.this.getActivity(), item.clazz);
                    startActivity(intent);
                    MenuListFragment.this.getActivity().finish();
                }
            });

            return convertView;
        }

    }
}
