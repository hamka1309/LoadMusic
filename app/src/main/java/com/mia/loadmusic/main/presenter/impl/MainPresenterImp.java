package com.mia.loadmusic.main.presenter.impl;

import android.content.Context;

import com.mia.loadmusic.main.model.MusicModel;
import com.mia.loadmusic.main.presenter.MainContract;
import com.mia.loadmusic.model.Music;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImp implements MainContract.Presenter {
    private MusicModel musicModel;
    private Context context;
    private MainContract.View view;
    private Disposable disposable;

    public MainPresenterImp(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadData() {
        view.showLoading();
        musicModel = new MusicModel();
        musicModel.getAllVideoMedia(context).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Music>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<Music> music) {
                        view.showData(music);
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showEmpty();
                        view.hideLoading();
                    }
                });
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
