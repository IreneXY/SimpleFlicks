package com.mintminter.simpleflicks;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mintminter.simpleflicks.api.Bootstrap;
import com.mintminter.simpleflicks.util.Common;

/**
 * Created by Irene on 9/16/17.
 */

public class YoutubeActivity extends YouTubeBaseActivity {
    private YouTubePlayerView mYoutubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String videoId = getIntent().getStringExtra(Common.EXTRA_YOUTUBEVIDEOID);
        if(TextUtils.isEmpty(videoId)){
            finish();
        }

        setContentView(R.layout.activity_youtube);
        mYoutubeView = (YouTubePlayerView) findViewById(R.id.player);

        mYoutubeView.initialize(Bootstrap.GOOGLEAPIKEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                            @Override
                            public void onLoading() {

                            }

                            @Override
                            public void onLoaded(String s) {

                            }

                            @Override
                            public void onAdStarted() {

                            }

                            @Override
                            public void onVideoStarted() {

                            }

                            @Override
                            public void onVideoEnded() {

                            }

                            @Override
                            public void onError(YouTubePlayer.ErrorReason errorReason) {
                                Log.e("YouTubePlayer Error", errorReason.name());
                            }
                        });
                        youTubePlayer.loadVideo(videoId);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

    }

}
