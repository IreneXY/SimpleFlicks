package com.mintminter.simpleflicks.common;

/**
 * Created by Irene on 9/12/17.
 */

public class Bootstrap {
    public static final String APIKEY = "141552688f99dd1ec82085ec46cfecb7";
    public static final String API_SECURE_BASE_URL = "https://api.themoviedb.org/3";
    public static final String IMAGE_SECURE_BASE_URL = "https://image.tmdb.org/t/p/";

    /**
     * get now playing: https://developers.themoviedb.org/3/movies/get-now-playing
     */
    public static final String API_NOW_PLAYING = "/movie/now_playing";

    /**
     * get api configuration: https://developers.themoviedb.org/3/configuration/get-api-configuration
     */
    public static final String API_CONFIGURATION = "/configuration";

    /**
     * get api URL
     * @param endpoint
     * @return apiURL
     */
    public static String getApiURL(String endpoint){
        return API_SECURE_BASE_URL + endpoint;
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

}
