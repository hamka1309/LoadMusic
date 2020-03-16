package com.mia.loadmusic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.loadmusic.adapter.HomeAdapter;
import com.mia.loadmusic.model.Music;
import com.mia.loadmusic.util.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private Disposable disposable;
    private  HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void initView() {
        homeAdapter = new HomeAdapter(this);
        if (this.rvMain != null) {
            this.rvMain.setAdapter(homeAdapter);
        }
        getDataSong();
    }

    private void getDataSong() {
        AppUtil.getAllVideoMedia(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Music>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<Music> music) {
                        if (homeAdapter != null) {
                            homeAdapter.setListMusics(music);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

}
