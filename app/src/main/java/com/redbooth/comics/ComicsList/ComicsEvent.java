package com.redbooth.comics.ComicsList;

import com.redbooth.comics.entities.Marvel;

import java.util.List;

/**
 * Created by david on 13/11/16.
 */

public class ComicsEvent {
    List<Marvel.Data.Comic> comics;

    public List<Marvel.Data.Comic> getComics() {
        return comics;
    }

    public void setComics(List<Marvel.Data.Comic> comics) {
        this.comics = comics;
    }
}
