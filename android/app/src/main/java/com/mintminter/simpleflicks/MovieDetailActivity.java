package com.mintminter.simpleflicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mintminter.simpleflicks.api.ApiCallback;
import com.mintminter.simpleflicks.api.ApiManager;
import com.mintminter.simpleflicks.api.Bootstrap;
import com.mintminter.simpleflicks.api.MovieContext;
import com.mintminter.simpleflicks.util.Common;

/**
 * Created by Irene on 9/17/17.
 */

public class MovieDetailActivity extends YouTubeBaseActivity implements ApiCallback{

    private YouTubePlayerView mYoutubePlayerView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private TextView mRelease;
    private TextView mScore;
    private TextView mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String sMovie = getIntent().getStringExtra(Common.EXTRA_MOVIE);
        if(TextUtils.isEmpty(sMovie)){
            finish();
        }

        MovieContext.Movie movie = new MovieContext.Movie();
        movie.loadFromString(sMovie);

        setContentView(R.layout.activity_moviedetail);
        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mToolbar.setTitle(movie.title);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.detail_youtube);
        new ApiManager().getTrailer(movie.id, "en_US", this);

        mTitle = (TextView) findViewById(R.id.detail_title);
        mRelease = (TextView) findViewById(R.id.detail_release);
        mScore = (TextView) findViewById(R.id.detail_score);
        mDescription = (TextView) findViewById(R.id.detail_description);

        mTitle.setText(movie.title);
        mDescription.setText(movie.overview);
        mRelease.setText(movie.release_date);
        mScore.setText(movie.vote_average + "");

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void setPlayingList(MovieContext.PlayingList playList) {

    }

    @Override
    public void setFailure(int statusCode, String res) {

    }

    @Override
    public void setTrailer(final MovieContext.Trailer trailer) {
        if(trailer == null){
            return;
        }
        mYoutubePlayerView.initialize(Bootstrap.GOOGLEAPIKEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(trailer.key);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}
