package com.mintminter.simpleflicks.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Irene on 9/13/17.
 */

public class MovieContext {

    public static long getDateInMilliSeconds(String date){
        long milliSeconds = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            milliSeconds = df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliSeconds;
    }

    public static class Movie{
        public int vote_count;
        public int id;
        public boolean video;
        public double vote_average;
        public String title;
        public double popularity;
        public String poster_path;
        public String original_language;
        public String original_title;
        public int[] genre_ids;
        public String backdrop_path;
        public boolean adult;
        public String overview;
        public String release_date;
        public long release_date_in_milliseconds;

        public void loadFromJson(JSONObject movieJson){
            vote_count = movieJson.optInt("vote_count", 0);
            id = movieJson.optInt("id", -1);
            video = movieJson.optBoolean("video", false);
            vote_average = movieJson.optDouble("vote_average", 0.0);
            title = movieJson.optString("title", "");
            popularity = movieJson.optDouble("popularity", 0.0);
            poster_path = movieJson.optString("poster_path","");
            original_language = movieJson.optString("original_language","");
            original_title = movieJson.optString("original_title","");
            JSONArray arrs = movieJson.optJSONArray("genre_ids");
            if(arrs != null){
                int len = arrs.length();
                genre_ids = new int[len];
                for(int i = 0; i < len; i++){
                    genre_ids[i] = arrs.optInt(i,-1);
                }
            }
            backdrop_path = movieJson.optString("backdrop_path", "");
            adult = movieJson.optBoolean("adult", false);
            overview = movieJson.optString("overview", "");
            release_date = movieJson.optString("release_date","0000-00-00");
            release_date_in_milliseconds = getDateInMilliSeconds(release_date);
        }

        public void loadFromString(String result){
            try {
                JSONObject json = new JSONObject(result);
                loadFromJson(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PlayingList{
        public ArrayList<Movie> movies = new ArrayList<>();
        public int page;
        public int total_results;
        public long maximum_date_in_milliseconds;
        public long minimum_date_in_milliseconds;
        public int total_pages;

        public void loadFromJson(JSONObject playingListJson){
            JSONArray results = playingListJson.optJSONArray("results");
            if(results != null){
                int len = results.length();
                for(int i = 0; i < len; i++){
                    JSONObject movieJson = results.optJSONObject(i);
                    if(movieJson != null) {
                        Movie movie = new Movie();
                        movie.loadFromJson(movieJson);
                        movies.add(movie);
                    }
                }
            }
            page = playingListJson.optInt("page",0);
            total_results = playingListJson.optInt("total_results",0);
            JSONObject datesJson = playingListJson.optJSONObject("dates");
            if(datesJson != null){
                maximum_date_in_milliseconds = getDateInMilliSeconds(datesJson.optString("maximum", "0000-00-00"));
                minimum_date_in_milliseconds = getDateInMilliSeconds(datesJson.optString("minimum", "0000-00-00"));
            }
            total_pages = playingListJson.optInt("total_pages",0);
        }

        public void loadFromString(String result){
            try {
                JSONObject json = new JSONObject(result);
                loadFromJson(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    public static class Trailer{
        public static final String TYPE_TEASER = "Teaser";
        public static final String TYPE_TRAILER = "Trailer";

        public String id;
        public String iso_639_1;
        public String iso_3166_1;
        public String key;
        public String name;
        public String site;
        public int size;
        public String type;
        public boolean isInYoutube;
        public boolean isTrailer;

        public void loadFromJson(JSONObject trailerJson){
            id = trailerJson.optString("id", "");
            iso_639_1 = trailerJson.optString("iso_639_1", "");
            iso_3166_1 = trailerJson.optString("iso_3166_1", "");
            key = trailerJson.optString("key", "");
            name = trailerJson.optString("name", "");
            site = trailerJson.optString("site", "");
            isInYoutube = "youtube".equals(site.toLowerCase());
            size = trailerJson.optInt("size", -1);
            type = trailerJson.optString("type");
            isTrailer = TYPE_TRAILER.equals(type);
        }

        public void loadFromString(String result){
            try {
                JSONObject trailerJson = new JSONObject(result);
                loadFromJson(trailerJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class VideoList{
        public int id;
        ArrayList<Trailer> trailers = new ArrayList<>();

        public void loadFromJson(JSONObject videosJson){
            id = videosJson.optInt("id", -1);
            JSONArray results = videosJson.optJSONArray("results");
            if(results != null){
                int len = results.length();
                for(int i = 0; i < len; i++){
                    JSONObject trailerJson = results.optJSONObject(i);
                    if(trailerJson != null) {
                        Trailer trailer = new Trailer();
                        trailer.loadFromJson(trailerJson);
                        trailers.add(trailer);
                    }
                }
            }
        }

        public void loadFromString(String result){
            try {
                JSONObject videosJson = new JSONObject(result);
                loadFromJson(videosJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
