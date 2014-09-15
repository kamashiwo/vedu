package com.datang.olv.propaganda.scence;

import android.content.Intent;
import android.os.Bundle;

import com.datang.olv.propaganda.R;
import com.panoramagl.PLCubicPanorama;
import com.panoramagl.PLIPanorama;
import com.panoramagl.PLImage;
import com.panoramagl.PLView;
import com.panoramagl.enumeration.PLCubeFaceOrientation;
import com.panoramagl.utils.PLUtils;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by liqing3 on 14-8-4.
 */
public class ScencePanoramaActivity extends PLView {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle value = intent.getBundleExtra("DATA");

        ArrayList<Integer> panoPicID = new ArrayList<Integer>();
        panoPicID = value.getIntegerArrayList("ID");

        this.loadPanorama(panoPicID);

    }

    /**
     * This event is fired when OpenGL renderer was created
     * @param gl Current OpenGL context
     */
    @Override
    protected void onGLContextCreated(GL10 gl) {
        super.onGLContextCreated(gl);
    }

    /**
     * Load panorama image manually
     *
     */
    private void loadPanorama(ArrayList<Integer> panoPicID)
    {
        GL10 gl = this.getCurrentGL();
        PLIPanorama panorama = null;
        //Lock panoramic view
        this.setBlocked(true);
        //Spherical panorama example (supports up 1024x512 texture)
        //Cubic panorama example (supports up 1024x1024 texture per face)
        PLCubicPanorama cubicPanorama = new PLCubicPanorama();
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(0).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationFront);
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(1).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationBack);
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(3).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationLeft);
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(2).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationRight);
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(4).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationUp);
        cubicPanorama.setImage(gl, PLImage.imageWithBitmap(PLUtils.getBitmap(this, panoPicID.get(5).intValue()), false), PLCubeFaceOrientation.PLCubeFaceOrientationDown);
        panorama = cubicPanorama;

        this.reset();
        this.setPanorama(panorama);
        //Unlock panoramic view
        this.setBlocked(false);
    }
}
