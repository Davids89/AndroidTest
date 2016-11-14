package com.redbooth.comics.ComicsList;

/**
 * Created by david on 13/11/16.
 */

public interface ComicsListPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ComicsEvent event);
}
