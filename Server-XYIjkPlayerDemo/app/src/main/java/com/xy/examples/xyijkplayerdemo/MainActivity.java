package com.xy.examples.xyijkplayerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity implements VideoListener{


    IMediaPlayer mediaPlayer;
    XYPlayerFragmentLayout xyPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xyPlayerView = (XYPlayerFragmentLayout) findViewById(R.id.xy_player);
        xyPlayerView.setVideoListener(this);


        // http test address
        // xyPlayerView.setPath("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov");

        // rtsp test address
        // xyPlayerView.setPath("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");

        // rtsp phone
        xyPlayerView.setPath("rtsp://192.168.43.1:8086");
        try {
            xyPlayerView.load();
        } catch (IOException e) {
            Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
            e.printStackTrace();
        }


        // airplay test activity

        Button button = (Button) findViewById(R.id.start_air_play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AirplayActivity.class);
                startActivity(intent);
            }
        });
        

    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        xyPlayerView.start();
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }



}
