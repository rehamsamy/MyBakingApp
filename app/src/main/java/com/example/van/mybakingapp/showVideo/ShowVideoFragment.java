package com.example.van.mybakingapp.showVideo;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.van.mybakingapp.R;
import com.example.van.mybakingapp.VideoStepsActivity;
import com.example.van.mybakingapp.video.VideoFragment;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URI;

public class ShowVideoFragment extends Fragment  implements ExoPlayer.EventListener{
    private static final String TAG=ShowVideoFragment.class.getSimpleName();
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer player;
    private MediaSource mediaSource;
   private MediaSession mMediaSession;
   private PlaybackState.Builder mStateBuilder ;
   private TrackSelectionArray array;
    public ShowVideoFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_video_show,container,false);
        exoPlayerView=(SimpleExoPlayerView) view.findViewById(R.id.exo_player_view);


        TrackSelector selector=new DefaultTrackSelector();
        LoadControl control=new DefaultLoadControl();

        player= ExoPlayerFactory.newSimpleInstance(getContext(),selector,control);
        exoPlayerView.setPlayer(player);


        String videoUrl= VideoStepsActivity.videoUrl;
        Uri  uri= Uri.parse(videoUrl);
        String userAgent= Util.getUserAgent(getContext(),"ShowVideoFragment");

        mediaSource=new ExtractorMediaSource(uri,new DefaultDataSourceFactory(getContext(),userAgent),new DefaultExtractorsFactory(),null,null);

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        if(player==null){
            Log.v(TAG,"exo player is null");
        }



        return view;
    }

    public void releasePlayer(){
        player.stop();
        player.release();
    }


    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }



    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        array=trackSelections;




    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mStateBuilder.setState(PlaybackState.STATE_PLAYING,
                   player.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            mStateBuilder.setState(PlaybackState.STATE_PAUSED,
                    player.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());



    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mMediaSession = new MediaSession(getContext(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSession.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackState.Builder()
                .setActions(
                        PlaybackState.ACTION_PLAY |
                                PlaybackState.ACTION_PAUSE |
                                PlaybackState.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackState.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
       // mMediaSession.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);

    }



}
