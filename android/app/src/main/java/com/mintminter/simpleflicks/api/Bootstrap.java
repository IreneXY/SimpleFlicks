package com.mintminter.simpleflicks.api;

import android.content.Context;
import android.util.Log;

import com.mintminter.simpleflicks.BuildConfig;
import com.mintminter.simpleflicks.R;
import com.mintminter.simpleflicks.util.Common;

/**
 * Created by Irene on 9/12/17.
 */

public class Bootstrap {
    public static final String MOVIEDBAPIKEY = BuildConfig.MOVIEDBAPIKEY;
    public static final String GOOGLEAPIKEY = BuildConfig.GOOGLEAPIKEY;
    public static final String API_SECURE_BASE_URL = "https://api.themoviedb.org/3";
    public static final String IMAGE_SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch";

    /**
     * get now playing: https://developers.themoviedb.org/3/movies/get-now-playing
     */
    public static final String API_NOW_PLAYING = "/movie/now_playing";

    /**
     * get api configuration: https://developers.themoviedb.org/3/configuration/get-api-configuration
     */
    public static final String API_CONFIGURATION = "/configuration";

    /**
     *  get trailers: https://developers.themoviedb.org/3/movies/get-movie-videos
     */
    public static final String API_TRAILER = "/movie/%1$s/videos";

    /**
     * get api URL
     * @param endpoint
     * @return apiURL
     */
    public static String getApiURL(String endpoint){
        return API_SECURE_BASE_URL + endpoint;
    }

    /**
     * get api URL
     * @param endpoint
     * @param param
     * @return
     */
    public static String getApiURL(String endpoint, String param){
        return getApiURL(String.format(endpoint, param));
    }

    /**
     * get image URL: https://developers.themoviedb.org/3/getting-started/images
     * @param fileSize
     * @param filePath
     * @return imageURL
     */
    public static String getImageURL(String fileSize, String filePath){
        return IMAGE_SECURE_BASE_URL + fileSize + filePath;
    }

    public static String getPosterUrl(Context context, String path){
        return Bootstrap.getImageURL(Common.getString(context, R.string.poster_size), path);
    }

    public static String getBackdropUrl(Context context, String path){
        return Bootstrap.getImageURL(Common.getString(context, R.string.backdrop_size), path);
    }

    public static String getTrailerURL(int videoID){
        return getApiURL(API_TRAILER, videoID+"");
    }

}
