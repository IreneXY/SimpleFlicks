package com.mintminter.simpleflicks.api;

import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Irene on 9/13/17.
 */

public class ApiManager {

    public void getPlayingList(String language, int page, String region, final ApiCallback apiCallback){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api_key", Bootstrap.APIKEY);
        if(!TextUtils.isEmpty(language)) {
            params.put("language", language);
        }
        if(page > 0) {
            params.put("page", page);
        }
        if(!TextUtils.isEmpty(region)) {
            params.put("region", region);
        }
        client.get(Bootstrap.getApiURL(Bootstrap.API_NOW_PLAYING), params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                        Log.i("INFO", res);
                        MovieContext.PlayingList playlist = new MovieContext.PlayingList();
                        playlist.loadFromString(res);
                        apiCallback.setPlayingList(playlist);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        apiCallback.setFailure(statusCode, res);
                    }
                }
        );
    }
}
