package com.ncap.ncapbilgi.view;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ncap.ncapbilgi.R;
import com.ncap.ncapbilgi.model.Ncap;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;


public class VideoFragment extends Fragment implements IVLCVout.Callback {

    private LibVLC mLibVLC = null;
    private org.videolan.libvlc.MediaPlayer mMediaPlayer = null;
    private String URL;
    private SurfaceView mVideoSurface = null;
    private IVLCVout vlcVout;
    private Media media;
    private MediaController controller;
    private int mHeight;
    private int mWidth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video,container,false);

        //Get args
        Bundle args = getArguments();
        URL = args.getString("url");

        mVideoSurface = rootView.findViewById(R.id.surface);

        final ArrayList<String> attributes = new ArrayList<>();
        attributes.add("-vvv");
        mLibVLC = new LibVLC(getContext(), attributes);
        mMediaPlayer = new MediaPlayer(mLibVLC);
        vlcVout = mMediaPlayer.getVLCVout();
        vlcVout.setWindowSize(960,600);

        controller = new MediaController(getContext());
        controller.setMediaPlayer(playerInterface);
        controller.setAnchorView(mVideoSurface);
        mVideoSurface.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.show(10000);
            }
        });



        return rootView;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mLibVLC.release();

    }

    @Override
    public void onStart() {
        super.onStart();
        vlcVout.setVideoView(mVideoSurface);
        vlcVout.attachViews();
        mMediaPlayer.getVLCVout().addCallback(this);


        if (URL != "") {
            try {
                media = new Media(mLibVLC, Uri.parse(URL));
                media.setHWDecoderEnabled(true,true);
                mMediaPlayer.setMedia(media);
                media.release();
            } catch (Exception e) {
                throw new RuntimeException("Invalid asset folder or url");
            }

        } else {
            try {

                String ASSET_FILENAME = "ncap.mp4";
                media = new Media(mLibVLC,getActivity().getAssets().openFd(ASSET_FILENAME));
                media.setHWDecoderEnabled(true,true);
                mMediaPlayer.setMedia(media);
                media.release();
            } catch (Exception e) {
                throw new RuntimeException("Invalid asset folder or url");
            }
        }
        mMediaPlayer.play();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMediaPlayer.stop();
        mMediaPlayer.getVLCVout().detachViews();
        mMediaPlayer.getVLCVout().removeCallback(this);

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
