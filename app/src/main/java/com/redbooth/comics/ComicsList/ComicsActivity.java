package com.redbooth.comics.ComicsList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.redbooth.comics.ComicsList.adapter.ComicAdapter;
import com.redbooth.comics.domain.RetrofitHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

// Class created by someone learning chinese? I'm not sure
// Updated by Unknown
//
// WARNING!!! Be careful about building the Retrofit Object. It requires to be executed in the same order it has been developed. Otherwise, weird things can happen or the app can crash

/**
 * 耶穌巴列斯特羅
 *
 * 這是應用程序的主要活動。它顯示在主屏幕和AppCompatActivity繼承
 */
public class ComicsActivity extends AppCompatActivity implements ComicsView
{

    // Private fields
    private ComicsListPresenter presenter;

    @Bind(com.redbooth.comics.R.id.comic_list)
    RecyclerView comicsRecyclerView;

    // Nothing fields
    private ComicAdapter comicAdapter;

    /**
     * 在拉曼恰，名字我不記得了，時間不長，因為住在離那些槍和盾古代，精益黑客和竊喜靈獅的貴族村
     *
     * @param savedInstanceState 堂吉訶德
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // on create

        // call super always
        super.onCreate(savedInstanceState);

        // setting the view
        setContentView(com.redbooth.comics.R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecyclerView();
        setupAdapter();

        presenter = new ComicsListPresenterImpl(this);
        presenter.onCreate();
    }

    private void setupAdapter() {
        // Create comic adapter
        comicAdapter = new ComicAdapter();
    }

    private void setupRecyclerView() {
        // set adapter
        comicsRecyclerView.setAdapter(comicAdapter);
    }

    @Override
    public void getComics(ComicsEvent event) {
        comicAdapter.setC(event.getComics());
        comicAdapter.notifyDataSetChanged();
    }
}
