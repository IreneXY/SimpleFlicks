package com.mintminter.simpleflicks.util;

import android.content.Context;
import android.util.Log;

import com.mintminter.simpleflicks.R;
import com.mintminter.simpleflicks.api.Bootstrap;
import com.mintminter.simpleflicks.api.MovieContext;

/**
 * Created by Irene on 9/16/17.
 */

public class Common {
    public static final double THRESHOLD_HIGHRATING = 6.0;
    public static String getString(Context context, int stringID){
        return context.getResources().getString(stringID);
    }

    public static boolean isHighRatingMovie(MovieContext.Movie movie){
        return movie.vote_average >= THRESHOLD_HIGHRATING;
    }

}
