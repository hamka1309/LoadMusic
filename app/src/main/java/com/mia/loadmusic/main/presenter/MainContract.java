package com.mia.loadmusic.main.presenter;

import com.mia.loadmusic.model.Music;

import java.util.List;

public interface MainContract {
    public interface View {
        void showData(List<Music> listMusics);

        void showEmpty();

        void showLoading();

        void hideLoading();
    }

    public interface Presenter {

        void loadData();

        void onDestroy();

    }
}
