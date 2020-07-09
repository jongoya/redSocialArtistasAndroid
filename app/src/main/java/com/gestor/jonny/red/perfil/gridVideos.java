package com.gestor.jonny.red.perfil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.gestor.jonny.red.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;

import java.util.ArrayList;

/**
 * Created by jonny on 29/10/15.
 */
public class gridVideos extends BaseAdapter implements YouTubePlayer.OnInitializedListener{
    public static final String API_KEY = "AIzaSyCdakw7-rIuATQ4yjwwg8EAVao8obJ5KrM";
    //http://youtu.be/<VIDEO_ID>
    public static final String VIDEO_ID = "dKLftgvYsVU";

    private Context mContext;
    private final ArrayList<String> textViewValues;
    @Override
    public int getCount() {
        //return textViewValues.size();
        return 1;
}
    public gridVideos(Context c,ArrayList<String> valores){
        mContext = c;
        textViewValues=valores;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.item_videos, null);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.imagenGale);
            gridView.setLayoutParams(new GridView.LayoutParams(155, 155));
            YouTubePlayerView youTubePlayerView = (YouTubePlayerView)gridView.findViewById(R.id.youtube_player);
            youTubePlayerView.initialize(API_KEY, this);
            //gridView.setBackgroundDrawable(drawable);
        } else {
            gridView = (View) convertView;
            //imageView = (ImageView) convertView;
        }
        //imageView.setImageResource(mThumbIds[position]);
        //return imageView;
        return gridView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("fallo al reproducir");
    }
    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}
