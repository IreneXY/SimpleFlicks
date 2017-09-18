package com.mintminter.simpleflicks.util;

import android.content.Context;
import android.util.Log;

import com.mintminter.simpleflicks.R;
import com.mintminter.simpleflicks.api.Bootstrap;
import com.mintminter.simpleflicks.api.MovieContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Irene on 9/16/17.
 */

public class Common {
    public static final double THRESHOLD_HIGHRATING = 5.0;

    public static final String EXTRA_YOUTUBEVIDEOID = "youtubevideoid";
    public static final String EXTRA_MOVIE = "movie";

    public static String getString(Context context, int stringID){
        return context.getResources().getString(stringID);
    }

    public static boolean isHighRatingMovie(MovieContext.Movie movie){
        return movie.vote_average >= THRESHOLD_HIGHRATING;
    }

    public static String readValueFromLoacalProperties(Context context, String key){
        String res = "";
        Properties properties = new Properties();
        InputStream inputStream =
                context.getClass().getClassLoader().getResourceAsStream("local.properties");
        try {
            properties.load(inputStream);
            res = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
