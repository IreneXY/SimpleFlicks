package com.mintminter.simpleflicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mintminter.simpleflicks.api.ApiCallback;
import com.mintminter.simpleflicks.api.ApiManager;
import com.mintminter.simpleflicks.api.MovieContext;
import com.mintminter.simpleflicks.util.Common;

public class MainActivity extends AppCompatActivity implements ApiCallback{

    private RecyclerView mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = (RecyclerView) findViewById(R.id.main_moviebrief_list);
        mMovieList.setLayoutManager(new LinearLayoutManager(this));

        getPlayingList();
    }

    private void getPlayingList(){
        new ApiManager().getPlayingList("en_US", 1, "US", this);
    }

    @Override
    public void setPlayingList(MovieContext.PlayingList playList) {
        mMovieList.setAdapter(new MovieBriefAdapter(this, playList.movies, this));
    }

    @Override
    public void setFailure(int statusCode, String res) {

    }

    @Override
    public void setTrailer(MovieContext.Trailer trailer) {
        if(trailer != null){
            Intent youtubeIntent = new Intent(this, YoutubeActivity.class);
            youtubeIntent.putExtra(Common.EXTRA_YOUTUBEVIDEOID, trailer.key);
            startActivity(youtubeIntent);
        }
    }
}
