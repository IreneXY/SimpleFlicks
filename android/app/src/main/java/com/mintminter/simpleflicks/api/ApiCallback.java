package com.mintminter.simpleflicks.api;

/**
 * Created by Irene on 9/13/17.
 */

public interface ApiCallback {
    void setPlayingList(MovieContext.PlayingList playList);
    void setFailure(int statusCode, String res);
    void setTrailer(MovieContext.Trailer trailer);
}
