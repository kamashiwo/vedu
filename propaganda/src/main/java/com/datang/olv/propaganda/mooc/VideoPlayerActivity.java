package com.datang.olv.propaganda.mooc;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.datang.olv.propaganda.R;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by l on 14-9-2.
 */
public class VideoPlayerActivity extends Activity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,  View.OnClickListener, Handler.Callback, SeekBar.OnSeekBarChangeListener {
    public static final String TAG = "VideoPlayerActivitG";
    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
//    private SurfaceHolder surfaceHolder;
    private int videoWidth;
    private int videoHeight;
    private RelativeLayout frame;
    private ImageView videoStart;
    private ImageView videoController;
    private RelativeLayout top;
    private RelativeLayout bottom;
    private TextView maxTime;
    private TextView curTime;
    private Timer timer = new Timer();
    private TimerTask task;
    private Handler handler;
    private DecimalFormat df = new DecimalFormat("00");
    private SeekBar seekBar;
    private ImageView size;
//    private DisplayMetrics metrics = new DisplayMetrics();

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("VideoPlayer OnCreate()");

        setContentView(R.layout.activity_video_player);
        Log.e(TAG, "VideoPlayerActivity onCreate");
        init();
        Log.e(TAG, "VideoPlayerActivity Init");
        handler = new Handler(this);
        task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                handler.sendEmptyMessage(0);
            }
        };
    }

    @SuppressWarnings("deprecation")
    private void init() {
        this.mediaPlayer = new MediaPlayer();
        this.surfaceView = (SurfaceView) this.findViewById(R.id.videoplayer_surfaceview);
        this.surfaceView.setOnClickListener(new MenuShowHideListener());
        surfaceView.getHolder().setFixedSize(176, 144);//设置分辨率
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置surfaceview不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前
        surfaceView.getHolder().addCallback(new SurceCallBack());//对surface对象的状态进行监听
        frame = (RelativeLayout) findViewById(R.id.videoplayer_frame);
        ViewGroup.LayoutParams p = frame.getLayoutParams();
//        p.width = metrics.widthPixels;
//        p.height = 400;
//        frame.setLayoutParams(p);
        videoStart = (ImageView) findViewById(R.id.videoplay_play);
        videoStart.setVisibility(View.VISIBLE);
        videoStart.setOnClickListener(this);
        videoController = (ImageView) findViewById(R.id.videoplay_resume);
        videoController.setOnClickListener(new StateControlListener());
        this.top = (RelativeLayout) findViewById(R.id.videoplay_top);
        this.top.setVisibility(View.GONE);
        this.bottom = (RelativeLayout) findViewById(R.id.videoplay_bottom);
        this.bottom.setVisibility(View.GONE);
        this.maxTime = (TextView) findViewById(R.id.videoplay_maxtime);
        this.curTime = (TextView) findViewById(R.id.videoplay_curtime);
        seekBar = (SeekBar) findViewById(R.id.videoplay_progress);
        size = (ImageView) findViewById(R.id.videoplay_size);
        size.setOnClickListener(this);
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    private void playVideo() {
        this.mediaPlayer.reset();
        File file = new File(Environment.getExternalStorageDirectory(), "MOV_0074.mp4");

        if (!file.exists()) {
            Log.e("VideoPlayerActivity", file.getAbsolutePath() + " Video NOT EXIST");
            return;
        } else {
            Log.e("VideoPlayerActivity", file.getAbsolutePath() + "Video EXIST");
        }
        this.mediaPlayer.setDisplay(this.surfaceView.getHolder());
        this.mediaPlayer.setOnCompletionListener(this);
        //		this.mediaPlayer.setOnPreparedListener(this);
        //		this.mediaPlayer.setOnBufferingUpdateListener(this);
        this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            this.mediaPlayer.setDataSource(file.getAbsolutePath());
            this.mediaPlayer.prepare();
            setSize();
            this.maxTime.setText(getTime(mediaPlayer.getDuration()));
            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setOnSeekBarChangeListener(this);
            this.mediaPlayer.start();
            videoStart.setVisibility(View.GONE);
            videoController.setImageResource(R.drawable.mook_pause);
            timer.schedule(task, 0, 1000);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private void setSize() {
        this.videoWidth = this.mediaPlayer.getVideoWidth();
        this.videoHeight = this.mediaPlayer.getVideoHeight();
        if (this.videoWidth <= 0 || this.videoHeight <= 0) {
            return;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams p = frame.getLayoutParams();
        p.width = metrics.widthPixels;
        p.height = (int) (p.width * ((float) this.videoHeight / this.videoWidth));
        frame.setLayoutParams(p);
    }

    private String getTime(int time) {
        float duration = (float) (time / 1000.0);
        int h = (int) (duration / 3600);
        int m = (int) ((duration % 3600) / 60);
        int s = (int) (duration % 60);
        return df.format(h) + ":" + df.format(m) + ":" + df.format(s);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO Auto-generated method stub
        this.curTime.setText(getTime(mediaPlayer.getDuration()));
        videoStart.setVisibility(View.VISIBLE);
        videoController.setImageResource(R.drawable.mook_play);
        task.cancel();
        task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                handler.sendEmptyMessage(0);
            }
        };
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    private class MenuShowHideListener implements View.OnClickListener {
        private boolean isShow = false;

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (isShow) {
                top.setVisibility(View.GONE);
                bottom.setVisibility(View.GONE);
                isShow = false;
            } else {
                top.setVisibility(View.VISIBLE);
                bottom.setVisibility(View.VISIBLE);
                isShow = true;
            }
        }

    }

    private class StateControlListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (mediaPlayer == null) {
                Log.e(TAG,"mediaPlayer is null");
                return;
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                task.cancel();
                task = new TimerTask() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        handler.sendEmptyMessage(0);
                    }
                };
                videoController.setImageResource(R.drawable.mook_play);
            } else {
                mediaPlayer.start();
                timer.schedule(task, 0, 1000);
                videoController.setImageResource(R.drawable.mook_pause);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.videoplay_play) {
            playVideo();
        } else if (v.getId() == R.id.videoplay_size) {
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        // TODO Auto-generated method stub
        if (msg.what == 0) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                curTime.setText(getTime(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        if (fromUser) {
            mediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        curTime.setText(getTime(seekBar.getProgress()));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
//		super.onConfigurationChanged(newConfig);
//		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
//			ViewGroup.LayoutParams p=frame.getLayoutParams();
//			p.width=metrics.heightPixels;
//			p.height=metrics.widthPixels;
//			frame.setLayoutParams(p);
//		}else{
//			ViewGroup.LayoutParams p=frame.getLayoutParams();
//			p.width=metrics.widthPixels;
//			p.height=400;
//			frame.setLayoutParams(p);
//		}

    }

    private class SurceCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.e(TAG, "surfaceCreated");
            // TODO Auto-generated method stub
            try {
                playVideo();//自动播放
            } catch (Exception e) {
                Log.i("cat", ">>>error", e);
            }
            Log.i("cat", ">>>surface created");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // TODO Auto-generated method st
            Log.e(TAG, "surfaceChanged");

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stu        b
            mediaPlayer.stop();
            Log.e(TAG,"surfaceDestroyed");

        }

    }
}
