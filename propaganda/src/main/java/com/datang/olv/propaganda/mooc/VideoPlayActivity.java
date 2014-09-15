package com.datang.olv.propaganda.mooc;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.datang.olv.propaganda.R;

import java.io.File;

/**
 * Created by l on 14-9-3.
 */
public class VideoPlayActivity extends Activity {
    private VideoView video1;
    MediaController mediaco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        video1=(VideoView)findViewById(R.id.videoview);
        mediaco=new MediaController(this);
        File file = new File(Environment.getExternalStorageDirectory(), "test.mp4");
        if(file.exists()){
            //VideoView与MediaController进行关联
            video1.setVideoPath(file.getAbsolutePath());
            video1.setMediaController(mediaco);
            mediaco.setMediaPlayer(video1);
            //让VideiView获取焦点
            video1.requestFocus();
        }

    }

}