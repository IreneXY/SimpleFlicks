package com.mintminter.simpleflicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.mintminter.simpleflicks.util.Common;

/**
 * Created by Irene on 9/17/17.
 */

public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra(Common.EXTRA_MOVIETITLE);
        if(TextUtils.isEmpty(title)){
            finish();
        }

        setContentView(R.layout.activity_moviedetail);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(title);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
