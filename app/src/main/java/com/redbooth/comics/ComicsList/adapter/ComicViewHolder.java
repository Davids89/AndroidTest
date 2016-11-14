package com.redbooth.comics.ComicsList.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redbooth.comics.entities.Marvel;


class ComicViewHolder extends RecyclerView.ViewHolder {
    ImageView thumbnail;
    TextView title;
    Marvel.Data.Comic item;

    ComicViewHolder(View itemView) {
        super(itemView);
        this.thumbnail = (ImageView) itemView.findViewById(com.redbooth.comics.R.id.thumbnail);
        this.title = (TextView) itemView.findViewById(com.redbooth.comics.R.id.title);
    }
}
