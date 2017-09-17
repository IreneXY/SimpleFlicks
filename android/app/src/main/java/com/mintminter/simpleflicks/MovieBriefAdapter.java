package com.mintminter.simpleflicks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mintminter.simpleflicks.api.ApiCallback;
import com.mintminter.simpleflicks.api.ApiManager;
import com.mintminter.simpleflicks.api.Bootstrap;
import com.mintminter.simpleflicks.api.MovieContext;
import com.mintminter.simpleflicks.util.Common;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Irene on 9/16/17.
 */

public class MovieBriefAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private static final int VIEWTYPE_BRIEF = 0;
    private static final int VIEWTYPE_BACKDROP = 1;

    private Context mContext;
    private ArrayList<MovieContext.Movie> mMovies;
    private ApiCallback mCallback;

    public MovieBriefAdapter(Context context, ArrayList<MovieContext.Movie> movies, ApiCallback callback){
        mContext = context;
        mMovies = movies;
        mCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEWTYPE_BACKDROP){
            return new BackdropViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_backdrop, parent, false));
        }else {
            return new MovieBriefViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType == VIEWTYPE_BACKDROP){
            ((BackdropViewHolder) holder).bind(position);
        }else {
            ((MovieBriefViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public int getItemViewType(int positon){
        MovieContext.Movie movie = mMovies.get(positon);
        if(Common.isHighRatingMovie(movie)){
            return VIEWTYPE_BACKDROP;
        }else{
            return VIEWTYPE_BRIEF;
        }
    }


    class MovieBriefViewHolder extends RecyclerView.ViewHolder{

        private ImageView mPoster;
        private TextView mTitle;
        private TextView mDescription;
        private View mItemView;

        public MovieBriefViewHolder(View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
            mTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
            mDescription = (TextView) itemView.findViewById(R.id.item_movie_description);
            mItemView = itemView;
        }

        public void bind(int position){
            final MovieContext.Movie movie = mMovies.get(position);
            mTitle.setText(movie.title);
            mDescription.setText(movie.overview);
            String tag = (String) mPoster.getTag();
            if(TextUtils.isEmpty(tag)) {
                Picasso.with(mContext)
                        .load(Bootstrap.getPosterUrl(mContext, movie.poster_path))
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mPoster);
            }else{
                Picasso.with(mContext)
                        .load(Bootstrap.getBackdropUrl(mContext, movie.backdrop_path))
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mPoster);
            }
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                    detailIntent.putExtra(Common.EXTRA_MOVIETITLE, movie.title);
                    mContext.startActivity(detailIntent);
                }
            });
        }
    }

    class BackdropViewHolder extends RecyclerView.ViewHolder{

        private ImageView mBackdrop;
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mPlay;
        private View mItemView;

        public BackdropViewHolder(View itemView){
            super(itemView);
            mBackdrop = (ImageView) itemView.findViewById(R.id.backdrop_image);
            mBackdrop.setAlpha(0.9f);
            mTitle = (TextView) itemView.findViewById(R.id.item_backdrop_title);
            mDescription = (TextView) itemView.findViewById(R.id.item_backdrop_description);
            mPlay = (ImageView) itemView.findViewById(R.id.item_backdrop_play);
            mItemView = itemView;
        }

        public void bind(int position){
            final MovieContext.Movie movie = mMovies.get(position);
            Picasso.with(mContext)
                    .load(Bootstrap.getBackdropUrl(mContext, movie.backdrop_path))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mBackdrop);
            mTitle.setText(movie.title);
            mDescription.setText(movie.overview);
            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new ApiManager().getTrailer(movie.id, "en_US", mCallback);
                }
            });
        }
    }
}
