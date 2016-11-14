package com.redbooth.comics.ComicsList;

import com.redbooth.comics.lib.EventBus;
import com.redbooth.comics.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by david on 13/11/16.
 */

public class ComicsListPresenterImpl implements ComicsListPresenter {

    private ComicsListInteractor interactor;
    private EventBus eventBus;
    private ComicsView view;

    public ComicsListPresenterImpl(ComicsView view) {
        this.interactor = new ComicsListInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
    }

    @Override
    public void onCreate() {
        interactor.createKeysMap();
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ComicsEvent event) {
        if(view != null){
            view.getComics(event);
        }
    }
}
