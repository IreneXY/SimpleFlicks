package com.mintminter.simpleflicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mintminter.simpleflicks.api.ApiCallback;
import com.mintminter.simpleflicks.api.ApiManager;
import com.mintminter.simpleflicks.api.MovieContext;

public class MainActivity extends AppCompatActivity implements ApiCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayingList();
    }

    private void getPlayingList(){
        new ApiManager().getPlayingList("en_US", 1, "US", this);
    }

    @Override
    public void setPlayingList(MovieContext.PlayingList playList) {
        Log.i("INFO", "playlist total pages = " + playList.total_pages);
    }

    @Override
    public void setFailure(int statusCode, String res) {

    }
}
