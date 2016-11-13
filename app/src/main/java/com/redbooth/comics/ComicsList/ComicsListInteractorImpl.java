package com.redbooth.comics.ComicsList;

/**
 * Created by david on 13/11/16.
 */

public class ComicsListInteractorImpl implements ComicsListInteractor {

    private ComicsListRepository repository;

    public ComicsListInteractorImpl() {
        repository = new ComicsListRepositoryImpl();
    }

    @Override
    public void createKeysMap() {
        repository.createKeysMap();
        repository.marvelUpdating();
    }
}
