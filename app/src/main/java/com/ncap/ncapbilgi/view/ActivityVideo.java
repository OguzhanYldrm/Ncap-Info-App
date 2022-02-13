package com.ncap.ncapbilgi.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.ncap.ncapbilgi.R;
import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class ActivityVideo extends AppCompatActivity implements IVLCVout.Callback {
    private LibVLC mLibVLC = null;
    private org.videolan.libvlc.MediaPlayer mMediaPlayer = null;
    private String URL;
    private SurfaceView mVideoSurface = null;
    private IVLCVout vlcVout;
    private Media media;
    private MediaController controller;
    private int mHeight;
    private int mWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //Get Url
        Intent intent = getIntent();
        URL = intent.getStringExtra("url");
        mVideoSurface = findViewById(R.id.surface);

        //Set Toolbar
        Toolbar toolbar = findViewById(R.id.middle_page_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.back_icon) {
                    onBackPressed();
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_middlepage);


        //Set Video Area
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mHeight = displayMetrics.heightPixels;
        mWidth = displayMetrics.widthPixels;


        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(this, args);
        mMediaPlayer = new MediaPlayer(mLibVLC);
        vlcVout = mMediaPlayer.getVLCVout();


        controller = new MediaController(this);
        controller.setMediaPlayer(playerInterface);
        controller.setAnchorView(mVideoSurface);

        mVideoSurface.setOnClickListener(v -> controller.show(3000));



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mLibVLC.release();

    }

    @Override
    protected void onStart() {
        super.onStart();
        vlcVout.setVideoView(mVideoSurface);
        vlcVout.setWindowSize(mWidth,mHeight);
        vlcVout.addCallback(this);
        vlcVout.attachViews();


        if (!URL.isEmpty()) {
            try {
                media = new Media(mLibVLC, Uri.parse(URL));
                mMediaPlayer.setMedia(media);
                media.release();
            } catch (Exception e) {
                throw new RuntimeException("Invalid asset folder");
            }
        } else {
            Toast.makeText(getApplicationContext(), "URL EMPTY", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.stop();
        vlcVout.detachViews();
        vlcVout.removeCallback(this);

    }

    @Override
    public void onSurfacesCreated(IVLCVout vlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vlcVout) {

    }

    private MediaController.MediaPlayerControl playerInterface = new MediaController.MediaPlayerControl() {
        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            float pos = mMediaPlayer.getPosition();
            return (int) (pos * getDuration());
        }

        public int getDuration() {
            return (int) mMediaPlayer.getLength();
        }

        public boolean isPlaying() {
            return mMediaPlayer.isPlaying();
        }

        public void pause() {
            mMediaPlayer.pause();
        }

        public void seekTo(int pos) {
            mMediaPlayer.setPosition((float) pos / getDuration());
        }

        public void start() {
            mMediaPlayer.play();
        }


        public boolean canPause() {
            return true;
        }

        public boolean canSeekBackward() {
            return true;
        }

        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }
    };
}
