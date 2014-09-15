package com.datang.olv.propaganda.scence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.datang.olv.propaganda.R;
import com.datang.olv.propaganda.main.MenuListFragment;
import com.datang.olv.propaganda.overview.LeaderDetailActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

public class ScenceActivity extends FragmentActivity {
    SlidingMenu menu;

    private ImageView ivJisuanjilou;
    private ArrayList<Integer> JisuanlipanoID;

    private ImageView ivtushuguan;
    private ArrayList<Integer> TushuguanpanoID;

    private ImageView ivsushequ;
    private ArrayList<Integer> SushequpanoID;

    private ImageView ivhuxinting;
    private ArrayList<Integer> HuxintingpanoID;

    private ImageView ivshiyanshinei;
    private ArrayList<Integer> ShiyanshineipanoID;

    private ImageView ivjiaowulou;
    private ArrayList<Integer> JiaowuloupanoID;

    private ImageView ivcaoping;
    private ArrayList<Integer> CaopingpanoID;

    private ImageView ivyuelanshi;
    private ArrayList<Integer> YuelanshipanoID;

    private ImageView ivqicheshixun;
    private ArrayList<Integer> QicheshixunpanoID;

    private ImageView ivjidianshixun;
    private ArrayList<Integer> JidianshixunpanoID;

    private ImageView ivzhubaoshixun;
    private ArrayList<Integer> ZhubaoshixunpanoID;

    private ImageView ivshangwushixun;
    private ArrayList<Integer> ShangwushixunpanoID;

    private ImageView ivguanlishixun;
    private ArrayList<Integer> GuanlishixunpanoID;

    private ImageView ivwaiyushixun;
    private ArrayList<Integer> WaiyushixunpanoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scence);
        this.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScenceActivity.this.finish();

            }
        });

        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new MenuListFragment())
                .commit();

        ivJisuanjilou = (ImageView)this.findViewById(R.id.fengmao_jisuanjilou);

        JisuanlipanoID = new ArrayList<Integer>();

        ivJisuanjilou.setClickable(true);
        ivJisuanjilou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.jisuanjilou);
                value.putIntegerArrayList("pano", JisuanlipanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivtushuguan = (ImageView)this.findViewById(R.id.fengmao_tushuguan);

        TushuguanpanoID = new ArrayList<Integer>();

        ivtushuguan.setClickable(true);
        ivtushuguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.tushuguan);
                value.putIntegerArrayList("pano", JisuanlipanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivsushequ = (ImageView)this.findViewById(R.id.fengmao_sushequ);

        SushequpanoID = new ArrayList<Integer>();

        ivsushequ.setClickable(true);
        ivsushequ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.sushequ);
                value.putIntegerArrayList("pano", SushequpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivhuxinting = (ImageView)this.findViewById(R.id.fengmao_huxinting);

        HuxintingpanoID = new ArrayList<Integer>();

        ivhuxinting.setClickable(true);
        ivhuxinting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.huxinting);
                value.putIntegerArrayList("pano", HuxintingpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivshiyanshinei = (ImageView)this.findViewById(R.id.fengmao_shiyanshinei);

        ShiyanshineipanoID = new ArrayList<Integer>();

        ivshiyanshinei.setClickable(true);
        ivshiyanshinei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.shiyanshinei);
                value.putIntegerArrayList("pano", ShiyanshineipanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivjiaowulou = (ImageView)this.findViewById(R.id.fengmao_jiaowulou);

        JiaowuloupanoID = new ArrayList<Integer>();

        ivjiaowulou.setClickable(true);
        ivjiaowulou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.jiaowulou);
                value.putIntegerArrayList("pano", JiaowuloupanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivcaoping = (ImageView)this.findViewById(R.id.fengmao_caoping);

        CaopingpanoID = new ArrayList<Integer>();

        ivcaoping.setClickable(true);
        ivcaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.caoping);
                value.putIntegerArrayList("pano", CaopingpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivyuelanshi = (ImageView)this.findViewById(R.id.fengmao_yuelanshi);

        YuelanshipanoID = new ArrayList<Integer>();

        ivyuelanshi.setClickable(true);
        ivyuelanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.yuelanshi);
                value.putIntegerArrayList("pano", YuelanshipanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivqicheshixun = (ImageView)this.findViewById(R.id.fengmao_qicheshixun);

        QicheshixunpanoID = new ArrayList<Integer>();

        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_f);
        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_b);
        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_r);
        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_l);
        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_u);
        QicheshixunpanoID.add(R.drawable.pano_qicheshixun_d);

        ivqicheshixun.setClickable(true);
        ivqicheshixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.qicheshixun);
                value.putIntegerArrayList("pano", QicheshixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivjidianshixun = (ImageView)this.findViewById(R.id.fengmao_jidianshixun);

        JidianshixunpanoID = new ArrayList<Integer>();
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_f);
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_b);
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_r);
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_l);
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_u);
        JidianshixunpanoID.add(R.drawable.pano_jidianshixun_d);

        ivjidianshixun.setClickable(true);
        ivjidianshixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.jidianshixun);
                value.putIntegerArrayList("pano", JidianshixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivzhubaoshixun = (ImageView)this.findViewById(R.id.fengmao_zhubaoshixun);

        ZhubaoshixunpanoID = new ArrayList<Integer>();
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_f);
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_b);
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_r);
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_l);
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_u);
        ZhubaoshixunpanoID.add(R.drawable.pano_zhubaoshixun_d);

        ivzhubaoshixun.setClickable(true);
        ivzhubaoshixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.zhubaoshixun);
                value.putIntegerArrayList("pano", ZhubaoshixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivshangwushixun = (ImageView)this.findViewById(R.id.fengmao_shangwushixun);

        ShangwushixunpanoID = new ArrayList<Integer>();

        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_f);
        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_b);
        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_r);
        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_l);
        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_u);
        ShangwushixunpanoID.add(R.drawable.pano_shangwushixun_d);

        ivshangwushixun.setClickable(true);
        ivshangwushixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.shangwushixun);
                value.putIntegerArrayList("pano", ShangwushixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivguanlishixun = (ImageView)this.findViewById(R.id.fengmao_guanlishixun);

        GuanlishixunpanoID = new ArrayList<Integer>();

        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_f);
        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_b);
        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_r);
        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_l);
        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_u);
        GuanlishixunpanoID.add(R.drawable.pano_guanlishixun_d);

        ivguanlishixun.setClickable(true);
        ivguanlishixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.guanlishixun);
                value.putIntegerArrayList("pano", GuanlishixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });

        ivwaiyushixun = (ImageView)this.findViewById(R.id.fengmao_waiyushixun);

        WaiyushixunpanoID = new ArrayList<Integer>();

        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_f);
        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_b);
        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_r);
        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_l);
        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_u);
        WaiyushixunpanoID.add(R.drawable.pano_waiyushixun_d);

        ivwaiyushixun.setClickable(true);
        ivwaiyushixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScenceDetailTemplateActivity

                Intent intent = new Intent(ScenceActivity.this,
                        ScenceDetailTemplateActivity.class);

                Bundle value = new Bundle();
                value.putInt("ID",R.drawable.waiyushixun);
                value.putIntegerArrayList("pano", WaiyushixunpanoID);
                intent.putExtra("DATA",value);

                ScenceActivity.this.startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    public class ScenceDetailInfo{
        private int MainImageID;
        private ArrayList<Integer> mQuanImage;

        public int getImageID() {
            return MainImageID;
        }

        public void setImageID(int imageID) {
            MainImageID = imageID;
        }

        public void AddQuanImage(int QuanID) {mQuanImage.add(new Integer(QuanID)); }
    }
}
